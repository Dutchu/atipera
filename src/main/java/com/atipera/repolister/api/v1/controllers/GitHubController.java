package com.atipera.repolister.api.v1.controllers;

import com.atipera.repolister.clients.github.GitHubRepo;
import com.atipera.repolister.services.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GitHubController {

    private final GithubService githubService;

    public GitHubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<GitHubRepo>> getRepos(@RequestParam String name) {
        var result = githubService.getRepos(name);
        return ResponseEntity.ok(result);
    }
}
