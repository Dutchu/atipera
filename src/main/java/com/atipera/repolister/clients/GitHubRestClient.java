package com.atipera.repolister.clients;

import com.atipera.repolister.clients.github.GitHubRepo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class GitHubRestClient {

    private final RestClient restClient;

    public GitHubRestClient(RestClient restClient) {
        this.restClient = restClient;
    }


    public List<GitHubRepo> fetchData(String username) {
        return restClient.get()
                .uri("/users/{user}/repos", username)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
