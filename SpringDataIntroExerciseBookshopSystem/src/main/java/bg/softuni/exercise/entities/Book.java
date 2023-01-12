package bg.softuni.exercise.entities;

import bg.softuni.exercise.enums.AgeRestriction;
import bg.softuni.exercise.enums.EditionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "edition_type")
    @Enumerated(EnumType.STRING)
    private EditionType editionType;

    @Column
    private BigDecimal price;

    @Column
    private int copies;

    @Column(name="release_date")
    private LocalDate releaseDate;

    @Column(name="age_restriction")
    @Enumerated(EnumType.STRING)
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;

    @ManyToMany
    private Set<Category> categories;

    public Book(String title, EditionType editionType, BigDecimal price, LocalDate releaseDate, AgeRestriction ageRestriction, Author author, Set<Category> categories, int copies) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
        this.copies = copies;
    }
}
