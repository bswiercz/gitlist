package io.bs.gitlist.repositorylist.domain;

class Commit {
    private String sha;

    public String toDto() {
        return sha;
    }
}
