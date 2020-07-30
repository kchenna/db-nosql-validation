package com.kc.batch.source.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kc.batch.dao.entity.Hotel;

@Repository
public interface ISourceRepository extends CrudRepository<Hotel, String> {

}
