package com.mendix.recipes.recipe;

import com.mendix.recipes.common.ResponseType;
import com.mendix.recipes.common.RestResponseFactory;
import com.mendix.recipes.recipe.dto.form.IngredientDivisionForm;
import com.mendix.recipes.recipe.dto.form.IngredientForm;
import com.mendix.recipes.recipe.dto.form.RecipeForm;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static com.mendix.recipes.common.JsonUtils.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {RecipeController.class, RestResponseFactory.class})
class RecipeControllerTest {

    @MockBean
    private RecipeService recipeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addRecipe_inValidFormData() throws Exception {

        final RecipeForm recipeForm = new RecipeForm();
        recipeForm.setTitle("New Recipe");
        recipeForm.setYield(-4);

        mockMvc
            .perform(
                post("/api/recipe")
                    .content(asJsonString(recipeForm))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            // .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("msgId").value("validationError"))
            .andExpect(jsonPath("type").value(ResponseType.ERROR.name()))
            .andExpect(jsonPath("errorCode").isNotEmpty())
            .andExpect(jsonPath("text").isNotEmpty())
            .andExpect(jsonPath("text", is(not("validationError"))))
            .andExpect(jsonPath("data").isEmpty());
    }

    @Test
    void addRecipe_inValidDataType() throws Exception {

        mockMvc
            .perform(
                post("/api/recipe")
                    .content("{\"title\": \"New Recipe\", \"yield\": \"Some Text\"}")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            // .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("msgId").value("badRequest"))
            .andExpect(jsonPath("type").value(ResponseType.ERROR.name()))
            .andExpect(jsonPath("errorCode").isNotEmpty())
            .andExpect(jsonPath("text").isNotEmpty())
            .andExpect(jsonPath("text", is(not("validationError"))))
            .andExpect(jsonPath("data").isNotEmpty());
    }

    @Test
    void addRecipe_inValidUuidType() throws Exception {

        mockMvc
            .perform(
                post("/api/recipe")
                    .content("{\"title\": \"New Recipe\", \"yield\": 4, \"categories\": [\"xxx\"]}")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            // .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("msgId").value("badRequest"))
            .andExpect(jsonPath("type").value(ResponseType.ERROR.name()))
            .andExpect(jsonPath("errorCode").isNotEmpty())
            .andExpect(jsonPath("text").isNotEmpty())
            .andExpect(jsonPath("text", is(not("validationError"))))
            .andExpect(jsonPath("data").isNotEmpty());
    }

    @Test
    void addRecipe_inValidNestedFormData() throws Exception {

        final IngredientDivisionForm divisionForm = new IngredientDivisionForm();
        divisionForm.setTitle("X".repeat(101));

        final RecipeForm recipeForm = new RecipeForm();
        recipeForm.setTitle("New Recipe");
        recipeForm.setYield(4);
        recipeForm.setCategories(List.of(UUID.fromString("2f44e5ec-7375-4bff-9409-698c536c84ba")));
        recipeForm.setIngredients(List.of(divisionForm));

        mockMvc
            .perform(
                post("/api/recipe")
                    .content(asJsonString(recipeForm))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            // .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("msgId").value("validationError"))
            .andExpect(jsonPath("type").value(ResponseType.ERROR.name()))
            .andExpect(jsonPath("errorCode").isNotEmpty())
            .andExpect(jsonPath("text").isNotEmpty())
            .andExpect(jsonPath("text", is(not("validationError"))))
            .andExpect(jsonPath("data").isEmpty());
    }

    @Test
    void addRecipe_succeeds() throws Exception {

        final UUID recipeUuid = UUID.fromString("1ad1be80-2ce4-4269-b2cd-1c834804bfca");

        Mockito.when(recipeService.add(Mockito.any(RecipeForm.class)))
            .thenReturn(recipeUuid);

        final RecipeInfoImpl recipeInfo = new RecipeInfoImpl();
        recipeInfo.setUuid(recipeUuid);

        Mockito.when(recipeService.get(Mockito.any(UUID.class)))
            .thenReturn(recipeInfo);

        final IngredientForm ingredient = new IngredientForm();
        ingredient.setQuantity("1/2");
        ingredient.setUnit("cup");
        ingredient.setContent("Water");

        final IngredientDivisionForm divisionForm = new IngredientDivisionForm();
        divisionForm.setTitle("MAIN");
        divisionForm.setItems(List.of(ingredient));

        final RecipeForm recipeForm = new RecipeForm();
        recipeForm.setTitle("New Recipe");
        recipeForm.setYield(4);
        recipeForm.setCategories(List.of(UUID.fromString("2f44e5ec-7375-4bff-9409-698c536c84ba")));
        recipeForm.setDirections(List.of(""));
        recipeForm.setIngredients(List.of(divisionForm));

        mockMvc
            .perform(
                post("/api/recipe")
                    .content(asJsonString(recipeForm))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            // .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("msgId").value("recipeAdded"))
            .andExpect(jsonPath("type").value(ResponseType.INFO.name()))
            .andExpect(jsonPath("errorCode").isEmpty())
            .andExpect(jsonPath("text").isNotEmpty())
            .andExpect(jsonPath("text", is(not("recipeAdded"))))
            .andExpect(jsonPath("data").isNotEmpty())
            .andExpect(jsonPath("data.uuid", is(recipeUuid.toString())));
    }

    @Test
    void getRecipe_invalidParameter() throws Exception {

        mockMvc
            .perform(
                get("/api/recipe")
                    .param("category", "xxx")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            // .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("msgId").value("validationError"))
            .andExpect(jsonPath("type").value(ResponseType.ERROR.name()))
            .andExpect(jsonPath("errorCode").isNotEmpty())
            .andExpect(jsonPath("text").isNotEmpty())
            .andExpect(jsonPath("text", is(not("validationError"))))
            .andExpect(jsonPath("data").isEmpty());
    }

    @Test
    void getRecipe_succeeds() throws Exception {

        final IngredientItemInfoImpl ingredient = new IngredientItemInfoImpl();
        ingredient.setQuantity("1/2");
        ingredient.setUnit("cup");
        ingredient.setContent("Water");

        final DivisionInfoImpl divisionInfo = new DivisionInfoImpl();
        divisionInfo.setTitle("MAIN");
        divisionInfo.setItems(List.of(ingredient));

        final DirectionStepInfoImpl stepInfo = new DirectionStepInfoImpl();
        stepInfo.setContent("Some directions");

        final CategoryInfoImpl categoryInfo = new CategoryInfoImpl();
        categoryInfo.setName("Soups");

        final RecipeInfoImpl recipeInfo = new RecipeInfoImpl();
        recipeInfo.setUuid(UUID.randomUUID());
        recipeInfo.setTitle("New Recipe");
        recipeInfo.setYield(4);
        recipeInfo.setIngredients(List.of(divisionInfo));
        recipeInfo.setDirections(List.of(stepInfo));
        recipeInfo.setCategories(Set.of(categoryInfo));

        Mockito.when(recipeService.list(Mockito.any(), Mockito.any()))
            .thenReturn((List.of(recipeInfo)));

        mockMvc
            .perform(
                get("/api/recipe")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            // .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("msgId").value("recipeListFetched"))
            .andExpect(jsonPath("type").value(ResponseType.INFO.name()))
            .andExpect(jsonPath("errorCode").isEmpty())
            .andExpect(jsonPath("text").isNotEmpty())
            .andExpect(jsonPath("text", is(not("recipeListFetched"))))
            .andExpect(jsonPath("data").isNotEmpty())
            .andExpect(jsonPath("data", hasSize(1)));
    }
}
