package com.example.app.SpringBootWebPhop.repository;

import com.example.app.SpringBootWebPhop.entity.Orange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrangeRepository extends CrudRepository <Orange, Long> {
}
