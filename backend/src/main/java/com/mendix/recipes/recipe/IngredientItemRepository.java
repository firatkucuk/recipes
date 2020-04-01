package com.mendix.recipes.recipe;

import com.mendix.recipes.domain.IngredientItem;
import org.springframework.data.jpa.repository.JpaRepository;

interface IngredientItemRepository extends JpaRepository<IngredientItem, Long> {

}
