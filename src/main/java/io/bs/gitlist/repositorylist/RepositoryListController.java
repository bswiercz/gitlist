package io.bs.gitlist.repositorylist;

import io.bs.gitlist.repositorylist.domain.RepositoryListFacade;
import io.bs.gitlist.repositorylist.dto.RepositoryDto;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
class RepositoryListController {

    private final RepositoryListFacade repositoryListFacade;

    public RepositoryListController(RepositoryListFacade repositoryListFacade) {
        this.repositoryListFacade = repositoryListFacade;
    }

    @GetMapping("users/{username}/repos")
    ResponseEntity<String> getRepositoriesForUsername(@PathVariable String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>( this.repositoryListFacade.findRepositories(username), headers, HttpStatus.OK);
    }

}
