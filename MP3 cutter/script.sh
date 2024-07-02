#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

JAR_FILE="$SCRIPT_DIR/demo-1.0-SNAPSHOT.jar"

JAVA_FX_PATH="openjfx"

MODULE_PATH="$JAVA_FX_PATH/javafx-controls/21-ea+24:$JAVA_FX_PATH/javafx-fxml/21-ea+24:$JAVA_FX_PATH/javafx-base/21-ea+24:$JAVA_FX_PATH/javafx-graphics/21-ea+24"

# Uruchomienie aplikacji JavaFX
java --module-path $MODULE_PATH --add-modules javafx.controls,javafx.fxml -jar "$JAR_FILE"

