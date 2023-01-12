package com.musala.alvaro.testdrones.service;

import java.util.List;
import com.musala.alvaro.testdrones.model.BatteryCheckLog;

public interface IBatteryCheckLogService {
	
	List<BatteryCheckLog> getAllBatteryCheckLog();
	BatteryCheckLog getBatteryCheckLogById(long id);
	BatteryCheckLog createBatteryCheckLog(BatteryCheckLog batteryCheck);
	BatteryCheckLog updateBatteryCheckLog(long id, BatteryCheckLog batteryCheck);
	void deleteBatteryCheckLog(long id);

}
