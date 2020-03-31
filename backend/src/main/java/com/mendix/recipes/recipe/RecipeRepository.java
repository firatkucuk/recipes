package com.mendix.recipes.recipe;

import com.mendix.recipes.domain.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("select r from Recipe r")
    List<RecipeInfo> list();
}
