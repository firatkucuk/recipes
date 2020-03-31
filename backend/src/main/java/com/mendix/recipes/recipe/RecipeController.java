package com.mendix.recipes.recipe;

import com.mendix.recipes.RestResponseFactory;
import com.mendix.recipes.RestResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService       recipeService;
    private final RestResponseFactory responseFactory;

    @GetMapping({"", "/"})
    public RestResponse<List<RecipeInfo>> getRecipes() {

        return responseFactory.info("recipeListFetched", recipeService.list());
    }
}
