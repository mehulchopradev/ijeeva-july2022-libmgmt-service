package com.ijeeva.libmgmtservice.dao;


import com.ijeeva.libmgmtservice.dao.projections.book.TitlePrice;
import com.ijeeva.libmgmtservice.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
    // dynamic finder methods -- We just have to declare these methods (abstract)
    // following certain naming convention for the methods
    // find/count<<Entity class>>By<<attribute name capital case>><<operators>><<logical operator>>
    // <<attribute name capital case>><<operators>>

    List<Book> findBookByPagesGreaterThan(int pages);
    List<Book> findBookByPagesGreaterThanAndPriceLessThan(int pages, float price);
    // List<Book> findBookByTitleLike(String substring);
    List<Book> findBookByPriceLessThanOrderByTitleDesc(float price);

    // count Book where price greater than some passed value
    Long countBookByPriceGreaterThan(float price);

    // check whether a book exists with the title starting with Ruby -- boolean
    boolean existsBookByTitleStartingWith(String substring);

    // find title and price of all the books that have a specific word in their title
    List<TitlePrice> findBookByTitleLike(String substring);

    // named queries
    List<Book> findAllByTitleIncludes(String substring);
}
