package com.atipera.repolister.services;

import com.atipera.repolister.clients.github.GitHubRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GithubService {
    List<GitHubRepo> getRepos(String username);
}
