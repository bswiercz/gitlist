package io.bs.gitlist.repositorylist.domain;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

class RepositoryListFetcher {
    private final HttpClient httpClient;

    public RepositoryListFetcher(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<Repository> fetch(String username, Integer page, Integer pageSize) {

        Gson gson = new Gson();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri( getUriForRequest(username, page, pageSize) )
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Repository[] repositories = gson.fromJson( response.body(), Repository[].class);

            return Arrays.asList(repositories);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private URI getUriForRequest(String username, Integer page, Integer pageSize) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.github.com/users/");
        sb.append(username);
        sb.append("/repos?page=");
        sb.append(page.toString());
        sb.append("&per_page=");
        sb.append(pageSize.toString());
        return new URI( sb.toString() );
    }
}
