package io.bs.gitlist.repositorylist.domain;

import io.bs.gitlist.repositorylist.dto.BranchDto;

class Branch {
    private String name;
    private Commit commit;

    public BranchDto toDto() {
        return new BranchDto(name, commit.toDto());
    }
}
