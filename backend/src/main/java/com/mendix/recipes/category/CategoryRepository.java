package com.mendix.recipes.category;

import com.mendix.recipes.domain.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c order by c.name")
    List<CategoryListItem> list();
}
