# Minesweeper in Greenfoot

Klassisches Minesweeper-Spiel in Java mit Greenfoot.

---

## Projektübersicht

- Drei Schwierigkeitsstufen: Easy (9×9,10), Medium (12×12,30), Hard (16×16,99)
- Custom-Modus: Breite, Höhe, Minen frei wählbar
- Automatisches Aufdecken leerer Felder (Flood Fill)
- Flaggen zum Markieren vermuteter Minen
- Zufällige Minenplatzierung bei jedem Spielstart

---

## Dateien

### StartScreen.java
- Menü zur Auswahl der Schwierigkeit oder benutzerdefinierter Werte
- Steuerung:  
  - ← / → : Schwierigkeit wechseln  
  - TAB : Eingabefeld wechseln  
  - 0–9 : Werte eingeben  
  - ENTER : Spiel starten  

### MinesweeperWorld.java
- Das Spielfeld
- Initialisierung der Zellen, Platzierung der Minen, Berechnung der Zahlen
- Aufdecken von Zellen und Flood Fill
- Spielende bei Aufdecken einer Mine

### Cell.java
- Einzelne Zelle auf dem Spielfeld
- Eigenschaften: Mine, aufgedeckt, markiert, Anzahl angrenzender Minen
- Steuerung:  
  - Linksklick: aufdecken  
  - Rechtsklick: Flagge setzen/entfernen

---

## Steuerung im Spiel

| Aktion | Eingabe |
|--------|---------|
| Feld aufdecken | Linksklick |
| Flagge setzen | Rechtsklick |
| Zurück zum Menü | ESC |

---

## Validierung (Custom-Modus)

- Breite ≥ 2, Höhe ≥ 2  
- Minen ≥ 1 und ≤ Breite × Höhe − 1

---

## Darstellung

- Verdecktes Feld: Grau  
- Aufgedecktes Feld: Hellgrau  
- Zahl: Blau  
- Mine: Schwarzes "*"  
- Flagge: Roter Hintergrund mit Flaggensymbol
