package com.example.app.SpringBootWebPhop.repository;

import com.example.app.SpringBootWebPhop.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends CrudRepository<Order, Long> {
}
