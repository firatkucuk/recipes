package com.mendix.recipes.category;

import com.mendix.recipes.common.ResponseType;
import com.mendix.recipes.common.RestResponseFactory;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {CategoryController.class, RestResponseFactory.class})
public class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCategories() throws Exception {

        Mockito.when(categoryService.list()).thenReturn(
            List.of(new CategoryListItem() {
                @Override
                public String getName() {
                    return "test";
                }

                @Override
                public UUID getUuid() {
                    return UUID.fromString("2f44e5ec-7375-4bff-9409-698c536c84ba");
                }
            })
        );

        mockMvc
            .perform(
                get("/api/category")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            // .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("msgId").value("categoryListFetched"))
            .andExpect(jsonPath("type").value(ResponseType.INFO.name()))
            .andExpect(jsonPath("errorCode").isEmpty())
            .andExpect(jsonPath("text").isNotEmpty())
            .andExpect(jsonPath("text", is(not("categoryListFetched"))))
            .andExpect(jsonPath("data", notNullValue()))
            .andExpect(jsonPath("data", hasSize(1)))
            .andExpect(jsonPath("data[0].name").value("test"))
            .andExpect(jsonPath("data[0].uuid").value("2f44e5ec-7375-4bff-9409-698c536c84ba"));
    }
}
