package com.example.app.SpringBootWebPhop.repository;

import com.example.app.SpringBootWebPhop.entity.Apple;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppleRepository extends CrudRepository<Apple, Long> {
    List<Apple> findAll();
}
