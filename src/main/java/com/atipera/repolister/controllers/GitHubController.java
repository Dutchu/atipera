package com.atipera.repolister.controllers;

import com.atipera.repolister.api.v1.model.GitHubRepo;
import com.atipera.repolister.services.GitHubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/find{name}")
    public ResponseEntity<List<GitHubRepo>> getRepos(@PathVariable("name") String name) {
        var result = gitHubService.getRepos(name);
        return ResponseEntity.ok(result);
    }
}
