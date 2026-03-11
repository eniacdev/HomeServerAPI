package com.example.HomeServerAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.example.HomeServerAPI.model.SystemLog;

public interface IMetricRepository extends JpaRepository<SystemLog, Long>{

}
