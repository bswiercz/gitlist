# gitlist

### TL;DR
Fetch one's public github repositories, which are not forks, given username. For each repository show its owner and last commit SHA.

## Building and running

Build sources
```
$ gradle build
```

In project root directory, build app image
```
# docker build -t bswiercz/gitlist:latest .
```

Run app in container
```
# docker run -d --name gitlist-demo -p 8080:8080 bswiercz/gitlist:latest
```

Check if it's running
```
$ curl http://localhost:8080/users/torvalds/repos
```

## ToDo
- Detach HTTP io routines by adding abstraction for datasources (`RepositoryFetcher`, `BranchesFetcher`) and passing them as params to `RepositoryListFacade` so that we use full potential of hexagonal architecture pattern
