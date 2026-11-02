# Working on this repo (Base44 sandbox)

## What it is
Spring Boot (Java 21) monolith: REST controllers under `/src/main/java/com/npo/management`,
and a static vanilla HTML/CSS/JS frontend in `src/main/resources/static` served by the
same Tomcat from one origin (all `fetch()` calls are relative paths). MongoDB is the only
backend dependency; there is no external service or secret.

## Run it
```bash
docker compose -f docker-compose.base44.yml up -d --build
docker compose -f docker-compose.base44.yml logs -f app
```
* Host port **3000** → app port 8080. MongoDB runs as the `mongo` service; the app connects
  with `SPRING_MONGODB_URI=mongodb://mongo:27017/npo` (the repo's `application.properties`
  default points at `localhost:27017`, which does not exist in the container).
* No language runtime exists on the sandbox host — run everything through the container:
  `docker compose -f docker-compose.base44.yml exec -T app ./mvnw <goal>` or `mvn <goal>`.
* Maven dependencies are cached in the `m2` named volume, so the first start is the slow one.

## Live-reload behaviour
* HTML/CSS/JS: compose sets
  `SPRING_WEB_RESOURCES_STATICLOCATIONS=file:/app/src/main/resources/static/`, so edits to
  static files are served immediately (refresh the preview, no restart).
* Java: there is no devtools/watch loop. After changing Java code run
  `docker compose -f docker-compose.base44.yml restart app` (first `request` recompiles,
  takes ~20–40 s) and then force a preview reload.

## Verifying it works
* `curl -s -o /dev/null -w '%{http_code}' http://localhost:3000/` → 200.
* Default admin is created on first boot by `AdminSeeder`: `admin@npo.com` / `Admin@123`
  (only created when no ADMIN user exists).
* `SecurityConfig` permits the static pages and public endpoints; role-gated APIs
  (`/admin/**`, `/helper/**`, `/leaderboard`, `/help-requests`) require the JWT from `/login`
  sent as `Authorization: Bearer <token>`.

## Quirks
* Spring Boot 4.1.1 — note the `-webmvc` starter names.
* MongoDB data lives in the `mongo-data` volume; deleting it re-seeds the admin user.
