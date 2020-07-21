package com.kc.batch.configuration;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kc.batch.dao.entity.Voltage;

@Component
public class VoltageRowMapper implements RowMapper<Voltage> {

   	@Override
	public Voltage mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Voltage voltage = new Voltage();

        voltage.setVolt(new BigDecimal(rs.getString("volt")));
        voltage.setTime(Double.parseDouble(rs.getString("time")));
        return voltage;
	}
}
