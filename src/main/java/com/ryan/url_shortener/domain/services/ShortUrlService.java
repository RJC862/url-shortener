package com.ryan.url_shortener.domain.services;

import com.ryan.url_shortener.ApplicationProperties;
import com.ryan.url_shortener.domain.entities.ShortUrl;
import com.ryan.url_shortener.domain.models.CreateShortUrlCmd;
import com.ryan.url_shortener.domain.models.ShortUrlDto;
import com.ryan.url_shortener.domain.repository.ShortUrlRepository;
import com.ryan.url_shortener.dtos.CreateShortUrlForm;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;

import static java.util.stream.StreamSupport.stream;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper;
    private final ApplicationProperties properties;

    public ShortUrlService(ShortUrlRepository shortUrlRepository,
                           EntityMapper entityMapper,
                           ApplicationProperties properties) {
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;
        this.properties = properties;
    }

    public List<ShortUrlDto> findAllPublicShortUrls() {
        return shortUrlRepository.findPublicShortUrls().stream().map(entityMapper::toShortUrlDto).toList();
    }

    public ShortUrlDto createShortUrl(CreateShortUrlCmd cmd) {
        var shortKey = generateUniqueShortKey();
        var shortUrl = new ShortUrl();
        var expiryInDays = cmd.expiryInDays();
        shortUrl.setOriginalUrl(cmd.originalUrl());
        shortUrl.setShortKey(shortKey);
        shortUrl.setCreatedBy(null);
        shortUrl.setIsPrivate(false);
        shortUrl.setClickCount(0L);
        shortUrl.setExpiresAt(Instant.now().plus(
                expiryInDays != null ? expiryInDays : properties.defaultExpiryInDays(),
                java.time.temporal.ChronoUnit.DAYS));
        shortUrl.setCreatedAt(Instant.now());
        shortUrlRepository.save(shortUrl);
        return entityMapper.toShortUrlDto(shortUrl);
    }

    private String generateUniqueShortKey() {
        String shortKey;
        do {
            shortKey = generateRandomShortKey();
        } while (shortUrlRepository.existsByShortKey(shortKey));
        return shortKey;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_KEY_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomShortKey() {
        StringBuilder sb = new StringBuilder(SHORT_KEY_LENGTH);
        for (int i = 0; i < SHORT_KEY_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
