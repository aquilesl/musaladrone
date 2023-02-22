package com.musala.alvaro.testdrones.business;

import java.util.List;
import com.musala.alvaro.testdrones.model.DTO.OrderDTO;

public interface IOrderBusiness {

	public List<OrderDTO> getAllOrders();
	public OrderDTO getOrderById(Long droneId);
	public void deleteOrder(Long droneId);
	
}
