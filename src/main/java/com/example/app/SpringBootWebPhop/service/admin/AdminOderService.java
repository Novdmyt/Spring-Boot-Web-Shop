package com.example.app.SpringBootWebPhop.service.admin;

import com.example.app.SpringBootWebPhop.entity.Order;
import com.example.app.SpringBootWebPhop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class AdminOderService {
    @Autowired
    OrderRepository repository;

    public List<Order> getAll() {
        Iterable<Order> iterable = repository.findAll();
        List<Order> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(order -> new Order(order.getId(),
                                order.getOrderCode(),
                                order.getUserName(),
                                order.getUserPhone(),
                                order.getUserEmail(),
                                order.getContent(),
                                order.getStatus()))
                        .toList();
        return new ArrayList<>(list);
    }
}
