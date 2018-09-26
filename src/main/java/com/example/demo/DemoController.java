package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {
    private final TranslationService translationService;

    public DemoController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping
    public String hello() {
        return "Hello world!";
    }

    @PostMapping(path = "/translations")
    public void createTranslation(@RequestParam String word, @RequestParam String meaning) {
        translationService.addTranslation(word, meaning);
    }

    @GetMapping(path = "/translations")
    public Map<String, String> getTranslations() {
        return translationService.findAll();
    }

    @GetMapping(path = "/translations/{word}")
    public String getTranslations(@PathVariable String word) {
        return translationService.findByWord(word).orElse("[No translation]");
    }

}
