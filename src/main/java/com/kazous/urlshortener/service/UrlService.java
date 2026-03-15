package com.kazous.urlshortener.service;

public interface UrlService {
    String shortenUrl(String url);
    String getOriginalUrl(String shortUrl);
}
