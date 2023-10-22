package com.example.app.SpringBootWebPhop.service.admin;

import com.example.app.SpringBootWebPhop.entity.Apple;
import com.example.app.SpringBootWebPhop.exceptions.UseOrderException;
import com.example.app.SpringBootWebPhop.repository.AppleRepository;
import com.example.app.SpringBootWebPhop.utils.Constants;
import com.example.app.SpringBootWebPhop.utils.ResponseMessage;
import com.example.app.SpringBootWebPhop.utils.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

public class AdminAppleService {
    private static final Logger LOGGER =
            Logger.getLogger(AdminAppleService.class.getName());
    @Autowired
    AppleRepository repository;
    public ResponseEntity<?> addApple(String[] data, MultipartFile file) throws IOException {
        Map<String, String> errors = validateData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UseOrderException("Check inputs", errors);
            } catch (UseOrderException e) {
                return new ResponseEntity<>(new ResponseMessage(false,
                        e.getErrors(errors)), HttpStatus.OK);
            }
        } else {
            String imgPrefix = StringGenerator.genStr();
            Apple apple = new Apple();
            apple.setName(data[0]);
            apple.setArticle(data[1]);
            apple.setDescr(data[2]);
            apple.setPrice(Double.parseDouble(data[3]));
            String fileUpload = imgPrefix + "-" + data[4];

            String directory = Constants.URL_FILE_UPLOADS;
            String filepath = Paths.get(directory, fileUpload).toString();
            // Check data in logs
            LOGGER.info("Admin filepath: " + filepath);
            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(filepath));
            stream.write(file.getBytes());
            stream.close();

            apple.setImg(fileUpload);
            repository.save(apple);

            return new ResponseEntity<>(new ResponseMessage(true,
                    Constants.SAVED_MSG), HttpStatus.OK);
        }
    }

    public List<Apple> getAll() {
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

    private Map<String, String> validateData(String[] data) {
        Map<String, String> errors = new HashMap<>();
        if (data[0].isEmpty() | data[0].equals(" "))
            errors.put("name", Constants.INPUT_REQ_MSG);
        if (data[1].isEmpty() | data[1].equals(" "))
            errors.put("article", Constants.INPUT_REQ_MSG);
        if (data[2].isEmpty() | data[2].equals(" "))
            errors.put("description", Constants.INPUT_REQ_MSG);
        if (data[3].isEmpty() | data[3].equals(" "))
            errors.put("price", Constants.INPUT_REQ_MSG);
        if (data[4].isEmpty() | data[3].equals(" "))
            errors.put("file", Constants.ADD_FILE_MSG);
        return errors;
    }
}