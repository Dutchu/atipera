package com.atipera.repolister.clients.github.dto;

public record GitHubRepoInfo(String name, GitHubRepoOwner owner, Boolean fork) {
}
