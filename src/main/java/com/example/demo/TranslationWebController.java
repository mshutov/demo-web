package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mikhail Shutov
 */
@Controller
@RequestMapping(path = "/")
public class TranslationWebController {
    private final TranslationService translationService;

    public TranslationWebController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping(path = "random")
    public String home(Model model) {
        translationService.random().ifPresent(tp -> {
            model.addAttribute("word", tp.getWord());
            model.addAttribute("meaning", tp.getMeaning());
        });
        return "card";
    }
}
