package com.kc.batch.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.batch.dao.entity.Hotel;
import com.kc.batch.dest.dao.repository.IHotelRepository;

public class FilterProcessor implements ItemProcessor<Hotel, Hotel> {

	@Autowired
	public IHotelRepository couchbaseRepo;

	@Override
	public Hotel process(final Hotel hotel) {
		ObjectMapper mapper = new ObjectMapper();
		String dest;
		String source;
		try {
			System.out.println("id " + hotel.getId());

			//Optional<Hotel> optional = couchbaseRepo.findById(hotel.getId());
			
			/*Iterable<Hotel> hotels = couchbaseRepo.findAllById(Arrays.asList(new String[] {"a"}));
			

			if (optional.isPresent()) {
				Hotel destHotel = optional.get();

				Hotel test = new Hotel();
				test.setAddress(destHotel.getAddress());
				test.setId(destHotel.getId());
				
				dest = mapper.writeValueAsString(test);
				source = mapper.writeValueAsString(hotel);

				if (mapper.readTree(source).equals(mapper.readTree(dest))) {
					return null;
				}

			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hotel;
	}
}
