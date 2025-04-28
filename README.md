# AAS Business Card

## Start Containers
Start container:
```sh
docker compose up
```

## Build Java Script
```sh
docker compose exec frontend bash
npm run build
```
Alternatively run `npm run watch` for auto-rebuild

## Start Server

```sh
docker compose exec backend bash
./gradlew booRun
```

Then visit http://localhost:8080.

Alternatively, start server in continuous mode:
```sh
./gradlew build --continuous
```
Then in another terminal:
```sh
./gradlew bootRun
```
