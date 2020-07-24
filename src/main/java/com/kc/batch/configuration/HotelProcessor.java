package com.kc.batch.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.kc.batch.dao.entity.Hotel;
import com.kc.batch.dest.dao.repository.IHotelRepository;

public class HotelProcessor implements PageProcessor<Hotel> {

	@Autowired
	public IHotelRepository couchbaseRepo;
	
	@Override
	public void process(List<Hotel> source) {
		
		List<Long> collect = source.stream()
                .map(Hotel::getHotelId)
                .collect(Collectors.toList());
		
		
		System.out.println("Thread source.."+Thread.currentThread().getId()+" ..id "+collect);
		
		Iterable<Hotel> iterable = couchbaseRepo.findAllByHotelIdIn(collect);
		List<Hotel> dest = new ArrayList<Hotel>();
	    iterable.forEach(dest::add);
	    //source.retainAll(dest);
	    
	    System.out.println("dest" + dest );
	    List<Long> destForPrint = source.stream()
                .map(Hotel::getHotelId)
                .sorted()
                .collect(Collectors.toList());
	    
	    
	    System.out.println("Thread dest.."+Thread.currentThread().getId()+" ..id "+destForPrint);
	    
	    source.removeAll(dest);
	    
	    System.out.println("Thread not equal one .."+Thread.currentThread().getId()+" ..id "+source);
	    
	    
	    
	}
	


}