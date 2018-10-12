package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class TranslationPair {
    private final String word;
    private final String meaning;
}
