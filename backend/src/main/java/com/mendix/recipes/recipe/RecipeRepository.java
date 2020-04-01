package com.mendix.recipes.recipe;

import com.mendix.recipes.domain.Recipe;
import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface RecipeRepository extends JpaRepository<Recipe, Long> {

    <T extends RecipeInfo<?, ?, ?>> Optional<T> findByUuid(String uuid);

    @Query("" +
        "select distinct r " +
        "from Recipe r " +
        "left join r.categories c " +
        "left join r.directions s " +
        "left join r.ingredients d " +
        "left join d.items i " +
        "where" +
        "  (" +
        "    0 = :categoryCount" +
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
    <T extends RecipeInfo<?, ?, ?>> List<T> list(int categoryCount, List<String> category, String term);
}
