package com.ryan.url_shortener.web.controllers;

import com.ryan.url_shortener.domain.entities.ShortUrl;
import com.ryan.url_shortener.domain.models.ShortUrlDto;
import com.ryan.url_shortener.domain.services.ShortUrlService;
import com.ryan.url_shortener.web.controllers.dtos.CreateShortUrlForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {


    private final ShortUrlService shortUrlService;

    public HomeController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ShortUrlDto> shortUrls = shortUrlService.findAllPublicShortUrls();
        model.addAttribute("shortUrls", shortUrls);
        model.addAttribute("baseUrl", "http://localhost:8080");
        model.addAttribute("createShortUrlForm", new CreateShortUrlForm("http://localhost:8080"));
        return "index";
    }

}