package com.example.app.SpringBootWebPhop.repository;

import com.example.app.SpringBootWebPhop.entity.Banana;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BananaRepository  extends CrudRepository <Banana, Long> {
}
