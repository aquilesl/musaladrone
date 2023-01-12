package com.musala.alvaro.testdrones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.musala.alvaro.testdrones.model.BatteryCheckLog;

public interface BatteryCheckLogRepository extends JpaRepository<BatteryCheckLog, Long>{

}
