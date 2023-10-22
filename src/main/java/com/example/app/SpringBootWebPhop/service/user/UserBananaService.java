package com.example.app.SpringBootWebPhop.service.user;

import com.example.app.SpringBootWebPhop.entity.Banana;
import com.example.app.SpringBootWebPhop.repository.BananaRepository;
import com.example.app.SpringBootWebPhop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserBananaService {
    @Autowired
    BananaRepository repository;
    public List<Banana> getAll() {
        Iterable<Banana> iterable = repository.findAll();
        List<Banana> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(banana -> new Banana(banana.getId(),
                                Constants.BASE_URL +
                                        Constants.URL_IMAGES_UPLOADS + banana.getImg(),
                                banana.getName(),
                                banana.getArticle(),
                                banana.getDescr(),
                                banana.getPrice()))
                        .toList();
        return new ArrayList<>(list);
    }
}
