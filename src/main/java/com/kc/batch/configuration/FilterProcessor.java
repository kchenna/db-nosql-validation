package com.kc.batch.configuration;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.batch.dao.entity.Hotel;
import com.kc.batch.dest.dao.repository.IHotelRepository;

public class FilterProcessor implements ItemProcessor<Hotel, Hotel>{

    @Autowired
    public IHotelRepository couchbaseRepo;

    @Override
    public Hotel process(final Hotel hotel) {
        ObjectMapper mapper = new ObjectMapper();
    	String s;
		try {
				s = mapper.writeValueAsString(couchbaseRepo.findById("hotel_10025").get());
				String test = "{\"address\":\"Capstone Road, ME7 3JE\",\"alias\":null,\"checkin\":null,\"checkout\":null,\"city\":\"Medway\",\"country\":\"United Kingdom\",\"description\":\"40 bed summer hostel about 3 miles from Gillingham, housed in a districtive converted Oast House in a semi-rural setting.\",\"directions\":null,\"email\":null,\"fax\":null,\"free_breakfast\":true,\"free_internet\":false,\"free_parking\":true,\"id\":\"hotel_10025\",\"name\":\"Medway Youth Hostel\",\"pets_ok\":true,\"phone\":\"+44 870 770 5964\",\"public_likes\":[\"Julius Tromp I\",\"Corrine Hilll\",\"Jaeden McKenzie\",\"Vallie Ryan\",\"Brian Kilback\",\"Lilian McLaughlin\",\"Ms. Moses Feeney\",\"Elnora Trantow\"],\"state\":null,\"title\":\"Gillingham (Kent)\",\"tollfree\":null,\"type\":\"hotel\",\"url\":\"http://www.yha.org.uk\",\"vacancy\":true}";
				System.out.println("validation "+mapper.readTree(s).equals(mapper.readTree(test)));
				if (mapper.readTree(s).equals(mapper.readTree(test))) {
					return null;
				}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
        return hotel;
    }
}
