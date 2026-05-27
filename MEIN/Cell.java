import greenfoot.*;
import java.awt.Color;

public class Cell extends Actor {
    private int x, y;
    private boolean isMine = false;
    private boolean isRevealed = false;
    private boolean isFlagged = false;
    private int adjacentMines = 0;
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        updateImage();
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            if (Greenfoot.getMouseInfo().getButton() == 1) { // Linksklick
                ((MinesweeperWorld) getWorld()).revealCell(x, y);
            } else if (Greenfoot.getMouseInfo().getButton() == 3) { // Rechtsklick
                flag();
            }
        }
    }
    
    public void reveal() {
        isRevealed = true;
        updateImage();
    }
    
    public void flag() {
        if (!isRevealed) {
            isFlagged = !isFlagged;
            updateImage();
        }
    }
    
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(30, 30);
        
        if (isFlagged) {
            img.setColor(Color.RED);
            img.fillRect(0, 0, 30, 30);
            img.setColor(Color.YELLOW);
            img.drawString("🚩", 5, 20);
        } else if (isRevealed) {
            img.setColor(Color.LIGHT_GRAY);
            img.fillRect(0, 0, 30, 30);
            if (isMine) {
                img.setColor(Color.BLACK);
                img.drawString("*", 10, 20);
            } else if (adjacentMines > 0) {
                img.setColor(Color.BLUE);
                img.drawString("" + adjacentMines, 10, 20);
            }
        } else {
            img.setColor(Color.GRAY);
            img.fillRect(0, 0, 30, 30);
            img.setColor(Color.BLACK);
            img.drawRect(0, 0, 29, 29);
        }
        
        setImage(img);
    }
    
    // Getter und Setter
    public void setMine() { isMine = true; }
    public boolean hasMine() { return isMine; }
    public void setAdjacentMines(int count) { adjacentMines = count; }
    public int getAdjacentMines() { return adjacentMines; }
    public boolean isRevealed() { return isRevealed; }
}
