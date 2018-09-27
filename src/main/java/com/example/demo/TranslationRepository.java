package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TranslationRepository {
    private final JdbcTemplate jdbcTemplate;

    public TranslationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addTranslation(String word, String meaning) {
        jdbcTemplate.update("insert into translations (word, meaning) values (?,?)", word, meaning);
    }

    public Optional<String> findByWord(String word) {
        return jdbcTemplate.queryForList("select meaning from translations where word=?", String.class, word)
                .stream().findFirst();
    }

    public List<TranslationPair> findAll() {
        return jdbcTemplate.query("select word, meaning from translations",
                (rs, rowNum) -> new TranslationPair(rs.getString(1), rs.getString(2)));
    }
}
