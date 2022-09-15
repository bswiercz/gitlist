package io.bs.gitlist.repositorylist.domain;

import io.bs.gitlist.repositorylist.dto.RepositoryDto;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryListFacade {

    private final RepositoryListFetcher repositoryListFetcher;
    private final HttpClient httpClient;

    public RepositoryListFacade(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.repositoryListFetcher = new RepositoryListFetcher(this.httpClient);
    }

    public List<RepositoryDto> findRepositories(String username) {
        return this.repositoryListFetcher.fetch(username, 1, 1)
                .stream()
                .map(Repository::toDto)
                .collect(Collectors.toList());
    }
}
