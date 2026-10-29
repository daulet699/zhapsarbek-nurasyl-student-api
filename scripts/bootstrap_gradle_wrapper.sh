#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
if [ -f gradle/wrapper/gradle-wrapper.jar ] && [ -f gradlew ]; then
  echo 'Gradle wrapper already exists.'
  exit 0
fi
: "${DOCKER:=docker}"
"$DOCKER" run --rm --user root \
  -v "$PWD:/workspace" -w /workspace \
  -e GRADLE_USER_HOME=/tmp/gradle-user-home \
  gradle:8.14-jdk21 \
  gradle wrapper --gradle-version 8.14.3 --distribution-type bin
chmod +x gradlew
ls -l gradlew gradle/wrapper/gradle-wrapper.jar gradle/wrapper/gradle-wrapper.properties
