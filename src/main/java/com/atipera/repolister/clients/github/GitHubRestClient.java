package com.atipera.repolister.clients.github;

import com.atipera.repolister.clients.github.dto.GitHubBranchInfo;
import com.atipera.repolister.clients.github.dto.GitHubRepoInfo;
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


    public List<GitHubRepoInfo> fetchRepoData(String username) {
        return restClient.get()
                .uri("/users/{user}/repos", username)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public List<GitHubBranchInfo> fetchBranchData(String owner, String repoName) {
        return restClient.get()
                .uri("/repos/{owner}/{repo}/branches", owner, repoName)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
