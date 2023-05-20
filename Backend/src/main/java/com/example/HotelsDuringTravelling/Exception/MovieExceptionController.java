package com.example.HotelsDuringTravelling.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class MovieExceptionController{


    @ExceptionHandler(value = MovieNotFoundException.class)
    public ResponseEntity<Object> exception(MovieNotFoundException exception) {
        return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }
}
