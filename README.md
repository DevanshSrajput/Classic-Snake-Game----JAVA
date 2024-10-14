# 🐍 Snake Game

Welcome to the world of **Snake**, where you can relive your childhood by guiding a pixelated snake to its food while trying not to eat yourself! This Java-based game combines nostalgia with a sprinkle of coding magic. 

## 🎮 Introduction

Remember those days when playing games meant simple graphics and addictive gameplay? Well, this Snake game is here to take you back! Built with Java Swing, it’s all about maneuvering that hungry snake around the screen and gobbling up food while growing longer. Just don’t let it bite itself—nobody likes a self-destructive snake!

## 🎯 Objective

The main objective of this project is simple:
- **Eat food** to grow your snake.
- **Avoid collisions** with yourself or the walls.
- **Achieve the highest score** possible and have it saved in a MySQL database for bragging rights!

## 🌟 Features

- **Classic Gameplay**: Navigate your snake using arrow keys.
- **Score Tracking**: Keep track of your score in real-time.
- **High Score Storage**: Save your high score in a MySQL database (because who doesn't want to show off?).
- **Game Over Logic**: Know when you’ve messed up (and how badly).
- **User-Friendly UI**: Simple and clean interface—no fancy stuff, just pure fun!

## 🚀 Future Scope

What’s next for our slithering friend?
- **Levels of Difficulty**: Increase the speed as you munch more food!
- **Sound Effects**: Add some sizzle when you eat (or crash).
- **Leaderboard**: Compete with friends for the ultimate snake supremacy.
- **Power-ups**: Because who doesn’t love a little extra help?

## 🛠️ How to Run the Game

### Prerequisites
Make sure you have:
- Java Development Kit (JDK) installed.
- MySQL server running for high score storage.

### Installation Steps
1. Clone this repository:
   ```bash
   git clone https://github.com/DevanshSrajput/Classic-Snake-Game----JAVA.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Classic-Snake-Game----JAVA
   ```
3. Compile the Java files:
   ```bash
   javac App.java SnakeGame.java DatabaseManager.java
   ```
4. Run the application:
   ```bash
   java App
   ```

### Setup Database
1. Create a MySQL database named `snake_game`.
2. Create a table named `scores`:
   ```sql
   CREATE TABLE scores (id INT AUTO_INCREMENT PRIMARY KEY, score INT);
   ```

## 📊 Data Flow Diagram

![DFD](https://github.com/DevanshSrajput/Classic-Snake-Game----JAVA/blob/main/Data%20Flow%20Diagram%20(DFD).jpg)

## 🤔 How It Works

1. **Game Initialization**: The game starts, retrieves the high score from the database, and sets everything up.
2. **User Input**: Arrow keys control the snake's movement—no joystick needed!
3. **Game Loop**: Continuously checks for collisions, updates scores, and redraws the game board.
4. **Database Interaction**: Saves new high scores when achieved and retrieves them at the start of each game.

## 📚 How the Database Works

The game uses a MySQL database to store high scores:
- When you achieve a new high score, it gets saved in the `scores` table.
- When starting a new game, it retrieves the highest score from the database so you know what you're up against!

## 🤖 Contributing

Feel free to fork this repo, make changes, and submit pull requests! Just remember—no biting your own tail!

## 📄 License

This project is licensed under the MIT License—because sharing is caring!

---

Happy gaming! May your snake grow long and your scores be high! 🐍✨
