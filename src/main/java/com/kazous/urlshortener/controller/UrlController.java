package com.kazous.urlshortener.controller;

import com.kazous.urlshortener.dto.ShortenRequest;
import com.kazous.urlshortener.dto.ShortenResponse;
import com.kazous.urlshortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenResponse> shortenUrl(@RequestBody ShortenRequest request) {
        String code = urlService.shortenUrl(request.getUrl());
        String fullUrl = "http://localhost:8080/api/" + code;
        return ResponseEntity.ok(new ShortenResponse(fullUrl));
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String code) {
        String originalUrl = urlService.getOriginalUrl(code);

        return ResponseEntity.status(302)
            .location(URI.create(originalUrl))
            .build();
    }
}
