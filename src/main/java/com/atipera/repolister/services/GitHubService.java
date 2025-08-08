package com.atipera.repolister.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GithubService {
    List<GitHubRepoResponse> getRepos(String username);
}
