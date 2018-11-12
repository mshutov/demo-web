package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import lombok.val;

@Service
public class TranslationService {
    private final TranslationRepository repository;
    private final YandexTranslator yandexTranslator;

    private final Random random = new Random();

    public TranslationService(TranslationRepository repository, YandexTranslator yandexTranslator) {
        this.repository = repository;
        this.yandexTranslator = yandexTranslator;
    }

    void addTranslation(String word, String meaning) {
        repository.addTranslation(word, meaning);
    }

    Optional<TranslationPair> findByWord(String word) {
        val localResult = repository.findByWord(word);
        return localResult.isPresent() ? localResult : yandexTranslator.findByWord(word)
                .map(meaning -> new TranslationPair(word, meaning, true));
    }

    List<TranslationPair> findAll() {
        return repository.findAll();
    }

    Optional<TranslationPair> random() {
        val records = repository.findAll();
        return records.isEmpty() ? Optional.empty() : Optional.of(chooseRandomFromList(records));
    }

    private TranslationPair chooseRandomFromList(List<TranslationPair> elements) {
        return elements.get(random.nextInt(elements.size()));
    }
}
