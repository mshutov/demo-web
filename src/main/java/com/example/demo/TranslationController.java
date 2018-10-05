package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/translations")
public class TranslationController {
    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping
    public void createTranslation(@RequestParam String word, @RequestParam String meaning) {
        translationService.addTranslation(word, meaning);
    }

    @GetMapping
    public Map<String, String> getTranslations() {
        return translationService.findAll();
    }

    @GetMapping(path = "/{word}")
    public String getTranslations(@PathVariable String word) {
        return translationService.findByWord(word).orElse("[No translation]");
    }

    @GetMapping(path = "/random")
    public TranslationPair getRandomTranslations() {
        return translationService.random().orElse(new TranslationPair("[random]", "[No translation]"));
    }
}
