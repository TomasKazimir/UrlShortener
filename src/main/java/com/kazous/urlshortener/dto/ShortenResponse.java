package com.kazous.urlshortener.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortenResponse {
    private String shortenedUrl;
}
