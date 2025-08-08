package com.atipera.repolister.clients.github.dto;

import java.util.List;

public record GitHubRepoResponse(String name, GitHubRepoOwner owner, boolean fork, List<GitHubBranch> branches) {
}
