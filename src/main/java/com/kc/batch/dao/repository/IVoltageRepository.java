package com.kc.batch.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kc.batch.dao.entity.Voltage;

@Repository
public interface IVoltageRepository extends JpaRepository<Voltage, Long> {

}
