package com.mendix.recipes.category;

import com.mendix.recipes.common.ResponseType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getCategories() {

        final var response = restTemplate.getForEntity("/api/category", CategoryListRestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMsgId()).isEqualTo("categoryListFetched");
        assertThat(response.getBody().getType()).isEqualTo(ResponseType.INFO);
        assertThat(response.getBody().getErrorCode()).isNull();
        assertThat(response.getBody().getText()).isNotEmpty();
        assertThat(response.getBody().getText()).isNotEqualTo("categoryListFetched");

        final List<CategoryListItemImpl> data = response.getBody().getData();

        assertThat(data).isNotNull();
        assertThat(data).hasSize(7);
        assertThat(data.get(0).getName()).isEqualTo("Cake mixes");
    }
}
