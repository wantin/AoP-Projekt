# AoP-Projekt
Unser Spiel, das die Prüfung des Fachs AoP2 sein wird. geschrieben in java

TODOs
    primäre Aufgaben
        -GUI
            -Spielfeld
            -Handkarten
            -aktiver Spieler anzeige
            -ausgewählte einheit hervorheben
            -mögliche Züge signalisieren
        -Einheit
            Stärkewerte(effektiv gegen welche Einheit) = Leben: bei Angriff (egal von wem) reduziert(je nach Stärke des Gegners),
            extra Verteidigungswert,
            Bewegung, Reichweite, Name,
            Karte1/Karte2 -> Methoden ableiten, eine Einheit greift nur eine weitere an
        -Testeinheit
        -Main Funktion
            Spielablauf    
        -Spielbrett(jaa, links und rechts ist gut + passende Größe)
            freie Auswahl der Platzierung oder mit Einschränkungen?(z.B. nicht in Angriffsweite fremder Einheiten)
        -Spieler(Startkapital und Auswahl der Karten mit entsprechenden Eigenschaften zu Beginn des Spiels?,
            Möglichkeit der Namenseingabe und -anzeige während des Spiels, Ausgabe Gewinner?)


    sekundäre Aufgaben
        -vor dem Spiel Karten kaufen
        -vor dem Spiel Spielernamen eingeben
        -Handkartenanzeige
        -Namenanzeige
        -viele Einheiten
            -Kavallerie
            -Speerträger
            -Schwere Infantrie
            -Bogenschützen
            -Trebuchet
            ...
        -Bilder für Einheiten
        -Spielumgebung gestalten (GUI hübsch machen)

    optionale Aufgaben
        -Musik
        -Effektkarten
        -KI


Diskusion über wie wir Dinge machen sollten
    -Wie groß soll das Spielfeld sein?
    -W
    -Wie machen wir die Zugreihenfolge?
        Option 1: abwechselnd 1 Aktion
        Option 2: mehrere Aktionen nicht mehrfach die gleiche Einheit
            Dies würde erlauben, dass der Startspielervorteil eingegrenzt wird, indem der erste Zug weniger Aktionen hat.
    -Wie Funktionieren die Kämpfe:
        Valentin schlägt vor: Jede Einheit hat einen Stärkewert, dieser wird reduziert, wenn sie angegriffen wird. Er wird um den Stärkewert der angreifenen Einheit -den Rüstungswert der angegriffenen Einheit.
        Die angreifende Einheit erleidet keinen Schaden, außer durch andere Effekte.
    -Wie können wir verschiedene Karteneffekte gut realisieren?
      müssen wir bei jeder Karte die Fähigkeit haben und dann bei denen die nichts machen das leer lassen?
    -
    -

Aus den Diskussionen kam:

    -links rechts Aufteilung
