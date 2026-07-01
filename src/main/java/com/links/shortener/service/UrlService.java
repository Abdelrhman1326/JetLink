package com.links.shortener.service;

import com.links.shortener.entity.Url;
import com.links.shortener.exception.UrlNotFoundException;
import com.links.shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // Shorten: Save to DB and convert the ID to a Base36 string
    public String shortenUrl(String originalUrl) {
        Url url = urlRepository.save(new Url(originalUrl));
        return Long.toString(url.getId(), 36);
    }

    // Resolve: Get the Url object or throw an exception if it doesn't exist
    public Url getOriginalUrl(String shortCode) throws UrlNotFoundException {
        try {
            Long id = Long.parseLong(shortCode, 36);

            return urlRepository.findById(id)
                    .orElseThrow(() -> new UrlNotFoundException("Short URL not found for code: " + shortCode));
        } catch (NumberFormatException e) {
            throw new UrlNotFoundException("Invalid short URL code: " + shortCode);
        }
    }

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