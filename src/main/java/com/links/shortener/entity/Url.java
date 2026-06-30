package com.links.shortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "URLs")
public class Url {
    @Id
    private String originalUrl;
    private long shortenedCode;

    // constructors
    public Url() {

    }
    public Url(String originalUrl, long shortenedCode) {
        this.originalUrl = originalUrl;
        this.shortenedCode = shortenedCode;
    }

    // getters
    public String getOriginalUrl() {
        return originalUrl;
    }
    public long getShortenedCode() {
        return shortenedCode;
    }

    // setters
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public void setShortenedCode(long shortenedCode) {
        this.shortenedCode = shortenedCode;
    }
}