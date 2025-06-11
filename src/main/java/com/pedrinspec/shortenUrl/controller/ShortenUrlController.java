package com.pedrinspec.shortenUrl.controller;

import com.pedrinspec.shortenUrl.dto.ShortUrl;
import com.pedrinspec.shortenUrl.dto.ShortenUrlRequest;
import com.pedrinspec.shortenUrl.service.ShortUrlService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Tag(name= "Shorten URL", description = "Endpoints for URL shortening")
@RestController
@RequestMapping("/shorten-url")
public class ShortenUrlController {

    private final ShortUrlService service;

    @Autowired
    public ShortenUrlController(ShortUrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody @Valid ShortenUrlRequest url) {
        ShortUrl shortUrl = service.createShortUrl(url.getUrl());
        return ResponseEntity.ok(Map.of("shortenedUrl", "http://localhost:8080/"+shortUrl.getShortCode()));
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirectView(@PathVariable String shortCode) {
        return service.findByShortCode(shortCode)
                .map(url -> new RedirectView(url.getOriginalUrl()))
                .orElseGet(() -> new RedirectView("/not-found"));
    }

}
