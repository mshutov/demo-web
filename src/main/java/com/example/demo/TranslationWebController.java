package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author <a href="mailto:mshutov@wiley.com">Mikhail Shutov</a>
 */
@Controller
public class TranslationWebController {

    @GetMapping("/")
    String home() {
        return "index";
    }
}
