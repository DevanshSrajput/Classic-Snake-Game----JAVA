import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int boardWidth;
    private int boardHeight;
    private int tileSize = 25;

    private Tile snakeHead;
    private ArrayList<Tile> snakeBody;
    private Tile food;
    private Random random;

    private Timer gameLoop;
    private int velocityX;
    private int velocityY;
    private boolean gameOver = false;
    private int highscore; // High score variable

    private DatabaseManager dbManager; // Instance of DatabaseManager

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        dbManager = new DatabaseManager(); // Initialize DatabaseManager
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        resetGame(); // Start the game
    }

    public void resetGame() {
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<>();
        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;
        gameOver = false;

        // Retrieve high score from database each time a new game starts
        highscore = dbManager.getHighScore(); // Ensure this method exists in DatabaseManager

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Draw Food
        g.setColor(Color.red);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        // Draw Snake
        g.setColor(Color.green);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

        // Draw Snake Body
        for (Tile snakePart : snakeBody) {
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        // Score Display
        g.setFont(new Font("Arial", Font.BOLD, 16));
        
        if (gameOver) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", boardWidth / 2 - 100, boardHeight / 2 - 20); 
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press 'R' to restart", boardWidth / 2 - 70, boardHeight / 2 + 20); 
            g.drawString("Press 'Q' to Quit", boardWidth / 2 - 70, boardHeight / 2 + 50);

            // Update High Score if current score exceeds it
            if (snakeBody.size() > highscore) {
                highscore = snakeBody.size();
                dbManager.saveScore(highscore); // Save new high score to database
            }
            g.drawString("Score: " + snakeBody.size(), tileSize - 16, tileSize);
            g.drawString("High Score: " + highscore, boardWidth - 150, tileSize);
            
        } else {
            g.setColor(Color.white);
            g.drawString("High Score: " + highscore, boardWidth -150, tileSize);
            g.drawString("Score: " + snakeBody.size(), tileSize - 16, tileSize);
        }
    }

    public void placeFood() {
        food.x = random.nextInt(boardWidth / tileSize); 
        food.y = random.nextInt(boardHeight / tileSize); 
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {
        
       if (collision(snakeHead, food)) {
           snakeBody.add(new Tile(food.x, food.y));
           placeFood();
       }

       for (int i = snakeBody.size() - 1; i >= 0; i--) {
           Tile snakePart = snakeBody.get(i);
           if (i == 0) {
               snakePart.x = snakeHead.x;
               snakePart.y = snakeHead.y;
           } else {
               Tile prevsnakePart = snakeBody.get(i - 1);
               snakePart.x = prevsnakePart.x;
               snakePart.y = prevsnakePart.y;
           }
       }

       // Move Snake Head
       snakeHead.x += velocityX;
       snakeHead.y += velocityY;

       // Check for collisions with itself or boundaries
       for (Tile snakePart : snakeBody) {
           if (collision(snakeHead, snakePart)) {
               gameOver = true;
           }
       }

       if (snakeHead.x < 0 || snakeHead.x >= boardWidth / tileSize || 
           snakeHead.y < 0 || snakeHead.y >= boardHeight / tileSize) {
           gameOver = true; 
       }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
       move();
       repaint();
       if (gameOver) { 
           gameLoop.stop(); 
       }
   }

   @Override
   public void keyPressed(KeyEvent e) {
       if (gameOver && e.getKeyCode() == KeyEvent.VK_R) { 
           resetGame(); 
       } else if (!gameOver) { 
           switch (e.getKeyCode()) { 
               case KeyEvent.VK_UP:
                   if (velocityY != 1) { velocityX = 0; velocityY = -1; }
                   break; 
               case KeyEvent.VK_DOWN:
                   if (velocityY != -1) { velocityX = 0; velocityY = 1; }
                   break; 
               case KeyEvent.VK_LEFT:
                   if (velocityX != 1) { velocityX = -1; velocityY = 0; }
                   break; 
               case KeyEvent.VK_RIGHT:
                   if (velocityX != -1) { velocityX = 1; velocityY = 0; }
                   break; 
           }
       } else if (e.getKeyCode() == KeyEvent.VK_Q) { 
           System.exit(0); 
       }
   }

   @Override
   public void keyTyped(KeyEvent e) {}
   @Override
   public void keyReleased(KeyEvent e) {}
}