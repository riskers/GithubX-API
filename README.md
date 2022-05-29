# GithubX-API


[![](https://img.shields.io/docker/v/riskers/githubx-api/latest?label=Docker&style=for-the-badge)](https://hub.docker.com/r/riskers/githubx-api)

> the API for [GithubX](http://github.com/riskers/github-plus-extension)

## Deploy

```bash
> docker-compose up -d
```

## Dev

```bash
> cp src/main/resources/application-dev.properties.sample src/main/resources/application-dev.properties
```

## Publish Docker Image

The process had been integrated on [Github Actions](./.github/workflows/docker-publish.yml).

You can also publish the image manually:

1. package jar and build Docker image

    ```bash
    > docker build -t githubx-api .
    ```

2. push Docker image

    ```bash
    > docker tag githubx-api riskers/githubx-api
    > docker push riskers/githubx-api
    ```
