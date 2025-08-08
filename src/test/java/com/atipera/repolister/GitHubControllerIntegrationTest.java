package com.atipera.repolister;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitHubControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenValidUsernameIsPassed_thenShouldReturnOkAndRepositoryData() {
        String username = "octocat";
        String url = "/api/v1/repolister/" + username;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    public void whenInvalidUsernameIsPassed_thenShouldReturnNotFound() {
        String username = "this-user-does-not-exist-for=sure-9876543321";
        String url = "/api/v1/repolister/" + username;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    }
}
