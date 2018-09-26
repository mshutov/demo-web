package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:mshutov@wiley.com">Mikhail Shutov</a>
 */
@Service
public class TranslationService {
    private final ConcurrentHashMap<String, String> translations = new ConcurrentHashMap<>();

    public void addTranslation(String word, String meaning) {
        translations.put(word, meaning);
    }

    public Optional<String> findByWord(String word) {
        return Optional.ofNullable(translations.get(word));
    }

    public Map<String, String> findAll() {
        return Collections.unmodifiableMap(translations);
    }
}
