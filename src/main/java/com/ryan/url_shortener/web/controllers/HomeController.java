package com.ryan.url_shortener.web.controllers;

import com.ryan.url_shortener.ApplicationProperties;
import com.ryan.url_shortener.domain.models.CreateShortUrlCmd;
import com.ryan.url_shortener.domain.models.ShortUrlDto;
import com.ryan.url_shortener.domain.services.ShortUrlService;
import com.ryan.url_shortener.dtos.CreateShortUrlForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ryan.url_shortener.ApplicationProperties;

import java.util.List;

@Controller
public class HomeController {


    private final ShortUrlService shortUrlService;
    private final ApplicationProperties properties;

    public HomeController(ShortUrlService shortUrlService, ApplicationProperties properties) {
        this.shortUrlService = shortUrlService;
        this.properties = properties;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ShortUrlDto> shortUrls = shortUrlService.findAllPublicShortUrls();
        model.addAttribute("shortUrls", shortUrls);
        model.addAttribute("baseUrl", properties.baseUrl());
        model.addAttribute("createShortUrlForm", new CreateShortUrlForm("", null));
        return "index";
    }

    @PostMapping("/short-urls")
    String createShortUrl(@ModelAttribute("createShortUrlForm") @Valid CreateShortUrlForm form,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if (bindingResult.hasErrors()) {
            List<ShortUrlDto> shortUrls = shortUrlService.findAllPublicShortUrls();
            model.addAttribute("shortUrls", shortUrls);
            model.addAttribute("baseUrl", properties.baseUrl());
            return "index";
        }

        try {
            CreateShortUrlCmd cmd  = new CreateShortUrlCmd(form.originalUrl(), form.expiryInDays());
            var shortUrlDto = shortUrlService.createShortUrl(cmd);
            redirectAttributes.addFlashAttribute("successMessage", "Successfully Created Short-Url: " +
                    properties.baseUrl() + shortUrlDto.shortKey());
        } catch (Exception e){
                model.addAttribute("invalidUrlMessage", "Invalid URL");
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to Create Short-Url");
        }
        return "redirect:/";
    }

}