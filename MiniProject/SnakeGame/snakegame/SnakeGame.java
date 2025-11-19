/*This code implements a basic Snake game where the player controls the 
snake to eat apples, growing in length with each apple eaten. 
The game ends if the snake collides with its own body or the edges of the
window. The score is displayed at the top, and a "Game Over" message 
appears when the game ends.*/

package snakegame;

import javax.swing.JOptionPane;

public class SnakeGame {
    public static void main(String[] args) {
        int delay = selectDifficulty(); // Select difficulty level
        new GameFrame(delay); // Create and display the game frame
    }

    public static int selectDifficulty() {
        String[] options = {"Low", "Medium", "High"};
        int choice = JOptionPane.showOptionDialog(null, "Select Difficulty Level", "Difficulty",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0: return 150; // Low difficulty
            case 1: return 100; // Medium difficulty
            case 2: return 50;  // High difficulty
            default: return 100; // Default to medium difficulty
        }
    }
}


/*javac -cp .;"C:\Users\DELL\OneDrive\Desktop\miniproject\mysql-connector-j-8.4.0.jar" snakegame/*.java
java -cp .;"C:\Users\DELL\OneDrive\Desktop\miniproject\mysql-connector-j-8.4.0.jar" snakegame.SnakeGame */