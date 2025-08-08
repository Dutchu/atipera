package com.atipera.repolister.services;

import com.atipera.repolister.clients.github.GitHubRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitHubRestClientService implements GithubService {
    private final GitHubRestClient gitHubRestClient;

    public GitHubRestClientService(GitHubRestClient gitHubRestClient) {
        this.gitHubRestClient = gitHubRestClient;
    }

    public List<GitHubRepoResponse> getRepos(String username) {
        List<GitHubRepoResponse> data;
        // When this line is executed inside a virtual thread,
        // it efficiently "parks" instead of blocking a real OS thread.
        data = gitHubRestClient.fetchData(username);

        return data;
    }
}
