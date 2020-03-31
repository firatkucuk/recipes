package com.mendix.recipes.recipe;

import com.mendix.recipes.domain.Recipe;
import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("" +
        "select distinct r " +
        "from Recipe r " +
        "join r.categories c " +
        "join r.directions s " +
        "join r.ingredients d " +
        "join d.items i " +
        "where" +
        "  (" +
        "    0 = (select count(x) from Category x where x.uuid in :category)" +
        "    or" +
        "    c.uuid in :category" +
        "  )" +
        "  and" +
        "  (" +
        "    lower(r.title) like lower(concat('%', :term, '%'))" +
        "    or" +
        "    lower(c.name) like lower(concat('%', :term, '%'))" +
        "    or" +
        "    lower(s.content) like lower(concat('%', :term, '%'))" +
        "    or" +
        "    lower(d.title) like lower(concat('%', :term, '%'))" +
        "    or" +
        "    lower(i.content) like lower(concat('%', :term, '%'))" +
        "  )"
    )
    List<RecipeInfo> list(List<String> category, String term);
}
