package io.bs.gitlist.repositorylist.domain;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BranchesFetcher {
    private final HttpClient httpClient;
    private final Gson gson;
    private final Integer pageSize;

    public BranchesFetcher(HttpClient httpClient, Integer pageSize) {
        this.httpClient = httpClient;
        this.gson = new Gson();
        this.pageSize = pageSize;
    }
    public List<Branch> fetchBranches(String username, String repository) {
        List<Branch> result = new ArrayList<>();
        Integer page = 1;
        Branch[] branches;
        try {
            do {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(getUri(username, repository, page))
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                branches = gson.fromJson( response.body(), Branch[].class );
                result.addAll( Arrays.asList(branches) );
                page++;
            } while( branches.length >= pageSize );
            return result;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URI getUri(String owner, String repository, Integer page) throws URISyntaxException {
        return new URI("https://api.github.com/repos/" +
                owner +
                "/" +
                repository +
                "/branches?page=" +
                page.toString() +
                "&per_page=" + pageSize.toString());
    }
}
