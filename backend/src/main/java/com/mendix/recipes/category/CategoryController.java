package com.mendix.recipes.category;

import com.mendix.recipes.common.RestResponse;
import com.mendix.recipes.common.RestResponseFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
class CategoryController {

    private final CategoryService     categoryService;
    private final RestResponseFactory responseFactory;

    @GetMapping({"", "/"})
    RestResponse<List<CategoryListItem>> getCategories() {

        return responseFactory.info("categoryListFetched", categoryService.list());
    }
}
