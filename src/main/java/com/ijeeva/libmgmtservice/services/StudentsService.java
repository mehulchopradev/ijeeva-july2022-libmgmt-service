package com.ijeeva.libmgmtservice.services;

import com.ijeeva.libmgmtservice.dao.BookRepository;
import com.ijeeva.libmgmtservice.dao.BookStudentRepository;
import com.ijeeva.libmgmtservice.dao.StudentRepository;
import com.ijeeva.libmgmtservice.entities.Book;
import com.ijeeva.libmgmtservice.entities.BookStudent;
import com.ijeeva.libmgmtservice.entities.Student;
import com.ijeeva.libmgmtservice.exceptions.BookAlreadyIssuedException;
import com.ijeeva.libmgmtservice.exceptions.BookNotFoundException;
import com.ijeeva.libmgmtservice.exceptions.BookNotIssuedException;
import com.ijeeva.libmgmtservice.exceptions.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Calendar;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookStudentRepository bookStudentRepository;

    @Autowired
    private SendEmailService sendEmailService;

    public Student registerStudent(Student student) {
        Student newStudent = this.studentRepository.save(student);
        this.sendEmailService.sendEmail("mehulc@hey.com");
        return newStudent;
    }

    public void issueBook(Long studentId, Long bookId) {
        Optional<Student> optional = this.studentRepository.findById(studentId);
        if (optional.isEmpty()) {
            throw new StudentNotFoundException("Student not found");
        }
        Student student = optional.get();

        Optional<Book> optional1 = this.bookRepository.findById(bookId);
        if (optional1.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }
        Book book = optional1.get();

        Set<BookStudent> bookIssued = student.getBooksIssued();
        for (BookStudent bs : bookIssued) {
            if (bs.getBook().getId() == book.getId() && bs.getReturnDate() == null) {
                throw new BookAlreadyIssuedException("book already issued to student");
            }
        }

        // create a new BookStudent entity and save it
        BookStudent bookStudent = new BookStudent(book, student, Calendar.getInstance().getTime());
        this.bookStudentRepository.save(bookStudent);
    }

    public void returnBook(Long studentId, Long bookId) {
        Optional<Student> optional = this.studentRepository.findById(studentId);
        if (optional.isEmpty()) {
            throw new StudentNotFoundException("Student not found");
        }
        Student student = optional.get();

        Optional<Book> optional1 = this.bookRepository.findById(bookId);
        if (optional1.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }
        Book book = optional1.get();

        Set<BookStudent> bookIssued = student.getBooksIssued();
        boolean isBookIssued = false;
        for (BookStudent bs : bookIssued) {
            if (bs.getBook().getId() == book.getId() && bs.getReturnDate() == null) {
                isBookIssued = true;
                bs.setReturnDate(Calendar.getInstance().getTime());
                this.bookStudentRepository.save(bs);
                break;
            }
        }

        if (!isBookIssued) {
            throw new BookNotIssuedException("Book was not issued to the student");
        }
    }

    @Transactional
    public void transferBook(Long fromStudentId, Long toStudentId, Long bookId) {
        Student from = this.studentRepository.findById(fromStudentId)
                .orElseThrow(() -> new StudentNotFoundException("From student not found"));
        Student to = this.studentRepository.findById(toStudentId)
                .orElseThrow(() -> new StudentNotFoundException("To student not found"));
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book to transfer not found"));

        BookStudent alreadyIssuedBookStudent = null;
        Set<BookStudent> booksIssued = from.getBooksIssued();
        for (BookStudent bs : booksIssued) {
            if (bs.getBook().getId() == book.getId() && bs.getReturnDate() == null) {
                alreadyIssuedBookStudent = bs;
                break;
            }
        }
        if (alreadyIssuedBookStudent == null) {
            // throw an exception
            throw new RuntimeException("Cannot transfer book");
        }

        // Transfer book
        BookStudent newBookStudent = new BookStudent(book, to, Calendar.getInstance().getTime());
        this.bookStudentRepository.save(newBookStudent);

        // some exception happens
        // String msg = null;
        // msg.toLowerCase(); // deliberately create a Nullpointer exception

        alreadyIssuedBookStudent.setReturnDate(Calendar.getInstance().getTime());
        this.bookStudentRepository.save(alreadyIssuedBookStudent);
    }
}
