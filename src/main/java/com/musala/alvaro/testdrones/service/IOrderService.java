package com.musala.alvaro.testdrones.service;

import java.util.List;
import com.musala.alvaro.testdrones.model.Order;
import com.musala.alvaro.testdrones.model.enums.DroneState;

public interface IOrderService {

	List<Order> getAllOrders();
	Order getOrderById(long id);
	Order createOrder(Order order);
	Order updateOrder(long id, Order order);
	void deleteOrder(long id);
	Order findFirstByDroneIdAndDroneState (long droneId, DroneState state);
}
