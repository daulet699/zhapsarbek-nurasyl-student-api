# DevOps Midterm: workflow для Nurasyl Zhapsarbek

Этот ZIP — персональная **исходная заготовка**, а не доказательство выполненных команд. Git, Docker, Jenkins и GitHub нужно выполнить на MacBook и снять реальные скриншоты.

## Task 1.1 — структура

```bash
cd ~/devops
mkdir -p zhapsarbek-nurasyl-student-api/{scripts,logs,backups,src}
cd zhapsarbek-nurasyl-student-api
# распаковать сюда файлы из архива (см. сообщение ChatGPT)
touch logs/Zhapsarbek_Nurasyl_system_report.log
find . -maxdepth 2 -print
```

## Task 1.2–1.4 — Linux, в Ubuntu

```bash
chmod 744 scripts/Zhapsarbek_Nurasyl_system_report.sh
bash scripts/Zhapsarbek_Nurasyl_system_report.sh
ls -l scripts/Zhapsarbek_Nurasyl_system_report.sh
cp README.md backups/Zhapsarbek_Nurasyl_README_backup.md
cmp README.md backups/Zhapsarbek_Nurasyl_README_backup.md
ps aux | head
ps aux | grep '[j]ava' || true
tail -n 5 logs/Zhapsarbek_Nurasyl_system_report.log
grep 'Zhapsarbek' logs/Zhapsarbek_Nurasyl_system_report.log > Zhapsarbek_Nurasyl_log_analysis.txt
cat Zhapsarbek_Nurasyl_log_analysis.txt
```
`>` перезаписывает файл; `>>` добавляет новые строки в конец.

## Task 2 — Git

До первого коммита сгенерировать wrapper, проверить файлы:

```bash
bash scripts/bootstrap_gradle_wrapper.sh
git init -b main
git config user.name 'Nurasyl Zhapsarbek'
git config user.email 'YOUR_REAL_GITHUB_EMAIL'
git add .
git commit -m 'Nurasyl Zhapsarbek: initial Spring Boot Student API'
```

Для двух содержательных изменений на ветке `feature/zhapsarbek-nurasyl-crud` можно (1) добавить пояснение о CRUD в README и закоммитить; (2) добавить полезный тест или уточнение и закоммитить. Затем `git switch main; git merge feature/zhapsarbek-nurasyl-crud`.

Для обязательного конфликта: создать ветку `feature/zhapsarbek-nurasyl-documentation`, изменить одну строку README (например, строку с курсом) и закоммитить; затем в `main` изменить **эту же строку иначе**, коммит; `git merge feature/zhapsarbek-nurasyl-documentation`; вручную разрешить конфликт в README, убрать `<<<<<<<`, `=======`, `>>>>>>>`; `git add README.md; git commit`.

Перед push нужно создать **свой** репозиторий `zhapsarbek-nurasyl-student-api` в GitHub, затем:

```bash
git remote add origin https://github.com/YOUR_GITHUB_USERNAME/zhapsarbek-nurasyl-student-api.git
git push -u origin main
git push origin feature/zhapsarbek-nurasyl-crud feature/zhapsarbek-nurasyl-documentation
git tag -a v1.0-zhapsarbek-nurasyl-37841 -m 'Nurasyl Zhapsarbek student API version 1.0'
git push origin v1.0-zhapsarbek-nurasyl-37841
```
Не коммитить `.env`, токены и пароли.

## Task 3 — Docker

```bash
cp .env.example .env
# Отредактировать .env — задать уникальный пароль
export PATH="$HOME/.docker/bin:$PATH"
docker compose config
docker compose up -d --build
docker compose ps
docker compose logs app --tail=30
curl -i http://localhost:8096/
```

CRUD POST/GET/PUT/DELETE и запрос отсутствующего ID; приложить реальные HTTP-ответы. Проверить сохранность данных через `docker compose restart` и повторный GET. Образ и контейнер имеют имена Nurasyl, порт 8096.

## Task 4 — Jenkins

Job: `Zhapsarbek_Nurasyl_Student_API`. Pipeline script from SCM, GitHub repo Nurasyl, `*/main`, `Jenkinsfile`. Убедиться, что на Mac есть Java 21 (`/usr/libexec/java_home -v 21`), Docker Desktop и доступный Gradle wrapper.

Сборка: Checkout -> Build -> Test -> Docker Build -> Verify. Controlled failure делать **только в личной тестовой ветке** и затем исправить, не оставляя main сломанным.

Для автоматического запуска Jenkins можно включить **Poll SCM** `H/5 * * * *`, выполнить новый commit и push, дождаться polling-события и показать, что сборка запустилась автоматически. Для demo сохранить вывод последней успешной сборки и истории.

**Не подменять скриншоты: результаты должны соответствовать реально выполненным командам.**
