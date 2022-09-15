package io.bs.gitlist.repositorylist.domain;

import io.bs.gitlist.repositorylist.dto.RepositoryDto;

import java.net.http.HttpClient;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryListFacade {

    private final RepositoryFetcher repositoryFetcher;
    private final BranchesFetcher branchesFetcher;
    private final HttpClient httpClient;

    public RepositoryListFacade(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.repositoryFetcher = new RepositoryFetcher(this.httpClient, 3);
        this.branchesFetcher = new BranchesFetcher(this.httpClient, 3);
    }

    public List<RepositoryDto> findRepositories(String username) {
        return this.repositoryFetcher.fetchRepositories(username)
                .parallelStream()
                .map(r -> r.toDto( branchesFetcher.fetchBranches(username, r.getName()) ))
                .collect(Collectors.toList());
    }
}
