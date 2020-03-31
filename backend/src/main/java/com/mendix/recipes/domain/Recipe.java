package com.mendix.recipes.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "RECIPE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"ingredients", "directions", "categories"})
@EqualsAndHashCode(exclude = {"ingredients", "directions", "categories"})
public class Recipe {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID", nullable = false, unique = true, length = 36)
    private String uuid;

    @Column(name = "TITLE", nullable = false, unique = true, length = 150)
    private String title;

    @Column(name = "YIELD", nullable = false)
    private int yield;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "recipe", cascade = {CascadeType.ALL})
    private List<IngredientDivision> ingredients = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "recipe", cascade = {CascadeType.ALL})
    private List<DirectionStep> directions = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "RECIPE_CATEGORY",
        joinColumns = {@JoinColumn(name = "RECIPE_ID")},
        inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")}
    )
    private Set<Category> categories = new HashSet<>();
}
