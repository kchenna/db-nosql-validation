package com.kc.batch.source.dao.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kc.batch.configuration.SourceRowMapper;
import com.kc.batch.dao.entity.Hotel;

@Repository
public class HotelImpl {
	
	/*@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcTemplate1;
	
	public List<Hotel> getAllHotel() {
		String sql1 = "select address from hotel";
		return jdbcTemplate1.query(sql1, new SourceRowMapper());
	}
	
	
	public void testCreate(Hotel hotel) {
		
		String sql ="INSERT INTO hotel(\n" + 
				"	hotel_id,address, alias, checkin, checkout, city, country, description, directions, email, fax, free_breakfast, free_internet,"
				+ " free_parking, geo_id, id, name, pets_ok, phone, price, public_likes, review_id, state, title,"
				+ " tollfree, type, url, vacancy)\n" + 
				"	VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate1.update(sql, new Object[] { 
				hotel.getHotelId(),
				hotel.getAddress(),
				hotel.getAlias(),
				hotel.getCheckin(),
				hotel.getCheckout(),
				hotel.getCity(),
				hotel.getCountry(),
				hotel.getDescription(),
				hotel.getDirections(),
				hotel.getEmail(),
				hotel.getFax(),
				hotel.isFree_breakfast(),
				hotel.isFree_internet(),
				hotel.isFree_parking(),
				null,
				hotel.getId(),
				hotel.getName(),
				hotel.isPets_ok(),
				hotel.getPhone(),
				0,
				null,
				null,
				hotel.getState(),
				hotel.getTitle(),
				hotel.getTollfree(),
				hotel.getType(),
				hotel.getUrl(),
				hotel.isVacancy()
		});
	}*/

}
