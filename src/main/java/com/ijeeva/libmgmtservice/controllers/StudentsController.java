package com.ijeeva.libmgmtservice.controllers;

import com.ijeeva.libmgmtservice.dao.StudentRepository;
import com.ijeeva.libmgmtservice.entities.Student;
import com.ijeeva.libmgmtservice.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentsService studentsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@Valid @RequestBody Student student) {
        return this.studentsService.registerStudent(student);
    }

    @PostMapping("/{studentId}/books/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String issueBook(@PathVariable Long studentId, @PathVariable Long bookId) {
        this.studentsService.issueBook(studentId, bookId);
        return "Book Issued";
    }

    @PutMapping("/{studentId}/books/{bookId}")
    public String returnBook(@PathVariable Long studentId, @PathVariable Long bookId) {
        this.studentsService.returnBook(studentId, bookId);
        return "Book returned";
    }

    @PostMapping("/{fromStudentId}/books/{bookId}/students/{toStudentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String transferBook(
            @PathVariable Long fromStudentId,
            @PathVariable Long bookId,
            @PathVariable Long toStudentId) {
        this.studentsService.transferBook(fromStudentId, toStudentId, bookId);
        return "Book transferred";
    }

}
