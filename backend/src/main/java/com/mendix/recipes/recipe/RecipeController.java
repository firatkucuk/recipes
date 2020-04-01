package com.mendix.recipes.recipe;

import com.mendix.recipes.common.RestResponse;
import com.mendix.recipes.common.RestResponseFactory;
import com.mendix.recipes.recipe.dto.form.RecipeForm;
import com.mendix.recipes.recipe.dto.info.RecipeInfo;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
class RecipeController {

    private final RecipeService       recipeService;
    private final RestResponseFactory responseFactory;

    @PostMapping({"", "/"})
    RestResponse<UUID> addRecipe(@RequestBody @Valid final RecipeForm form) {

        return responseFactory.info("recipeAdded", recipeService.add(form));
    }

    @GetMapping({"", "/"})
    RestResponse<List<RecipeInfo>> getRecipes(
        @RequestParam(required = false) List<String> category,
        @RequestParam(required = false) String term
    ) {

        return responseFactory.info("recipeListFetched", recipeService.list(category, term));
    }
}
