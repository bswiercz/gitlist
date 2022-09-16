package io.bs.gitlist.repositorylist.domain;

import com.google.gson.Gson;
import io.bs.gitlist.repositorylist.dto.UserNotFoundException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RepositoryFetcher {
    private final HttpClient httpClient;
    private final Gson gson;
    private final Integer pageSize;

    public RepositoryFetcher(HttpClient httpClient, Integer pageSize) {
        this.httpClient = httpClient;
        this.gson = new Gson();
        this.pageSize = pageSize;
    }

    public List<Repository> fetchRepositories(String username) {
        List<Repository> result = new ArrayList<>();
        Integer page = 1;
        Repository[] repositories;
        try {
            do {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(getUriForRepositoryRequest(username, page))
                        .GET()
                        .build();


                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if( response.statusCode() == HttpStatus.NOT_FOUND.value() ) {
                    throw new UserNotFoundException(username);
                }

                repositories = gson.fromJson( response.body(), Repository[].class );
                result.addAll( Arrays.asList(repositories) );
                page++;
            } while( repositories.length >= pageSize );
            return result;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URI getUriForRepositoryRequest(String username, Integer page) throws URISyntaxException {
        return new URI("https://api.github.com/users/" +
                username +
                "/repos?page=" +
                page.toString() +
                "&per_page=" + pageSize.toString());
    }
}
