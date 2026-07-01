package com.links.shortener.DTO;

public class UrlRequest {
    private String originalUrl;

    public UrlRequest() {
    }

    public UrlRequest(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}