package com.ijeeva.libmgmtservice.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(
                name = "Book.findAllByTitleIncludes",
                query = "from Book b where b.title like :substring"
        )
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private Float price;

    @Column(nullable = false)
    private Integer pages;

    @OneToMany(mappedBy = "book")
    private Set<BookStudent> studentsIssued;

    public Book() {}

    public Book(String title, Float price, Integer pages) {
        this.title = title;
        this.price = price;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Set<BookStudent> getStudentsIssued() {
        return studentsIssued;
    }

    public void setStudentsIssued(Set<BookStudent> studentsIssued) {
        this.studentsIssued = studentsIssued;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                '}';
    }
}
