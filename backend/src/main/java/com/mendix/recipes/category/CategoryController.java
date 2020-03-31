package com.mendix.recipes.category;

import com.mendix.recipes.RestResponseFactory;
import com.mendix.recipes.dto.RestResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService     categoryService;
    private final RestResponseFactory responseFactory;

    @GetMapping({"", "/"})
    public RestResponse<List<CategoryListItem>> getCategories() {

        return responseFactory.info("categoryListFetched", categoryService.list());
    }
}
