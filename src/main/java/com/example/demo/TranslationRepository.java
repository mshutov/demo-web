package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class TranslationRepository {
    private final JdbcTemplate jdbcTemplate;
    private final Random random = new Random();

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
        return jdbcTemplate.query("select word, meaning from translations", translationPairRowMapper());
    }

    private RowMapper<TranslationPair> translationPairRowMapper() {
        return (rs, rowNum) -> new TranslationPair(rs.getString(1), rs.getString(2));
    }

    public Optional<TranslationPair> random() {
        List<Integer> ids = jdbcTemplate.queryForList("select id from translations", Integer.class);
        int translationId = ids.get(random.nextInt(ids.size()));
        return jdbcTemplate.query("select word, meaning from translations where id=?", translationPairRowMapper(), translationId)
                .stream().findFirst();
    }
}
