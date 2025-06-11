package com.pedrinspec.shortenUrl.service;

import com.pedrinspec.shortenUrl.dto.ShortUrl;
import com.pedrinspec.shortenUrl.repository.ShortenRespository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class ShortUrlService {

    private final ShortenRespository repository;
    private final Random random = new Random();

    @Autowired
    public ShortUrlService(ShortenRespository repository) {
        this.repository = repository;
    }

    public ShortUrl createShortUrl(@NotNull String url) {
        String code;
        do {
            code = generateShortCode();
        } while (repository.findByShortCode(code).isPresent());

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setCreatedAt(LocalDateTime.now());
        shortUrl.setOriginalUrl(url);
        shortUrl.setShortCode(code);
        return repository.save(shortUrl);
    }

    public Optional<ShortUrl> findByShortCode(String code) {
        Optional<ShortUrl> shortUrl = repository.findByShortCode(code);
        shortUrl.ifPresent(url -> {
            url.setAccessCount(url.getAccessCount() + 1);
            repository.save(url);
        });
        return shortUrl;
    }

    private String generateShortCode() {
        StringBuilder shortCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            shortCode.append(BASE62.charAt(random.nextInt(BASE62.length())));
        }
        return shortCode.toString();
    }
}
