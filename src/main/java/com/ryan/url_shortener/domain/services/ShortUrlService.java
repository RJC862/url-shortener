package com.ryan.url_shortener.domain.services;

import com.ryan.url_shortener.domain.entities.ShortUrl;
import com.ryan.url_shortener.domain.models.ShortUrlDto;
import com.ryan.url_shortener.domain.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.StreamSupport.stream;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper;

    public ShortUrlService(ShortUrlRepository shortUrlRepository, EntityMapper entityMapper){
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;
    }

    public List<ShortUrlDto> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls().stream().map(entityMapper::toShortUrlDto).toList();
    }
}
