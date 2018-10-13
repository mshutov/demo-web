package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "word/{word}")
    public String meaning(@PathVariable String word, Model model) {
        translationService.findByWord(word).ifPresent(tp -> {
            model.addAttribute("word", tp.getWord());
            model.addAttribute("meaning", tp.getMeaning());
        });
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
