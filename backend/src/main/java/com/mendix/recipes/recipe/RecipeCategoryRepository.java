package com.mendix.recipes.recipe;

import com.mendix.recipes.domain.Category;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface RecipeCategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.uuid in :categories")
    Set<Category> findMatchedCategories(List<String> categories);
}
