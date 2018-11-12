package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import lombok.val;


/**
 * @author Mikhail Shutov
 */
@Service
public class YandexTranslator {
    private final RestTemplate restTemplate = new RestTemplate();

    private final static String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate" +
            "?key={apiKey}" +
            "&text={text}" +
            "&lang=en-ru";

    private final String apiKey;

    public YandexTranslator(@Value("${api.yandex.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public Optional<String> findByWord(String word) {
        val url = UriComponentsBuilder.fromHttpUrl(API_URL).build(apiKey, word);
        return Optional.ofNullable(restTemplate.getForObject(url, YandexTranslation.class))
                .flatMap(YandexTranslation::getFirstTranslation);
    }
}
