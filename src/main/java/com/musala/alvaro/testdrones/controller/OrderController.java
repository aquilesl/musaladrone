package com.musala.alvaro.testdrones.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.alvaro.testdrones.business.IOrderBusiness;
import com.musala.alvaro.testdrones.model.DTO.OrderDTO;
import com.musala.alvaro.testdrones.service.IOrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {

	private IOrderBusiness orderBusiness;

	@Autowired
	public OrderController(IOrderBusiness orderBusiness) {
		this.orderBusiness = orderBusiness;
	}

	@GetMapping("/")
	public ResponseEntity<List<OrderDTO>> getAllOrders() {

			List<OrderDTO> orders =  orderBusiness.getAllOrders();
            return new ResponseEntity<>(orders, HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") long id) {

        return new ResponseEntity<>(orderBusiness.getOrderById(id), HttpStatus.OK);

    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") long id) {

        	orderBusiness.deleteOrder(id);
            return new ResponseEntity<>("Order deleted", HttpStatus.OK);

    }
	
}
