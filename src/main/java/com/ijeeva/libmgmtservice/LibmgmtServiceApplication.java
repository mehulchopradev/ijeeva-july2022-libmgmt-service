package com.ijeeva.libmgmtservice;

import com.ijeeva.libmgmtservice.dao.BookRepository;
import com.ijeeva.libmgmtservice.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class LibmgmtServiceApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibmgmtServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/* Book book = new Book("Prog in groovy", 900.67f, 560);
		book = this.bookRepository.save(book);
		System.out.println(book); */

		/* List<Book> books = Arrays.asList(
				new Book("Scala programing", 1000f, 890),
				new Book("5 point someone", 670.45f, 230)
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
		this.bookRepository.deleteById(2l);
	}
}
