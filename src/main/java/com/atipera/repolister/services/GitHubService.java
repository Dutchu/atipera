package com.atipera.repolister.services;

import com.atipera.repolister.api.v1.dto.RepoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GithubService {
    List<RepoResponse> getRepos(String username);
}
