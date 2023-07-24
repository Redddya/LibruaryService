package com.example.library.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@ToString(exclude = {"id", "version", "books"})
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "people")
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    int id;
    @Version
    int version;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 40, message = "Too short or too long name")
    @Column(name = "first_name")
    String firstName;
    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 40, message = "Too short or too long surname")
    @Column(name = "last_name")
    String lastName;
    @Column(name = "date_of_birth")
    @Pattern(regexp = "[1-2][089][0-9][0-9]-[01][0-9]-[0-3][0-9]",
            message = "You entered wrong birthdate")
    String dateOfBirth;
    @OneToMany(mappedBy = "person", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Book> books;
}
