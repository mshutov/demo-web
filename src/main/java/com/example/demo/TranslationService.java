package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class TranslationService {
    private final TranslationRepository repository;
    private final Random random = new Random();

    public TranslationService(TranslationRepository repository) {
        this.repository = repository;
    }

    public void addTranslation(String word, String meaning) {
        repository.addTranslation(word, meaning);
    }

    public Optional<String> findByWord(String word) {
        return repository.findByWord(word);
    }

    public Map<String, String> findAll() {
        Map<String, String> result = new HashMap<>();
        repository.findAll().forEach(pair -> result.put(pair.getWord(), pair.getMeaning()));
        return result;
    }

    public Optional<TranslationPair> random() {
        List<Integer> ids = repository.findIds();
        return ids.isEmpty() ? Optional.empty() : repository.getById(chooseRandomIdFromList(ids));
    }

    private Integer chooseRandomIdFromList(List<Integer> ids) {
        return ids.get(random.nextInt(ids.size()));
    }
}
