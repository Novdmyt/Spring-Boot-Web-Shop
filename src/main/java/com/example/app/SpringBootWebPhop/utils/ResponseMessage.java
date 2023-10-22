package com.example.app.SpringBootWebPhop.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private boolean success;
    private String message;
}
