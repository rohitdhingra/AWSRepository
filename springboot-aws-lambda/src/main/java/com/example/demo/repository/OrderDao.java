package com.example.demo.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Order;

@Repository
public class OrderDao {

	public List<Order> buildOrders() {
		return Stream.of(new Order(1, "Mobile", 20000, 1), new Order(2, "Book", 999, 2), new Order(3, "Book", 466, 3),
				new Order(4, "Jeans", 1500, 1)).collect(Collectors.toList());
	}
}
