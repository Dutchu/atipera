package com.atipera.repolister.clients.github;

public record GitHubRepo(String name, String htmlUrl, boolean fork, GitHubRepoOwner owner) {
}
