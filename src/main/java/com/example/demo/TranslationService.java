package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import lombok.val;

@Service
public class TranslationService {
    private final TranslationRepository repository;
    private final Random random = new Random();

    public TranslationService(TranslationRepository repository) {
        this.repository = repository;
    }

    void addTranslation(String word, String meaning) {
        repository.addTranslation(word, meaning);
    }

    Optional<TranslationPair> findByWord(String word) {
        return repository.findByWord(word);
    }

    List<TranslationPair> findAll() {
        return repository.findAll();
    }

    Optional<TranslationPair> random() {
        val records = repository.findAll();
        return records.isEmpty() ? Optional.empty() : Optional.of(chooseRandomFromList(records));
    }

    private TranslationPair chooseRandomFromList(List<TranslationPair> ids) {
        return ids.get(random.nextInt(ids.size()));
    }
}
