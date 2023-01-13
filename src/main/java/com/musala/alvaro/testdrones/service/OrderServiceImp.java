package com.musala.alvaro.testdrones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.alvaro.testdrones.model.Order;
import com.musala.alvaro.testdrones.model.enums.DroneState;
import com.musala.alvaro.testdrones.repository.OrderRepository;

@Service
public class OrderServiceImp implements IOrderService {

	private OrderRepository orderRepo;
	
	@Autowired
	public OrderServiceImp(OrderRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public Order getOrderById(long id) {
		return orderRepo.getReferenceById(id);
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepo.save(order);
	}

	@Override
	public Order updateOrder(long id, Order newOrder) {
		Order oldOrder = orderRepo.getReferenceById(id);
		oldOrder.setDrone(newOrder.getDrone());
		oldOrder.setMedications(newOrder.getMedications());
		return orderRepo.save(oldOrder);
	}

	@Override
	public void deleteOrder(long id) {
		orderRepo.deleteById(id);
	}

	@Override
	public Order findFirstByDroneIdAndDroneState(long droneId, DroneState state) {
		return orderRepo.findFirstByDroneIdAndDroneState(droneId, state);
	}

}
