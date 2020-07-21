package com.kc.batch.dao.repository;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.kc.batch.dao.entity.Hotel;
 
@Repository
public interface IHotelRepository extends CouchbaseRepository<Hotel, String>
{
	@Query("SELECT COUNT(*) AS c FROM `travel-sample` ")
	Integer countAll();
	
}
