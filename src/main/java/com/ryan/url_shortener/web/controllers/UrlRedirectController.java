package com.ryan.url_shortener.web.controllers;

import com.ryan.url_shortener.domain.models.ShortUrlDto;
import com.ryan.url_shortener.domain.services.ShortUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UrlRedirectController {

    private final ShortUrlService shortUrlService;

    public UrlRedirectController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/s/{key}")
    public String redirectToUrl(@PathVariable String key){
        String originalUrl = shortUrlService.getOriginalUrlByKey(key);

        if(originalUrl != null){
            return "redirect:" + originalUrl;
        } else  {
            return "redirect:/";
        }
    }
}
