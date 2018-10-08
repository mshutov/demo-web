package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mikhail Shutov
 */
@Controller
@RequestMapping(path = "/")
public class TranslationWebController {

    @GetMapping
    String home() {
        return "index";
    }
}
