package com.atipera.repolister.services.mapper;

import com.atipera.repolister.api.v1.dto.Branch;
import com.atipera.repolister.api.v1.dto.RepoResponse;
import com.atipera.repolister.clients.github.dto.GitHubBranchInfo;
import com.atipera.repolister.clients.github.dto.GitHubRepoInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepoMapper {
    public static RepoResponse toRepoResponse(GitHubRepoInfo repo, List<GitHubBranchInfo> branches) {
        return new RepoResponse(
                repo.name(),
                repo.owner().login(),
                toBranchList(branches)
        );
    }

    private static List<Branch> toBranchList(List<GitHubBranchInfo> branchInfos) {
        return branchInfos.stream()
                .map(branchInfo -> new Branch(branchInfo.name(), branchInfo.commit().sha()))
                .collect(Collectors.toList());
    }
}
