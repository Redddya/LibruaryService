package com.example.library.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Year;

@Getter
@Setter
@ToString(exclude = {"id", "version"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "books")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    int id;
    @Version
    int version;
    @Column(name = "book_name")
    @NotEmpty(message = "Book name should not be empty")
    @Size(min = 1, max = 80, message = "Too short or too long book name")
    String bookName;
    @NotEmpty(message = "Book name should not be empty")
    @Size(min = 5, max = 60, message = "Too short or too long book name")
    @Pattern(regexp = "[A-Z][A-Za-z]+ [A-Z][A-Za-z]+", message = "Author name should be: Name Surname")
    String author;
    @NotEmpty(message = "Year should not be empty")
    @Pattern(regexp = "[1-2][089][0-9][0-9]", message = "Enter right year")
    String year;
    @ManyToOne
    @JoinColumn(name = "FK_Book_Person")
    Person person;
}
