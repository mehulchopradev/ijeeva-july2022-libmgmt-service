package com.ijeeva.libmgmtservice;

import com.ijeeva.libmgmtservice.dao.BookRepository;
import com.ijeeva.libmgmtservice.dao.projections.book.TitlePrice;
import com.ijeeva.libmgmtservice.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableAsync
public class LibmgmtServiceApplication /* implements CommandLineRunner */ {

	/* @Autowired
	private BookRepository bookRepository; */

	public static void main(String[] args) {
		SpringApplication.run(LibmgmtServiceApplication.class, args);
	}

	/* @Override
	public void run(String... args) throws Exception {
		/* Book book = new Book("Prog in groovy", 900.67f, 560);
		book = this.bookRepository.save(book);
		System.out.println(book); */

		/* List<Book> books = Arrays.asList(
				new Book("Java in action", 1000f, 890),
				new Book("HTML/CSS", 900.67f, 500)
		);
		this.bookRepository.saveAll(books); */

		// count of books
		// System.out.println(this.bookRepository.count());

		// all the books
		// System.out.println(this.bookRepository.findAll());

		// find book by id
		/* this.bookRepository.findById(10l)
				.ifPresentOrElse(
						book -> System.out.println(book),
						() -> System.out.println("Book not found")); */
		/* Optional<Book> optional = this.bookRepository.findById(10l);
		if (optional.isPresent()) {
			System.out.println(optional.get());
		} else {
			System.out.println("book not found");
		} */

		// whether book with id exists or no
		// System.out.println(this.bookRepository.existsById(1l));

		// update a particular book in the system
		/* this.bookRepository.findById(2l)
				.ifPresentOrElse(
						book -> {
							book.setPages(900);
							this.bookRepository.save(book);
						},
						() -> System.out.println("No book found to update")
				); */

		// delete a particular book from the system
		// this.bookRepository.deleteById(2l);

		// System.out.println(this.bookRepository.findBookByPagesGreaterThan(500));

		// System.out.println(this.bookRepository.findBookByPagesGreaterThanAndPriceLessThan(500, 1000));
		// System.out.println(this.bookRepository.countBookByPriceGreaterThan(700));
		// System.out.println(this.bookRepository.findBookByTitleLike("%prog%"));
		// System.out.println(this.bookRepository.findBookByPriceLessThanOrderByTitleDesc(800));
		// System.out.println(this.bookRepository.existsBookByTitleStartingWith("python"));

		/* List<TitlePrice> books = this.bookRepository.findBookByTitleLike("%prog%");
		books.forEach(book -> {
			System.out.println(book.getTitle());
			System.out.println(book.getPrice());
		}); */

		/* List<Book> books = this.bookRepository.findAllByTitleIncludes("%prog%");
		System.out.println(books); */

		/* this.bookRepository.groupByPriceCountBooks().forEach(element -> {
			System.out.println(element[0]);
			System.out.println(element[1]);
		});
	} */
}
