package org.interceptica.subfinder.service;

import org.interceptica.subfinder.model.ScanResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class SubFinderService {

    private static final Logger log = LoggerFactory.getLogger(SubFinderService.class);

    private final static String SUBFINDER = "subfinder";

    public ScanResult getSubDomains(String domain) throws IOException, InterruptedException {

        if (domain == null || domain.isEmpty()) {
            return new ScanResult();
        }

        ScanResult result = new ScanResult();
        result.setDomain(domain);
        log.info(String.format("Running command: %s -d " + domain + " -silent", SUBFINDER));
        String subFinderResult = runCommand(String.format("%s -d " + domain + " -silent", SUBFINDER));
        List<String> subdomains = List.of(subFinderResult.split("\n"));
        if (!subdomains.isEmpty()) {
            result.setSubdomains(subdomains);
        }

        return result;
    }

    public static String runCommand(String command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Command failed with exit code " + exitCode);
        }
        return output.toString();
    }

}
