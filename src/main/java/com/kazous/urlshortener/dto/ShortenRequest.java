package com.kazous.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

@Data
public class ShortenRequest {
    @NotBlank
    @URL
    @Getter
    private String url;
}
