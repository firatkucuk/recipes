package com.mendix.recipes.recipe;

import com.mendix.recipes.domain.IngredientItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientItemRepository extends JpaRepository<IngredientItem, Long> {

}
