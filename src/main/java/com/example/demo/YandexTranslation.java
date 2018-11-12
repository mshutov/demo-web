package com.example.demo;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Mikhail Shutov
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YandexTranslation {
    private List<String> text;

    @JsonIgnore
    public Optional<String> getFirstTranslation() {
        return text != null && !text.isEmpty() ? Optional.of(text.get(0)) : Optional.empty();
    }
}
