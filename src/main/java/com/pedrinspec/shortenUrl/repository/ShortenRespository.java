package com.pedrinspec.shortenUrl.repository;

import com.pedrinspec.shortenUrl.dto.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortenRespository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortCode(String code);
}
