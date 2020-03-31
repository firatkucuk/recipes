package com.mendix.recipes.category;

import com.mendix.recipes.common.ResponseType;
import com.mendix.recipes.common.RestResponse;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCategories() {

        final var response = restTemplate.getForEntity("/api/category", RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMsgId()).isEqualTo("categoryListFetched");
        assertThat(response.getBody().getType()).isEqualTo(ResponseType.INFO);
        assertThat(response.getBody().getErrorCode()).isNull();
        assertThat(response.getBody().getText()).isNotEmpty();
        assertThat(response.getBody().getText()).isNotEqualTo("categoryListFetched");
        assertThat(response.getBody().getData()).isNotNull();

        final var data = (List<Map<String, Object>>) response.getBody().getData();

        assertThat(data).hasSize(7);
        assertThat(data.get(0).get("name")).isEqualTo("Cake mixes");
    }
}
