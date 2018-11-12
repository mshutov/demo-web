package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import lombok.val;

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
    public RedirectView random() {
        return new RedirectView(
                translationService.random()
                        .map(TranslationPair::getWord)
                        .map(w -> "/word/" + w)
                        .orElse("/"));
    }

    @GetMapping(path = "word/{word}")
    public String meaning(@PathVariable String word, Model model) {
        val tp = translationService.findByWord(word)
                .orElseGet(() -> new TranslationPair(word, "[No translation available]"));

        model.addAttribute("word", tp.getWord());
        model.addAttribute("meaning", tp.getMeaning());
        model.addAttribute("translatedByYandex", tp.isTranslatedByYandex());

        return "card";
    }

    @RequestMapping(path = "new", method = {RequestMethod.GET, RequestMethod.POST})
    public String createForm(@RequestParam(required = false) String word,
                             @RequestParam(required = false) String meaning,
                             Model model) {
        if (word != null && meaning != null) {
            translationService.addTranslation(word, meaning);
            model.addAttribute("word", word);
            model.addAttribute("meaning", meaning);
        }
        return "form";
    }
}
