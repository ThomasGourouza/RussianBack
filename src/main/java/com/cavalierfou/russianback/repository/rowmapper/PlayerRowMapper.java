package com.cavalierfou.russianback.repository.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.cavalierfou.russianback.entity.Player;
import org.springframework.jdbc.core.RowMapper;

public class PlayerRowMapper implements RowMapper<Player> {

    @Override
    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Player(
                rs.getLong("id"),
                rs.getLong("birth_country_ref_id"),
                rs.getDate("birth_date"),
                rs.getString("email"),
                rs.getString("first_name"),
                rs.getLong("gender_ref_id"),
                rs.getLong("image_ref_id"),
                rs.getString("last_name"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("phone")
            );
    }
    
}
