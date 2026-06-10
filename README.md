# Minesweeper in Greenfoot

Klassisches Minesweeper-Spiel in Java mit Greenfoot.

---

## Projektübersicht

- Drei Schwierigkeitsstufen:
  - Easy (9×9, 10 Minen)
  - Medium (12×12, 30 Minen)
  - Hard (16×16, 99 Minen)
- **Custom-Modus** (derzeit noch nicht funktionsfähig)
- Zufällige Minenplatzierung
- Automatisches Aufdecken leerer Felder (Flood Fill)
- Flaggen zum Markieren vermuteter Minen

---

## Dateien

### StartScreen.java
- Menü zur Auswahl der Schwierigkeit
- Steuerung:  
  - ← / → : Schwierigkeit wechseln  
  - ENTER : Spiel starten  
- **Custom-Modus**:  
  - Aktuell noch nicht korrekt implementiert. Die Eingabe eigener Breite, Höhe und Minenanzahl funktioniert nicht zuverlässig.  

### MinesweeperWorld.java
- Erzeugt das Spielfeld
- Platziert Minen zufällig
- Berechnet die Zahlen der angrenzenden Minen
- Aufdecken der Zellen und Flood Fill
- Spielende bei Aufdecken einer Mine

### Cell.java
- Einzelne Spielfeldzelle
- Eigenschaften: Mine, aufgedeckt, markiert, angrenzende Minen
- Steuerung:  
  - Linksklick: Feld aufdecken  
  - Rechtsklick: Flagge setzen/entfernen

---

## Steuerung im Spiel

| Aktion | Eingabe |
|--------|---------|
| Feld aufdecken | Linksklick |
| Flagge setzen | Rechtsklick |
| Zurück zum Menü | ESC |

---

## Darstellung

- Verdecktes Feld: Grau  
- Aufgedecktes Feld: Hellgrau  
- Zahl: Blau (zeigt angrenzende Minen)  
- Mine: Schwarzes "*"  
- Flagge: Roter Hintergrund mit Flaggensymbol

---

## Bekannte Probleme

- Der Custom-Modus ist aktuell noch nicht funktionsfähig.  
- Die Eingabe von Breite, Höhe und Minenanzahl wird derzeit nicht korrekt übernommen.  
- Die vordefinierten Schwierigkeitsstufen funktionieren einwandfrei.  
- Behebung des Custom-Modus ist für eine zukünftige Version geplant.
