import greenfoot.*;
import java.util.*;

public class MinesweeperWorld extends World {
    private int WIDTH;
    private int HEIGHT;
    private int MINE_COUNT;
    private Cell[][] grid;
    
    // Konstruktor OHNE Parameter (Standard-Werte)
    public MinesweeperWorld() {
        this(10, 10, 10); // Ruft den anderen Konstruktor auf
    }
    
    // Konstruktor MIT Parametern (vom StartScreen)
    public MinesweeperWorld(int width, int height, int mineCount) {
        super(width, height, 30);
        this.WIDTH = width;
        this.HEIGHT = height;
        this.MINE_COUNT = mineCount;
        grid = new Cell[WIDTH][HEIGHT];
        initialize();
    }

    
    private void initialize() {
        // Zellen erstellen
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                grid[x][y] = new Cell(x, y);
                addObject(grid[x][y], x, y);
            }
        }
        // Minen zufällig platzieren
        placeMines();
        // Zahlen berechnen
        calculateNumbers();
    }
    
    private void placeMines() {
        int placed = 0;
        while (placed < MINE_COUNT) {
            int x = Greenfoot.getRandomNumber(WIDTH);
            int y = Greenfoot.getRandomNumber(HEIGHT);
            if (!grid[x][y].hasMine()) {
                grid[x][y].setMine();
                placed++;
            }
        }
    }
    
    private void calculateNumbers() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (!grid[x][y].hasMine()) {
                    int count = countAdjacentMines(x, y);
                    grid[x][y].setAdjacentMines(count);
                }
            }
        }
    }
    
    private int countAdjacentMines(int x, int y) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx, ny = y + dy;
                if (nx >= 0 && nx < WIDTH && ny >= 0 && ny < HEIGHT) {
                    if (grid[nx][ny].hasMine()) count++;
                }
            }
        }
        return count;
    }
    
    public void revealCell(int x, int y) {
        if (grid[x][y].isRevealed()) return;
        
        grid[x][y].reveal();
        
        if (grid[x][y].hasMine()) {
            gameOver(false);
        } else if (grid[x][y].getAdjacentMines() == 0) {
            // Floodfill für leere Zellen
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = x + dx, ny = y + dy;
                    if (nx >= 0 && nx < WIDTH && ny >= 0 && ny < HEIGHT) {
                        revealCell(nx, ny);
                    }
                }
            }
        }
    }
    
    private void gameOver(boolean won) {
        // Alle Minen zeigen
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (grid[x][y].hasMine()) {
                    grid[x][y].reveal();
                }
            }
        }
        Greenfoot.stop();
    }
}
