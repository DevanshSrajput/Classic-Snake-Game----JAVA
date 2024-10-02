# Snake Game

This is a simple implementation of the classic Snake game using Java and the Swing framework. The game allows players to control a snake that grows in length as it consumes food, while avoiding collisions with itself and the game boundaries.

## Features

- **Basic Gameplay**: Control the snake using arrow keys to eat food and grow in length.
- **Game Over Condition**: The game ends if the snake collides with itself or the boundaries of the game area.
- **Score Tracking**: The current score is displayed based on the length of the snake, along with a high score that persists across sessions.
- **Restart Functionality**: Players can restart the game after a game over by pressing the "R" key.
- **Exit Option**: Quit the game by pressing the "Q" key.

## Requirements

To run this program, you need:

- Java Development Kit (JDK) installed on your machine.
- An IDE or text editor to compile and run Java code (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code).

## How to Run

1. Clone or download this repository to your local machine.
2. Open a terminal or command prompt and navigate to the directory containing the `SnakeGame.java` file.
3. Compile the program using:
   ```bash
   javac SnakeGame.java
   ```
4. Run the compiled program using:
   ```bash
   java SnakeGame
   ```

## Controls

- **Arrow Keys**: Control the direction of the snake (Up, Down, Left, Right).
- **R**: Restart the game after a game over.
- **Q**: Quit the game.

## Game Logic Overview

The game's logic is implemented in a single class called `SnakeGame`, which handles:

- Rendering of the game components (snake, food, score).
- Movement of the snake based on user input.
- Collision detection for eating food and checking for game over conditions.

## Code Structure

The main components of the code include:

- **Tile Class**: Represents each segment of the snake and food on the board.
- **Game Loop**: A timer that updates the game state at regular intervals.
- **Collision Detection**: Checks if the snake has eaten food or collided with itself or walls.

## Future Updates 

I plan on adding sounds to this game when the snake eats or dies.
Please feel free to add the sound feature, I will work on this project again after my End-Sem Exams. 

**Package Required**:
- javax.sound.sampled.*;
- java.io.File;
- java.io.IOException;
