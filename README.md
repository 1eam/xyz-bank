
<h1 align="center">
  <br>
  XYZ BANK
  <br>
</h1>

<h4 align="center">banking service</h4>

<p align="center">
  <a href="#Installation instructions">Installation instructions</a> •
  <a href="#License">License</a> •
  <a href="#Additional information">Additional information</a>
</p>

## Installation instructions

Prerequisites: [Docker](https://docs.docker.com/engine/install/) 

Copy the following into a docker-compose.yml file and configure ports and environment as necessary

````yml
#This docker-compose file serves the purpose of running the xyz-bank backend API as a standalone service
version: '3'
services:
  xyz-bank:
    image: esther191299/xyz-bank
    container_name: xyz-bank
    depends_on:
      - postgres
    restart: always
    shm_size: 256mb
    ports:
      - "8080:8080"
    environment:
      xyz-bank-upload-directory: xyz-bank-upload-directory
  postgres:
    image: postgres
    container_name: postgres
    restart: always
    shm_size: 256mb
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin
      POSTGRES_DB: xyz-bank
      PGDATA: /data/postgres
    volumes:
      - postgres_db:/var/lib/postgresql/data
volumes:
      postgres_db:
````
run
```bash
$ docker-compose up -d
```

## Additional information
Database architecture - Entity relationships

![diagram.png](README%20sources%2Fdiagram.png)

## License

MIT