# Nurasyl Zhapsarbek — Student CRUD API

**Student:** Nurasyl Zhapsarbek  
**Group:** IT2-2308  
**Student ID:** 37841  
**Course:** DevOps Midterm

## Technologies
Java 21, Spring Boot 3.5.9, Spring Data JPA, PostgreSQL, Gradle 8.14.3, Docker Compose and Jenkins.

## CRUD routes

| Operation | Method | Endpoint | Status |
|---|---|---|---|
| Create | POST | `/api/students` | 201 |
| Read all | GET | `/api/students` | 200 |
| Read one | GET | `/api/students/{id}` | 200 or 404 |
| Update | PUT | `/api/students/{id}` | 200 or 404 |
| Delete | DELETE | `/api/students/{id}` | 204 or 404 |
| Personal info | GET | `/` | 200 |

## Prepare Gradle wrapper once

```bash
bash scripts/bootstrap_gradle_wrapper.sh
```

## Run automated tests

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
export PATH="$JAVA_HOME/bin:$PATH"
./gradlew test
```

## Launch PostgreSQL and API

Copy `.env.example` to `.env`, choose a **new private password** (never commit it), then:

```bash
docker compose config
docker compose up -d --build
docker compose ps
curl http://localhost:8096/
```

### Example CRUD request

```bash
curl -i -X POST http://localhost:8096/api/students \
  -H 'Content-Type: application/json' \
  -d '{"fullName":"Nurasyl Zhapsarbek","email":"nurasyl.student@example.com","groupName":"IT2-2308","studentNumber":"37841"}'
```

Choose the returned id for GET/PUT/DELETE.

## PostgreSQL persistence evidence

Create a student, run `docker compose restart`, then query `GET /api/students` again. The named volume preserves the records. Do not use `docker compose down -v` during the lab.

## Jenkins

Job name: `Zhapsarbek_Nurasyl_Student_API`; Definition: Pipeline script from SCM; branch: `*/main`; script: `Jenkinsfile`; Java 21 required. Use a polling trigger for automatic builds if webhook setup is unavailable.

## Individual work

For Git feature branches, deliberate README merge conflict, version tag, controlled failing CI test, and final evidence, see `docs/WORKFLOW_RU.md`.
