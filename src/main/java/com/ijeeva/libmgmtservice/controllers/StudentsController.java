package com.ijeeva.libmgmtservice.controllers;

import com.ijeeva.libmgmtservice.dao.StudentRepository;
import com.ijeeva.libmgmtservice.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return this.studentRepository.save(student);
    }
}
