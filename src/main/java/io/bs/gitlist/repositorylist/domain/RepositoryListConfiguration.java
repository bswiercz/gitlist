package io.bs.gitlist.repositorylist.domain;

import io.bs.gitlist.repositorylist.domain.RepositoryListFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
class RepositoryListConfiguration {
    @Bean
    RepositoryListFacade repositoryListFacade() {
        return new RepositoryListFacade(HttpClient.newHttpClient());
    }
}
