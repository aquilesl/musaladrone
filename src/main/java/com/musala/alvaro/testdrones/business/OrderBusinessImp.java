package com.musala.alvaro.testdrones.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.musala.alvaro.testdrones.model.DTO.OrderDTO;
import com.musala.alvaro.testdrones.service.IOrderService;

@Service
public class OrderBusinessImp implements IOrderBusiness{
	
	private ModelMapper modelMapper;
	private IOrderService orderService;

	public OrderBusinessImp(ModelMapper modelMapper, IOrderService orderService) {
		this.modelMapper = modelMapper;
		this.orderService = orderService;
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		List<OrderDTO> orders =  orderService.getAllOrders().stream().map(order -> modelMapper.map(order, OrderDTO.class))
				.collect(Collectors.toList());

        if (orders.isEmpty()) {
            return new ArrayList<OrderDTO>();
        }
        return orders;
	}

	@Override
	public OrderDTO getOrderById(Long droneId) {
		return modelMapper.map(orderService.getOrderById(droneId), OrderDTO.class);
	}

	@Override
	public void deleteOrder(Long droneId) {
		orderService.deleteOrder(droneId);
	}

}
