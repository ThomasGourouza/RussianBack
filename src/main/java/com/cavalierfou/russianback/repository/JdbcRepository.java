package com.cavalierfou.russianback.repository;

import java.util.List;
import com.cavalierfou.russianback.repository.rowmapper.SequenceNumberRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void resetSequence(String table, String sequence) {
        String maxQuery = "select max(id) as max from " + table;
        List<Long> maxs = namedParameterJdbcTemplate.query(maxQuery, new SequenceNumberRowMapper());

        if (!maxs.isEmpty()) {
            Long max = maxs.get(0);
            String alterQuery = "alter sequence " + sequence + " restart with " + (max + 1);
            namedParameterJdbcTemplate.update(alterQuery, new MapSqlParameterSource());
        }
    }

    public void delete(String table, String column, String id) {
        String deleteQuery = "delete from " + table + " where " + column + " = " + id;
        namedParameterJdbcTemplate.update(deleteQuery, new MapSqlParameterSource());
    }

}
