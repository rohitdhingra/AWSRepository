package com.example.demo;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Order;
import com.example.demo.repository.OrderDao;

@SpringBootApplication
public class SpringbootAwsLambdaApplication {

	@Autowired
	private OrderDao orderDao;

	@Bean
	public Supplier<List<Order>> orders() {
		return () -> orderDao.buildOrders();
	}

	@Bean
	public Function<String, List<Order>> findOrderByName() {
		return (input) -> orderDao.buildOrders().stream().filter(order -> order.getName().equals(input))
				.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsLambdaApplication.class, args);
	}

}
