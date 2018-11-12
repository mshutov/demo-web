package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * @author Mikhail Shutov
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YandexTranslation {
    private List<String> translations;

    @JsonIgnore
    public Optional<String> getFirstTranslation() {
        return translations != null && !translations.isEmpty() ? Optional.of(translations.get(0)) : Optional.empty();
    }
}
