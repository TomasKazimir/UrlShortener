package com.kazous.urlshortener.service;

import com.kazous.urlshortener.model.UrlMapping;
import com.kazous.urlshortener.repository.UrlRepository;
import com.kazous.urlshortener.util.Base62Encoder;
import com.kazous.urlshortener.util.Encoder;
import com.kazous.urlshortener.util.MockUrlEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository repository;
    private final Encoder encoder =  new Base62Encoder();

    public UrlServiceImpl(UrlRepository repository) {
        this.repository = repository;
    }

    public String shortenUrl(String url) {
        UrlMapping mapping = new UrlMapping();

        mapping.setOriginalUrl(url);
        mapping.setCreatedAt(Instant.now());

        repository.save(mapping);

        String code = encoder.encode(mapping.getId());

        mapping.setShortUrl(code);
        repository.save(mapping);

        return code;
    }

    public String getOriginalUrl(String shortUrl) {
        return repository.findByShortUrl(shortUrl)
                .map(UrlMapping::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found in database"));
    }
}
