package com.mendix.recipes.category;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void list() {

        final UUID uuid = UUID.fromString("2f44e5ec-7375-4bff-9409-698c536c84ba");

        Mockito.when(categoryRepository.list()).thenReturn(
            List.of(new CategoryListItem() {
                @Override
                public String getName() {
                    return "test";
                }

                @Override
                public UUID getUuid() {
                    return uuid;
                }
            })
        );

        final CategoryService        service = new CategoryService(categoryRepository);
        final List<CategoryListItem> list    = service.list();

        assertThat(list).hasSize(1);
        assertThat(list.get(0).getName()).isEqualTo("test");
        assertThat(list.get(0).getUuid()).isEqualTo(uuid);
    }
}
