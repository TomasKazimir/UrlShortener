package com.kazous.urlshortener.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
public class UrlMapping {
    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Getter
    @Setter
    private String originalUrl;

    @Setter
    @Column(unique = true)
    private String shortUrl;

    private long accessCount;

    @Getter
    @Setter
    private Instant createdAt;
}
