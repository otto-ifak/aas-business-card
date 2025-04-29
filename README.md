[![Tests](https://github.com/otto-ifak/aas-business-card/actions/workflows/ci.yml/badge.svg)](https://github.com/otto-ifak/aas-business-card/actions/workflows/ci.yml)

# AAS Business Card

## Example Setup
Please refer to `compose.prod.yml` for an example.
Update `POSTGRES_PASSWORD` or provide an `.env` file.
Then you can start the server using:
```sh
docker compose -f compose.prod.yml up
```
Finally visit http://localhost:8080.

## Development

Start container:
```sh
docker compose up
```

Build Java Script:
```sh
docker compose exec frontend bash
npm run build
```
Alternatively run `npm run watch` for auto-rebuild

Start Server

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
