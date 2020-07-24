package com.kc.batch.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.kc.batch.dao.entity.Hotel;
import com.kc.batch.dao.entity.Validator;
import com.kc.batch.dest.dao.repository.IHotelRepository;

public class Aggregator implements ItemWriter<Hotel> {

	@Autowired
	private Validator validator;

	@Autowired
	public IHotelRepository couchbaseRepo;

	
	@Override
	public void write(List<? extends Hotel> source) throws Exception {
		
		System.out.println("source ... "+source);
		
		/*List<Long> collect = source.stream()
                .map(Hotel::getHotelId)
                .collect(Collectors.toList());
		
		System.out.println("Thread source.."+Thread.currentThread().getId()+" ..id "+collect);
		
		Iterable<Hotel> iterable = couchbaseRepo.findAllByHotelIdIn(collect);
		List<Hotel> dest = new ArrayList<Hotel>();
	    iterable.forEach(dest::add);

	    List<Hotel> hotels = new ArrayList<Hotel>(source);
	    hotels.removeAll(dest);
		
	    validator.addAll(hotels);*/
		
		
	}

}