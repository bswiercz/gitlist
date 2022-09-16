package io.bs.gitlist.repositorylist.domain;

import io.bs.gitlist.repositorylist.dto.RepositoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class Repository {
    @Getter
    private String name;
    private Owner owner;
    @Getter
    private Boolean fork;

    public RepositoryDto toDto(List<Branch> branch) {
        return new RepositoryDto(name, owner.toDto(),
                branch.stream()
                .map(Branch::toDto)
                .collect(Collectors.toList())
        );
    }
}
