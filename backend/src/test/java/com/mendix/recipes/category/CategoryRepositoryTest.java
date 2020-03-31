package com.mendix.recipes.category;

import com.mendix.recipes.domain.Category;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findMatchedCategories() {

        Set<Category> categories = categoryRepository.findMatchedCategories(
            List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );

        assertThat(categories).isEmpty();

        categories = categoryRepository.findMatchedCategories(
            List.of(
                "2f44e5ec-7375-4bff-9409-698c536c84ba",
                UUID.randomUUID().toString(),
                "6d6da1d2-0f00-424e-9828-fcce3904f100"
            )
        );

        assertThat(categories).hasSize(2);
        final Iterator<Category> iterator = categories.iterator();
        assertThat(iterator.next().getName()).isEqualTo("Main dish");
        assertThat(iterator.next().getName()).isEqualTo("Vegetables");
    }

    @Test
    public void list() {

        assertThat(categoryRepository).isNotNull();
        assertThat(categoryRepository.list()).hasSize(7);
    }
}
