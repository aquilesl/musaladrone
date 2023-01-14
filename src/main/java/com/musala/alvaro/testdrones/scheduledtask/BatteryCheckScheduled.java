package com.musala.alvaro.testdrones.scheduledtask;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.musala.alvaro.testdrones.model.BatteryCheckLog;
import com.musala.alvaro.testdrones.model.Drone;
import com.musala.alvaro.testdrones.service.IBatteryCheckLogService;
import com.musala.alvaro.testdrones.service.IDroneService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Component
public class BatteryCheckScheduled {

	
	private static final Logger logger = LoggerFactory.getLogger(BatteryCheckScheduled.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private IBatteryCheckLogService batteryCheckService;
	private IDroneService droneService;
    
    @Autowired
	public BatteryCheckScheduled(IBatteryCheckLogService batteryCheckService, IDroneService droneService) {
		this.batteryCheckService = batteryCheckService;
		this.droneService = droneService;
	}

	@Scheduled(cron = "*/10 * * * * *")
    public void scheduleTaskDroneBatteryLevels() {
		Date date = new Date();
        logger.info("Starting Battery Levels check at - {}", dateTimeFormatter.format(LocalDateTime.now()));
        List<Drone> drones = droneService.getAllDrones();
        for (Drone drone : drones) {
        	BatteryCheckLog battTemp = new BatteryCheckLog(drone,date,drone.getBatteryCapacity());
        	batteryCheckService.createBatteryCheckLog(battTemp);
		}
        
    }
	
}
