import greenfoot.*;
import java.awt.*;

public class StartScreen extends World {
    private int inputWidth = 9;
    private int inputHeight = 9;
    private int inputMines = 10;
    
    // Schwierigkeitsstufen
    private static final int[][] DIFFICULTY_EASY = {9, 9, 10};
    private static final int[][] DIFFICULTY_MEDIUM = {12, 12, 30};
    private static final int[][] DIFFICULTY_HARD = {16, 16, 99};
    
    private String currentInput = "";
    private String selectedField = "difficulty"; // "difficulty", "width", "height", "mines"
    private int selectedDifficulty = 0; // 0=Easy, 1=Medium, 2=Hard, 3=Custom
    
    public StartScreen() {
        super(600, 500, 1);
        drawScreen();
    }
    
    public void act() {
        // Tastatureingaben verarbeiten
        String key = Greenfoot.getKey();
        
        if (key != null) {
            if (selectedDifficulty < 3) {
                // Schwierigkeitswahl mit Pfeiltasten
                if (key.equals("left")) {
                    selectedDifficulty = (selectedDifficulty - 1 + 4) % 4;
                    currentInput = "";
                } else if (key.equals("right")) {
                    selectedDifficulty = (selectedDifficulty + 1) % 4;
                    currentInput = "";
                }
            } else {
                // Custom-Mode: Eingaben für Breite, Höhe, Minen
                if (key.equals("tab") || key.equals("shift")) {
                    // Zum nächsten Feld springen
                    if (selectedField.equals("width")) {
                        selectedField = "height";
                    } else if (selectedField.equals("height")) {
                        selectedField = "mines";
                    } else if (selectedField.equals("mines")) {
                        selectedField = "width";
                    }
                    currentInput = "";
                }
                
                // Ziffern eingeben
                if (key.matches("[0-9]")) {
                    currentInput += key;
                    try {
                        int value = Integer.parseInt(currentInput);
                        if (value > 100) {
                            currentInput = "100";
                            value = 100;
                        }
                        
                        if (selectedField.equals("width")) {
                            inputWidth = value;
                        } else if (selectedField.equals("height")) {
                            inputHeight = value;
                        } else if (selectedField.equals("mines")) {
                            inputMines = value;
                        }
                    } catch (NumberFormatException e) {
                        // Ignorieren
                    }
                }
                
                // Backspace
                if (key.equals("backspace")) {
                    if (currentInput.length() > 0) {
                        currentInput = currentInput.substring(0, currentInput.length() - 1);
                    }
                }
            }
            
            // Enter zum Starten
            if (key.equals("enter")) {
                startGame();
            }
            
            // Escape zum Zurück zur Schwierigkeitswahl
            if (key.equals("escape") && selectedDifficulty == 3) {
                selectedDifficulty = 0;
                currentInput = "";
                selectedField = "difficulty";
            }
        }
        
        drawScreen();
    }
    
    private void drawScreen() {
        GreenfootImage bg = new GreenfootImage(getWidth(), getHeight());
        bg.setColor(Color.WHITE);
        bg.fillRect(0, 0, getWidth(), getHeight());
        
        bg.setColor(Color.BLACK);
        bg.setFont(new Font("Arial", Font.BOLD, 36));
        bg.drawString("MINESWEEPER", 160, 50);
        
        if (selectedDifficulty < 3) {
            // Schwierigkeitswahl
            drawDifficultyScreen(bg);
        } else {
            // Custom-Mode
            drawCustomScreen(bg);
        }
    }
    
    private void drawDifficultyScreen(GreenfootImage bg) {
        bg.setFont(new Font("Arial", Font.PLAIN, 20));
        bg.drawString("Wähle eine Schwierigkeit:", 50, 120);
        
        // Schwierigkeitsstufen zeichnen
        String[] difficulties = {"Easy\n(9x9, 10 Minen)", "Medium\n(12x12, 30 Minen)", "Hard\n(16x16, 99 Minen)", "Custom"};
        int[] colors = {Color.GREEN.getRGB(), Color.YELLOW.getRGB(), Color.RED.getRGB(), Color.BLUE.getRGB()};
        
        for (int i = 0; i < 4; i++) {
            int x = 50 + (i * 120);
            int y = 200;
            
            // Rahmen
            if (selectedDifficulty == i) {
                bg.setColor(new Color(colors[i]));
                bg.fillRect(x - 10, y - 10, 110, 100);
                bg.setColor(Color.BLACK);
                bg.setStroke(new BasicStroke(3));
                bg.drawRect(x - 10, y - 10, 110, 100);
            } else {
                bg.setColor(Color.LIGHT_GRAY);
                bg.drawRect(x - 10, y - 10, 110, 100);
            }
            
            bg.setColor(Color.BLACK);
            bg.setFont(new Font("Arial", Font.BOLD, 14));
            
            String[] parts = difficulties[i].split("\n");
            bg.drawString(parts[0], x + 5, y + 20);
            if (parts.length > 1) {
                bg.setFont(new Font("Arial", Font.PLAIN, 12));
                bg.drawString(parts[1], x + 5, y + 50);
            }
        }
        
        bg.setFont(new Font("Arial", Font.PLAIN, 16));
        bg.drawString("← / → zum Wählen | ENTER zum Starten", 50, 420);
    }
    
    private void drawCustomScreen(GreenfootImage bg) {
        bg.setFont(new Font("Arial", Font.PLAIN, 18));
        bg.drawString("Benutzerdefinierte Einstellungen:", 50, 120);
        
        bg.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Width-Feld
        drawInputField(bg, "Breite:", inputWidth, "width", 150);
        
        // Height-Feld
        drawInputField(bg, "Höhe:", inputHeight, "height", 220);
        
        // Mines-Feld
        drawInputField(bg, "Minen:", inputMines, "mines", 290);
        
        // Anleitung
        bg.setFont(new Font("Arial", Font.PLAIN, 14));
        bg.drawString("TAB zum Wechseln der Felder | ENTER zum Starten | ESC zurück", 30, 400);
        
        // Validierungshinweis
        int maxMines = inputWidth * inputHeight - 1;
        if (inputMines > maxMines) {
            bg.setColor(Color.RED);
            bg.drawString("⚠ Zu viele Minen! Max: " + maxMines, 50, 440);
        }
    }
    
    private void drawInputField(GreenfootImage bg, String label, int value, String fieldName, int y) {
        bg.setColor(Color.BLACK);
        bg.drawString(label, 50, y);
        
        // Box zeichnen
        if (selectedField.equals(fieldName)) {
            bg.setColor(Color.BLUE);
            bg.fillRect(150, y - 20, 100, 30);
            bg.setColor(Color.WHITE);
            bg.drawString(String.valueOf(value), 160, y);
        } else {
            bg.setColor(Color.LIGHT_GRAY);
            bg.fillRect(150, y - 20, 100, 30);
            bg.setColor(Color.BLACK);
            bg.drawString(String.valueOf(value), 160, y);
        }
    }
    
    private void startGame() {
        // Validierung
        if (selectedDifficulty < 3) {
            // Vordefinierte Schwierigkeit laden
            int[] difficulty = null;
            if (selectedDifficulty == 0) {
                difficulty = DIFFICULTY_EASY;
            } else if (selectedDifficulty == 1) {
                difficulty = DIFFICULTY_MEDIUM;
            } else if (selectedDifficulty == 2) {
                difficulty = DIFFICULTY_HARD;
            }
            
            if (difficulty != null) {
                Greenfoot.setWorld(new MinesweeperWorld(difficulty[0], difficulty[1], difficulty[2]));
            }
        } else {
            // Custom-Werte
            if (inputWidth < 2 || inputHeight < 2) {
                // Zu klein
                return;
            }
            
            int maxMines = inputWidth * inputHeight - 1;
            if (inputMines > maxMines) {
                inputMines = maxMines;
            }
            
            if (inputMines < 1) {
                inputMines = 1;
            }
            
            Greenfoot.setWorld(new MinesweeperWorld(inputWidth, inputHeight, inputMines));
        }
    }
}
