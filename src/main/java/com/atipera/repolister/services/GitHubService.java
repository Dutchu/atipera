package com.atipera.repolister.services;

import com.atipera.repolister.api.v1.model.GitHubRepo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GitHubService {
    private final RestClient restClient;

    public GitHubService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<GitHubRepo> getRepos(String username) {
        // When this line is executed inside a virtual thread,
        // it efficiently "parks" instead of blocking a real OS thread.
        return restClient.get()
                .uri("/users/{user}/repos", username)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
