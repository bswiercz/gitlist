package io.bs.gitlist.repositorylist.domain;

class Owner {
    public Owner(String login) {
        this.login = login;
    }

    private final String login;

    public String toDto() {
        return this.login;
    }
}
