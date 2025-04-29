FROM node:23.11 AS build-frontend
WORKDIR /frontend
COPY frontend /frontend
RUN npm install && npm run build

FROM gradle:8.13
WORKDIR /backend
COPY backend /backend
COPY --from=build-frontend /frontend/dist/aas-business-card/browser /backend/src/main/resources/static
RUN ./gradlew build -x test
ENTRYPOINT [ "gradlew", "bootRun" ]
