# NPO Data Management — working notes

Spring Boot 4.1.1 (Java 21) + MongoDB. The frontend is plain HTML/CSS/JS in
`src/main/resources/static/` served by the same Spring Boot app — there is no
separate frontend build step and no auth cookie (the UI stores a JWT in
`localStorage` and sends it as `Authorization: Bearer …`).

## Running it here

```bash
docker compose -f docker-compose.base44.yml up -d
```

- `mongo` — database, data in the `mongo-data` volume.
- `web` — `mvn spring-boot:run` from the bind-mounted repo, host port **3000** → container 8080.

The container's `./mvnw` wrapper jar is not committed and Maven is already in the
image, so the service runs `mvn` (3.9) instead of the wrapper.

## Non-obvious bits

- `application.properties` points at `mongodb://localhost:27017/npo`; the compose
  service overrides it with `SPRING_MONGODB_URI` / `SPRING_DATA_MONGODB_URI`
  (`mongodb://mongo:27017/npo`).
- The static resources are served from the source tree, not from `target/classes`:
  compose sets `SPRING_WEB_RESOURCES_STATIC_LOCATIONS=file:/app/src/main/resources/static/`.
  That is what makes HTML/CSS/JS edits appear without a rebuild.
- Java changes are **not** hot-reloaded (`spring-boot:run` does not watch classes).
  After editing anything under `src/main/java`: `docker compose -f docker-compose.base44.yml restart web`,
  then reload the preview.
- A default admin is seeded on first boot by `AdminSeeder`: `admin@npo.com` / `Admin@123`.
- The JWT signing key is hardcoded in `JwtService`, so no secret is required to boot.
  No external services (mail, payments, storage) are integrated — nothing to configure.

## How to check it works

```bash
curl -sI http://localhost:3000/index.html                       # 200, public site
curl -s -X POST http://localhost:3000/login \
  -H 'Content-Type: application/json' \
  -d '{"email":"admin@npo.com","password":"Admin@123"}'         # returns a JWT
curl -s -o /dev/null -w '%{http_code}' http://localhost:3000/admin/dashboard   # 403 without a token
```

## Pages and roles

Public: `index`, `login`, `register`, `donations`. After login the UI redirects by
role to `admin-dashboard.html`, `helper-dashboard.html` or `beneficiary-dashboard.html`.
Those dashboard pages bounce back to `login.html` when no token is in `localStorage`.

## Tests

`docker compose -f docker-compose.base44.yml exec -T web mvn -B test`
