#!/bin/bash

cd "$(dirname "$0")"

# Pętla po wszystkich plikach MP3 w katalogu
for mp3_file in *.mp3; do
    # Sprawdzenie, czy plik istnieje i czy jest plikiem MP3
    if [ -f "$mp3_file" ]; then
        echo "Aktualizowanie metadanych dla pliku: $mp3_file"

        # Pobranie nazwy artysty i tytułu z nazwy pliku
        # Używamy regex, aby wyciągnąć część po '-' jako artystę i resztę jako tytuł
        if [[ "$mp3_file" =~ ^[0-9]+[[:space:]](.+)[[:space:]]-[[:space:]](.+)\.mp3$ ]]; then
            ARTIST="${BASH_REMATCH[1]}"
            TITLE="${BASH_REMATCH[2]}"
        else
            echo "Nie można rozpoznać formatu nazwy pliku: $mp3_file"
            continue
        fi
        
        ALBUM="50 Studencki Festiwal Piosenki"
        YEAR="2014"

        # Ustawianie metadanych za pomocą eyeD3
        eyeD3 --to-v2.4 --artist="$ARTIST" --album="$ALBUM" --text-frame=TDRC:"$YEAR" --title="$TITLE" "$mp3_file"

        echo "Metadane zaktualizowane dla pliku: $mp3_file"
        echo "---"
    else
        echo "Nie znaleziono plików MP3 do przetworzenia."
    fi
done

echo "Zakończono aktualizację metadanych dla wszystkich plików MP3."

