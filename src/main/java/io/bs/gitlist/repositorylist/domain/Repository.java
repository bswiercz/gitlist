package io.bs.gitlist.repositorylist.domain;

import io.bs.gitlist.repositorylist.dto.RepositoryDto;

import java.util.ArrayList;

class Repository {
    private String name;
    private Owner owner;

    public Repository(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
    }

    public RepositoryDto toDto() {
        return new RepositoryDto(name, owner.toDto(), new ArrayList<>());
    }
}
