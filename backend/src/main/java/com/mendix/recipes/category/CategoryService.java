package com.mendix.recipes.category;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class CategoryService {

    private final CategoryRepository categoryRepository;

    List<CategoryListItem> list() {

        return categoryRepository.list();
    }
}
