package com.pedrinspec.shortenUrl.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "shorten_url")
@Getter
@Setter
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "shortCode")
    private String shortCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "access_count")
    private Long accessCount;

}
