package com.mendix.recipes.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "INGREDIENT_ITEM")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"division"})
@EqualsAndHashCode(exclude = {"division"})
public class IngredientItem {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID", nullable = false, unique = true, length = 36)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVISION_ID", referencedColumnName = "ID", nullable = false)
    private IngredientDivision division;

    @Column(name = "QUANTITY", length = 15)
    private String quantity;

    @Column(name = "UNIT", length = 50)
    private String unit;

    @Column(name = "CONTENT", nullable = false, length = 200)
    private String content;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
}
