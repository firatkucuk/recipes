package com.mendix.recipes.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "INGREDIENT_DIVISION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"recipe", "items"})
@EqualsAndHashCode(exclude = {"recipe", "items"})
public class IngredientDivision {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID", nullable = false, unique = true, length = 36)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_ID", referencedColumnName = "ID", nullable = false)
    private Recipe recipe;

    @Column(name = "TITLE", length = 100)
    private String title;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "division", cascade = {CascadeType.ALL})
    private List<IngredientItem> items = new ArrayList<>();
}
