package com.ryan.url_shortener;

import com.ryan.url_shortener.domain.entities.ShortUrl;
import com.ryan.url_shortener.domain.repository.ShortUrlRepository;
import com.ryan.url_shortener.domain.services.ShortUrlService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ryan.url_shortener.domain.services.ShortUrlService;

import java.util.List;

@Controller
public class HomeController {


    private final ShortUrlService shortUrlService;

    public HomeController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ShortUrl> shortUrls = shortUrlService.findAllPublicShortUrls();
        model.addAttribute("shortUrls", shortUrlService);
        model.addAttribute("baseUrl", "http://localhost:8080");
        return "index";
    }

}