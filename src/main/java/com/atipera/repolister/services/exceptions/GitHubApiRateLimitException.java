package com.atipera.repolister.services.exceptions;

public class GitHubApiRateLimitException extends RuntimeException {
    public GitHubApiRateLimitException(String message) {
        super(message);
    }
}
