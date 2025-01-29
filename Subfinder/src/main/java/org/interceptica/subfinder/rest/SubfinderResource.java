package org.interceptica.subfinder.rest;


import org.interceptica.subfinder.model.DomainDto;
import org.interceptica.subfinder.model.ScanResult;
import org.interceptica.subfinder.service.SubFinderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/risk")
public class SubfinderResource {

    private static final Logger log = LoggerFactory.getLogger(SubfinderResource.class);

    @Autowired
    SubFinderService subFinderService;

    @PostMapping("/scan")
    public ResponseEntity<ScanResult> scanDomains(@RequestBody DomainDto domain) throws IOException, InterruptedException {
        // Process domains
        ScanResult results = subFinderService.getSubDomains(domain.getDomain());
        return ResponseEntity.ok(results);
    }

}