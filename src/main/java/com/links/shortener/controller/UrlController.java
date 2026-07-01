package com.links.shortener.controller;

import com.links.shortener.DTO.UrlRequest;
import com.links.shortener.entity.Url;
import com.links.shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    // create new short link
    @PostMapping("/api/v1/urls")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest request) {
        String shortCode = urlService.shortenUrl(request.getOriginalUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(baseUrl + "/" + shortCode);
    }

    // visit link
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode) {
        Url url = urlService.getOriginalUrl(shortCode);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url.getOriginalUrl()))
                .build();
    }

    // remove short link
    @DeleteMapping("/api/v1/urls/{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode) {
        urlService.removeOriginalUrl(shortCode);
        return ResponseEntity.noContent().build();
    }
}