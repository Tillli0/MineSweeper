import greenfoot.*;
import java.awt.*;

public class StartScreen extends World {
    private int inputWidth = 9;
    private int inputHeight = 9;
    private int inputMines = 10;
    
    private String currentInput = "";
    private String selectedField = "difficulty";
    private int selectedDifficulty = 0; // 0=Easy, 1=Medium, 2=Hard, 3=Custom
    
    public StartScreen() {
        super(600, 500, 1);
        drawScreen();
    }
    
    public void act() {
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
                // Custom-Mode
                if (key.equals("tab")) {
                    if (selectedField.equals("width")) {
                        selectedField = "height";
                    } else if (selectedField.equals("height")) {
                        selectedField = "mines";
                    } else if (selectedField.equals("mines")) {
                        selectedField = "width";
                    }
                    currentInput = "";
                }
                
                if (key.matches("[0-9]")) {
                    currentInput += key;
                    try {
                        int value = Integer.parseInt(currentInput);
                        
                        if (value > 100) {
                            value = 100;
                            currentInput = "100";
                        }
                        
                        if (selectedField.equals("width")) {
                            inputWidth = value;
                        } else if (selectedField.equals("height")) {
                            inputHeight = value;
                        } else if (selectedField.equals("mines")) {
                            inputMines = value;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
                
                if (key.equals("backspace")) {
                    if (currentInput.length() > 0) {
                        currentInput = currentInput.substring(0, currentInput.length() - 1);
                    }
                }
            }
            
            if (key.equals("enter")) {
                startGame();
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
            drawDifficultyScreen(bg);
        } else {
            drawCustomScreen(bg);
        }
        
        setBackground(bg);
    }
    
    private void drawDifficultyScreen(GreenfootImage bg) {
        bg.setFont(new Font("Arial", Font.PLAIN, 20));
        bg.drawString("Wähle eine Schwierigkeit:", 50, 120);
        
        String[] difficulties = {
            "Easy\n(9x9, 10)",
            "Medium\n(12x12, 30)",
            "Hard\n(16x16, 99)",
            "Custom"
        };
        
        Color[] colors = {
            Color.GREEN,
            Color.YELLOW,
            Color.RED,
            Color.BLUE
        };
        
        for (int i = 0; i < 4; i++) {
            int x = 50 + (i * 120);
            int y = 200;
            
            if (selectedDifficulty == i) {
                bg.setColor(colors[i]);
                bg.fillRect(x - 10, y - 10, 110, 100);
                bg.setColor(Color.BLACK);
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
        bg.drawString("← / → zum Wählen | ENTER zum Starten | ESC zum Beenden", 30, 420);
    }
    
    private void drawCustomScreen(GreenfootImage bg) {
        bg.setFont(new Font("Arial", Font.PLAIN, 18));
        bg.drawString("Benutzerdefinierte Einstellungen:", 50, 120);
        
        bg.setFont(new Font("Arial", Font.PLAIN, 16));
        
        drawInputField(bg, "Breite:", inputWidth, "width", 150);
        drawInputField(bg, "Höhe:", inputHeight, "height", 220);
        drawInputField(bg, "Minen:", inputMines, "mines", 290);
        
        bg.setFont(new Font("Arial", Font.PLAIN, 14));
        bg.drawString("TAB zum Wechseln | ENTER zum Starten | ESC zum Beenden", 30, 400);
        
        int maxMines = inputWidth * inputHeight - 1;
        
        if (inputMines > maxMines) {
            bg.setColor(Color.RED);
            bg.drawString("Zu viele Minen! Max: " + maxMines, 50, 440);
        }
    }
    
    private void drawInputField(GreenfootImage bg, String label, int value,
                                String fieldName, int y) {
        bg.setColor(Color.BLACK);
        bg.drawString(label, 50, y);
        
        if (selectedField.equals(fieldName)) {
            bg.setColor(Color.BLUE);
            bg.fillRect(150, y - 20, 100, 30);
            bg.setColor(Color.WHITE);
        } else {
            bg.setColor(Color.LIGHT_GRAY);
            bg.fillRect(150, y - 20, 100, 30);
            bg.setColor(Color.BLACK);
        }
        
        bg.drawString(String.valueOf(value), 160, y);
    }
    
    private void startGame() {
        if (selectedDifficulty < 3) {
            int width = 9;
            int height = 9;
            int mines = 10;
            
            if (selectedDifficulty == 1) {
                width = 12;
                height = 12;
                mines = 30;
            } else if (selectedDifficulty == 2) {
                width = 16;
                height = 16;
                mines = 99;
            }
            
            Greenfoot.setWorld(new MinesweeperWorld(width, height, mines));
        } else {
            if (inputWidth < 2 || inputHeight < 2) {
                return;
            }
            
            int maxMines = inputWidth * inputHeight - 1;
            
            if (inputMines > maxMines) {
                inputMines = maxMines;
            }
            
            if (inputMines < 1) {
                inputMines = 1;
            }
            
            Greenfoot.setWorld(
                new MinesweeperWorld(inputWidth, inputHeight, inputMines)
            );
        }
    }
}