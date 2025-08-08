package com.atipera.repolister.services;

import com.atipera.repolister.clients.github.GitHubRepo;
import com.atipera.repolister.clients.GitHubRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitHubRestClientService implements GithubService {
    private final GitHubRestClient gitHubRestClient;

    public GitHubRestClientService(GitHubRestClient gitHubRestClient) {
        this.gitHubRestClient = gitHubRestClient;
    }

    public List<GitHubRepo> getRepos(String username) {
        List<GitHubRepo> data;
        // When this line is executed inside a virtual thread,
        // it efficiently "parks" instead of blocking a real OS thread.
        data = gitHubRestClient.fetchData(username);

        return data;
    }
}
