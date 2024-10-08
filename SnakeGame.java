import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    //Snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    //Food
    Tile food;
    Random random;

    //gameLogic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;
    int highscore =0;

    SnakeGame(int boardWidth, int boardHeight){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        resetGame();
    }

    public void resetGame(){
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();
        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;
        gameOver = false;
        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
}

    public void draw(Graphics g){
        //Grid
       // for (int i = 0; i < boardWidth/tileSize; i++){
           //g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
         //   g.drawLine(0, i*tileSize, boardWidth, i*tileSize);
       // }

        //Food
        g.setColor(Color.red);
      //  g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);


        //Snake
        g.setColor(Color.green);
       // g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);
        //SnakeBody
        for (int i = 0; i < snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
          //  g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
          g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        //Score
        g.setFont(new Font("Arial", Font.BOLD, 16));
        if (gameOver){
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over",boardWidth / 2 - 100, boardHeight / 2 - 20); //Centering the Text
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press 'R' to restart", boardWidth / 2 - 70, boardHeight / 2 + 20); //Centre the text below
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press 'Q' to Quit", boardWidth / 2 - 70, boardHeight / 2 + 50); // Centre the text below
            g.drawString("Game Over: "+ String.valueOf(snakeBody.size()), tileSize - 16, tileSize);


            //HighScore is updated only if current score exceeds it 
            if (snakeBody.size() > highscore){
                highscore = snakeBody.size(); 
            }
            g.drawString("High Score: " + highscore, boardWidth - 150, tileSize);
        }
        else{
            g.drawString ("High Score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
            g.setColor(Color.white);
            g.drawString("High Score: " + highscore, boardWidth -150, tileSize);
        }
    }

    public void placeFood(){
        food.x = random.nextInt(boardWidth/tileSize); //600/25 = 24
        food.y = random.nextInt(boardHeight/tileSize);
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move(){
        //eat food
        if (collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        //Snake Body
        for (int i = snakeBody.size()-1; i >=0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tile prevsnakePart = snakeBody.get(i-1);
                snakePart.x = prevsnakePart.x;
                snakePart.y = prevsnakePart.y;
        }
    }

        //SnakeHead
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        //game over condition
        for (int i = 0; i < snakeBody.size();i++) {
            Tile snakePart = snakeBody.get(i);
            //collide with the snake head
            if (collision(snakeHead, snakePart)){
                gameOver = true;
            }
        }

        if (snakeHead.x*tileSize < 0 || snakeHead.x*tileSize >  boardWidth || snakeHead.y*tileSize < 0 || snakeHead.y*tileSize > boardHeight) {
            gameOver = true;
        }

    //Update the HighScore if necessary
    if (snakeBody.size() > highscore){
        highscore = snakeBody.size();
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
        if (gameOver && e.getKeyCode() == KeyEvent.VK_R){
            resetGame(); //Restart the game when the 'R' key is pressed.
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
            
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_Q){
            System.exit(0); //Exit the game when 'Q' key is pressed.
        }
    }

    //Do Not Need this but its necessary to define
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}

