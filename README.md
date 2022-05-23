# GithubX-API

> the API for [GithubX](http://github.com/riskers/github-plus-extension)

## Deploy

```bash
> docker-compose up -d
```

## Dev

```bash
> cp src/main/resources/application-dev.properties.sample src/main/resources/application-dev.properties
```

## Publish

### 1. package jar

```bash
> mvn -U clean package -P docker
```

### 2. build Docker image

```bash
> docker build -t githubx-api .
```

### 3. push Docker image

```bash
> docker tag githubx-api riskers/githubx-api
> docker push riskers/githubx-api
```

