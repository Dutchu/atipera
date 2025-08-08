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
        //given
        String username = "octocat";
        String url = "/find?name=" + username;

        //when
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).contains("Spoon-Knife");
        assertThat(response.getBody()).contains("master");
    }

    @Test
    public void whenInvalidUsernameIsPassed_thenShouldReturnNotFound() {
        //given
        String username = "this-user-does-not-exist-for=sure-9876543321";
        String url = "/find?name=" + username;

        //when
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).contains("\"status\":\"NOT_FOUND\"");
        assertThat(response.getBody()).containsAnyOf("User not found");
    }
}
