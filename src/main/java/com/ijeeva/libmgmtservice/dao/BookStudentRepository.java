package com.ijeeva.libmgmtservice.dao;

import com.ijeeva.libmgmtservice.entities.BookStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStudentRepository extends JpaRepository<BookStudent, Long> {
}
