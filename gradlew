#!/usr/bin/env sh
# Minimal Gradle launcher: downloads the pinned distribution without requiring gradle-wrapper.jar.
set -eu
APP_HOME=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
GRADLE_VERSION=9.6.0
GRADLE_USER_HOME=${GRADLE_USER_HOME:-"$HOME/.gradle"}
GRADLE_HOME="$GRADLE_USER_HOME/wrapper/dists/folio-gradle-$GRADLE_VERSION/gradle-$GRADLE_VERSION"
if [ ! -x "$GRADLE_HOME/bin/gradle" ]; then
  mkdir -p "$(dirname "$GRADLE_HOME")"
  ZIP_PATH="$(dirname "$GRADLE_HOME")/gradle-$GRADLE_VERSION-bin.zip"
  if [ ! -f "$ZIP_PATH" ]; then
    if command -v curl >/dev/null 2>&1; then
      curl -fL --retry 3 "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip" -o "$ZIP_PATH"
    elif command -v wget >/dev/null 2>&1; then
      wget "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip" -O "$ZIP_PATH"
    else
      echo "Install curl or wget to download Gradle $GRADLE_VERSION." >&2
      exit 1
    fi
  fi
  unzip -q -o "$ZIP_PATH" -d "$(dirname "$GRADLE_HOME")"
fi
exec "$GRADLE_HOME/bin/gradle" -p "$APP_HOME" "$@"
