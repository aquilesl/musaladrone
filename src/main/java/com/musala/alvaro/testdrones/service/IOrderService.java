package com.musala.alvaro.testdrones.service;

import java.util.List;
import com.musala.alvaro.testdrones.model.Order;

public interface IOrderService {

	List<Order> getAllOrders();
	Order getOrderById(long id);
	Order createOrder(Order order);
	Order updateOrder(long id, Order order);
	void deleteOrder(long id);
	
}
