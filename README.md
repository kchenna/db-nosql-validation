docker-compose rm -f
docker-compose pull
docker-compose up --build -d

docker rm -f $(docker ps -aq)

  db:
    build:
      context: ./db
      dockerfile: Dockerfile
    ports:
      - 5432:5432
    environment:
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
    volumes:
      - db-data:/var/lib/postgresql/data:delegated
