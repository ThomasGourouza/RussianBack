package com.cavalierfou.russianback.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cavalierfou.russianback.entity.RussianAdjective;
import org.springframework.jdbc.core.RowMapper;

public class RussianAdjectiveRowMapper implements RowMapper<RussianAdjective> {

    @Override
    public RussianAdjective mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RussianAdjective(
            rs.getLong("id"), 
            rs.getString("root"), 
            rs.getLong("russian_adjective_category_ref_id"), 
            rs.getString("translation"));
    }
    
}
