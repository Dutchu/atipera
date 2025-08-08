package com.atipera.repolister.api.v1.dto;

import com.atipera.repolister.clients.github.dto.GitHubBranchInfo;
import com.atipera.repolister.clients.github.dto.GitHubRepoOwner;

import java.util.List;

public record RepoResponse(String repositoryName, String ownerLogin, List<Branch> branches) {
}
