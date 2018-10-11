package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        return "index";
    }

    @GetMapping(path = "random")
    public String random(Model model) {
        translationService.random().ifPresent(tp -> {
            model.addAttribute("word", tp.getWord());
            model.addAttribute("meaning", tp.getMeaning());
        });
        return "card";
    }

    @GetMapping(path = "new")
    public String createForm() {
        return "form";
    }

    @PostMapping(path = "create")
    public String create(@RequestParam String word, @RequestParam String meaning, Model model) {
        translationService.addTranslation(word, meaning);
        model.addAttribute("word", word);
        model.addAttribute("meaning", meaning);
        return "card";
    }
}
