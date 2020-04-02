package com.mendix.recipes.recipe;

import com.mendix.recipes.domain.Recipe;
import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @NonNull
    @Query("" +
        "select distinct r " +
        "from Recipe r " +
        "left join r.categories c " +
        "where" +
        "  c.uuid in :category"
    )
    <T extends RecipeInfo<?, ?, ?>> List<T> filterByCategories(@NonNull List<String> category);

    @NonNull
    @Query("" +
        "select distinct r " +
        "from Recipe r " +
        "left join r.categories c " +
        "left join r.directions s " +
        "left join r.ingredients d " +
        "left join d.items i " +
        "where" +
        "  lower(r.title) like lower(concat('%', :term, '%'))" +
        "  or" +
        "  lower(c.name) like lower(concat('%', :term, '%'))" +
        "  or" +
        "  lower(s.content) like lower(concat('%', :term, '%'))" +
        "  or" +
        "  lower(d.title) like lower(concat('%', :term, '%'))" +
        "  or" +
        "  lower(i.content) like lower(concat('%', :term, '%'))"
    )
    <T extends RecipeInfo<?, ?, ?>> List<T> filterByTerm(@NonNull String term);

    @NonNull
    @Query("" +
        "select distinct r " +
        "from Recipe r " +
        "left join r.categories c " +
        "left join r.directions s " +
        "left join r.ingredients d " +
        "left join d.items i " +
        "where" +
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
        "  )" +
        "  and" +
        "  c.uuid in :category"
    )
    <T extends RecipeInfo<?, ?, ?>> List<T> filterByTermAndCategories(@NonNull String term, @NonNull List<String> category);

    @NonNull
    <T extends RecipeInfo<?, ?, ?>> Optional<T> findByUuid(@Nullable String uuid);

    @NonNull
    @Query("select r from Recipe r order by r.title")
    <T extends RecipeInfo<?, ?, ?>> List<T> listAll();
}
