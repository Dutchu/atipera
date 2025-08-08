package com.atipera.repolister.services;

import com.atipera.repolister.api.v1.dto.RepoResponse;
import com.atipera.repolister.clients.github.GitHubRestClient;
import com.atipera.repolister.clients.github.dto.GitHubBranchInfo;
import com.atipera.repolister.clients.github.dto.GitHubRepoInfo;
import com.atipera.repolister.services.mapper.RepoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubRestClientService implements GithubService {
    private final GitHubRestClient gitHubRestClient;

    public GitHubRestClientService(GitHubRestClient gitHubRestClient) {
        this.gitHubRestClient = gitHubRestClient;
    }

    public List<RepoResponse> getRepos(String username) {
        List<GitHubRepoInfo> repos;

        repos = gitHubRestClient.fetchRepoData(username);

        return repos.stream()
                .filter(repo -> !repo.fork())
                .map(this::mapRepoToResponse)
                .collect(Collectors.toList());
    }

    private RepoResponse mapRepoToResponse(GitHubRepoInfo repo) {
        List<GitHubBranchInfo> branches = gitHubRestClient.fetchBranchData(repo.owner().login(), repo.name());
        return RepoMapper.toRepoResponse(repo, branches);
    }
}
