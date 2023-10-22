package com.example.app.SpringBootWebPhop.service.user;

import com.example.app.SpringBootWebPhop.entity.Apple;
import com.example.app.SpringBootWebPhop.repository.AppleRepository;
import com.example.app.SpringBootWebPhop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class UserAppleService {
    @Autowired
    AppleRepository repository;

    public List<Apple> getAll(){
        Iterable<Apple> iterable = repository.findAll();
        List<Apple> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(apple -> new Apple(apple.getId(),
                                Constants.BASE_URL +
                                        Constants.URL_IMAGES_UPLOADS + apple.getImg(),
                                apple.getName(),
                                apple.getArticle(),
                                apple.getDescr(),
                                apple.getPrice()))
                        .toList();
        return new ArrayList<>(list);
    }
}
