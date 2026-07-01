package com.links.shortener.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "urls")
public class Url implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalUrl;

    public Url() {}

    public Url(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }
}