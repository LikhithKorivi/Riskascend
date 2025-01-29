package org.interceptica.subfinder.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScanResult {
    private String domain;
    private List<String> subdomains;
    private List<String> resolvedIps;
    private List<String> httpResponses;
    private List<String> vulnerabilities;

}
