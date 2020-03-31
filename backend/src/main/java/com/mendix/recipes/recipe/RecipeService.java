package com.mendix.recipes.recipe;

import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<RecipeInfo> list(@Nullable List<String> categoryList, @Nullable final String term) {

        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }

        return recipeRepository.list(categoryList, term);
    }
}
