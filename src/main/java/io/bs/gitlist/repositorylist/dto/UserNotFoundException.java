package io.bs.gitlist.repositorylist.dto;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super("GitHub user " + username + " not found", null);
    }
}
