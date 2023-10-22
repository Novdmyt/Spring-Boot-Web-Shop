package com.example.app.SpringBootWebPhop.service.user;

import com.example.app.SpringBootWebPhop.entity.Orange;
import com.example.app.SpringBootWebPhop.repository.OrangeRepository;
import com.example.app.SpringBootWebPhop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserOrangeService {
    @Autowired
    OrangeRepository repository;
    public List<Orange> getAll() {
        Iterable<Orange> iterable = repository.findAll();
        List<Orange> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(orange -> new Orange(orange.getId(),
                                Constants.BASE_URL +
                                        Constants.URL_IMAGES_UPLOADS + orange.getImg(),
                                orange.getName(),
                                orange.getArticle(),
                                orange.getDescr(),
                                orange.getPrice()))
                        .toList();
        return new ArrayList<>(list);
    }
}
