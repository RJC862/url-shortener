package com.ryan.url_shortener.domain.services;

import com.ryan.url_shortener.domain.entities.ShortUrl;
import com.ryan.url_shortener.domain.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public List<ShortUrl> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls();
    }
}
