package com.musala.alvaro.testdrones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.musala.alvaro.testdrones.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
