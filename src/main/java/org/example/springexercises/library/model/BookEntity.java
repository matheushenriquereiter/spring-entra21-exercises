package org.example.springexercises.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column
    private Integer publicationYear;

    @Column
    private Boolean available;

    public BookEntity(String title, String author, Integer publicationYear, Boolean available) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.available = available;
    }

    @PrePersist
    public void prePersist() {
        if (available == null) {
            available = false;
        }
    }
}
