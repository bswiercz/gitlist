package io.bs.gitlist.repositorylist.domain;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class RepositoryListFetcher {
    private final HttpClient httpClient;

    public RepositoryListFetcher(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String fetch(String username, Integer page, Integer pageSize) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri( getUriForRequest(username, page, pageSize) )
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
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
