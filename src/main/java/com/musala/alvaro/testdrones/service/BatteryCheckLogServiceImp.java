package com.musala.alvaro.testdrones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musala.alvaro.testdrones.model.BatteryCheckLog;
import com.musala.alvaro.testdrones.repository.BatteryCheckLogRepository;

@Service
public class BatteryCheckLogServiceImp implements IBatteryCheckLogService {

	private final BatteryCheckLogRepository battRepo;
	
	@Autowired
	public BatteryCheckLogServiceImp(BatteryCheckLogRepository battRepo) {
		this.battRepo = battRepo;
	}

	@Override
	public List<BatteryCheckLog> getAllBatteryCheckLog() {
		return battRepo.findAll();
	}

	@Override
	public BatteryCheckLog getBatteryCheckLogById(long id) {
		return battRepo.getReferenceById(id);
	}

	@Override
	public BatteryCheckLog createBatteryCheckLog(BatteryCheckLog batteryCheck) {
		return battRepo.save(batteryCheck);
	}

	@Override
	public BatteryCheckLog updateBatteryCheckLog(long id, BatteryCheckLog newBatteryCheck) {
		BatteryCheckLog oldBatteryCheck = battRepo.getReferenceById(id);
		oldBatteryCheck.setBatteryLevel(newBatteryCheck.getBatteryLevel());
		oldBatteryCheck.setCheckTime(newBatteryCheck.getCheckTime());
		oldBatteryCheck.setDrone(newBatteryCheck.getDrone());
		return battRepo.save(oldBatteryCheck);
	}

	@Override
	public void deleteBatteryCheckLog(long id) {
		battRepo.deleteById(id);
	}

}
