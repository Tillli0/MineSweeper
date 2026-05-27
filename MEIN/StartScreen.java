import greenfoot.*;
import java.awt.*;

public class StartScreen extends World {
    private int inputWidth = 10;
    private int inputHeight = 10;
    private int inputMines = 10;
    
    private String currentInput = ""; // Für aktuelle Eingabe
    private String selectedField = "width"; // Welches Feld wird gerade bearbeitet
    
    public StartScreen() {
        super(400, 300, 1);
        drawScreen();
    }
    
    public void act() {
        // TODO: Tastatureingaben verarbeiten (Zahlen, Backspace, Tab)
        
        // TODO: Enter-Taste zum Spiel starten
        
        // TODO: Wert aktualisieren basierend auf selectedField
        
        drawScreen(); // Nach jeder Änderung neu zeichnen
    }
    
    private void drawScreen() {
        GreenfootImage bg = new GreenfootImage(getWidth(), getHeight());
        
        // TODO: Titel zeichnen
        bg.setColor(Color.BLACK);
        bg.drawString("MINESWEEPER", 150, 30);
        
        // TODO: Beschreibungen für die drei Eingabefelder
        bg.drawString("Width: ", 50, 80);
        bg.drawString("Height: ", 50, 120);
        bg.drawString("Mines: ", 50, 160);
        
        // TODO: Die Eingabefelder selbst zeichnen (Rechtecke)
        // TODO: Aktuelle Werte anzeigen
        
        // TODO: "Start Game" Button oder Anleitung
        bg.drawString("Press ENTER to start", 100, 250);
        
        getBackground().drawImage(bg, 0, 0);
    }
    
    private void startGame() {
        // Validierung: Width, Height, Mines sinnvoll?
        // TODO: Eingaben prüfen
        
        Greenfoot.setWorld(new MinesweeperWorld(inputWidth, inputHeight, inputMines));
    }
}
