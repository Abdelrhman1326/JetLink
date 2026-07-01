package com.links.shortener.service;

import com.links.shortener.entity.Url;
import com.links.shortener.exception.UrlNotFoundException;
import com.links.shortener.repository.UrlRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final CacheManager cacheManager;

    public UrlService(UrlRepository urlRepository, CacheManager cacheManager) {
        this.urlRepository = urlRepository;
        this.cacheManager = cacheManager;
    }

    public String shortenUrl(String originalUrl) {
        // if this url already exist in db as value, then just return it
        java.util.Optional<Url> existingUrl = urlRepository.findByOriginalUrl(originalUrl);
        if (existingUrl.isPresent()) {
            return Long.toString(existingUrl.get().getId(), 36);
        }

        Url url = urlRepository.save(new Url(originalUrl));
        String shortCode = Long.toString(url.getId(), 36);

        if (cacheManager.getCache("links") != null) {
            cacheManager.getCache("links").put(shortCode, url);
        }

        return shortCode;
    }

    @Cacheable(value = "links", key = "#shortCode")
    public Url getOriginalUrl(String shortCode) throws UrlNotFoundException {
        try {
            Long id = Long.parseLong(shortCode, 36);
            return urlRepository.findById(id)
                    .orElseThrow(() -> new UrlNotFoundException("Short URL not found for code: " + shortCode));
        } catch (NumberFormatException e) {
            throw new UrlNotFoundException("Invalid short URL code: " + shortCode);
        }
    }

    @CacheEvict(value = "links", key = "#shortCode")
    public void removeOriginalUrl(String shortCode) throws UrlNotFoundException {
        try {
            Long id = Long.parseLong(shortCode, 36);
            if (urlRepository.existsById(id)) {
                urlRepository.deleteById(id);
            } else {
                throw new UrlNotFoundException("This Url does not exist!");
            }
        } catch (NumberFormatException e) {
            throw new UrlNotFoundException("Invalid short URL code: " + shortCode);
        }
    }
}