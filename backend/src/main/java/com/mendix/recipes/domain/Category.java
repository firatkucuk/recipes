package com.mendix.recipes.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "CATEGORY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"recipes"})
@EqualsAndHashCode(exclude = {"recipes"})
public class Category {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID", nullable = false, unique = true, length = 36)
    private String uuid;

    @Column(name = "NAME", nullable = false, unique = true, length = 50)
    private String name;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "categories", cascade = CascadeType.MERGE)
    private Set<Recipe> recipes = new HashSet<>();
}
