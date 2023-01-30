package com.musala.alvaro.testdrones.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.musala.alvaro.testdrones.model.DTO.OrderDTO;
import com.musala.alvaro.testdrones.service.IOrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private ModelMapper modelMapper;
	private IOrderService orderService;

	@Autowired
	public OrderController(ModelMapper modelMapper, IOrderService orderService) {
		this.modelMapper = modelMapper;
		this.orderService = orderService;
	}

	@GetMapping("/")
	public ResponseEntity<List<OrderDTO>> getAllOrders() {

			List<OrderDTO> orders =  orderService.getAllOrders().stream().map(order -> modelMapper.map(order, OrderDTO.class))
					.collect(Collectors.toList());

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") long id) {

        return new ResponseEntity<>(modelMapper.map(orderService.getOrderById(id), OrderDTO.class), HttpStatus.OK);

    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") long id) {

        	orderService.deleteOrder(id);
            return new ResponseEntity<>("Order deleted", HttpStatus.OK);

    }
	
}
