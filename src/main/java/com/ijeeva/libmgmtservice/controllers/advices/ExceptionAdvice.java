package com.ijeeva.libmgmtservice.controllers.advices;

import com.ijeeva.libmgmtservice.entities.Student;
import com.ijeeva.libmgmtservice.exceptions.BookAlreadyIssuedException;
import com.ijeeva.libmgmtservice.exceptions.BookNotFoundException;
import com.ijeeva.libmgmtservice.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String f = ((FieldError)error).getField();
            String m = error.getDefaultMessage();
            map.put(f, m);
        });

        return map;
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleBookNotFoundException(BookNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleStudentNotFoundException(StudentNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BookAlreadyIssuedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleBookAlreadyIssuedException(BookAlreadyIssuedException e) {
        return e.getMessage();
    }
}
