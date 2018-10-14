package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class TranslationRepository {
    private final JdbcTemplate jdbcTemplate;

    TranslationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void addTranslation(String word, String meaning) {
        jdbcTemplate.update("insert into translations (word, meaning) values (?,?)", word, meaning);
    }

    Optional<TranslationPair> findByWord(String word) {
        return jdbcTemplate.query("select word, meaning from translations where word=?", translationPairRowMapper(), word)
                .stream().findFirst();
    }

    List<TranslationPair> findAll() {
        return jdbcTemplate.query("select word, meaning from translations", translationPairRowMapper());
    }

    private RowMapper<TranslationPair> translationPairRowMapper() {
        return (rs, rowNum) -> new TranslationPair(rs.getString(1), rs.getString(2));
    }
}
