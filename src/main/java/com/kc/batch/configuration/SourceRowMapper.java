package com.kc.batch.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kc.batch.dao.entity.Hotel;

@Component
public class SourceRowMapper implements RowMapper<Hotel> {

   	@Override
	public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
   		Hotel h = new Hotel();
   		h.setAddress(rs.getString("address"));
   		h.setId(rs.getString("id"));
		return h;
	}
}
