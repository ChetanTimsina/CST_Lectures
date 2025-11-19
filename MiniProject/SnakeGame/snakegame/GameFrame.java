/*GameFrame class sets up the main window for the game, adding the 
GamePanel, and configuring the frame properties.*/ 
package snakegame;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    GameFrame(int delay) {
        this.add(new GamePanel(delay)); // Pass the delay to GamePanel 
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Prevent the user from resizing the frame
        this.setResizable(false);
        this.pack();
        // Make the frame visible on the screen
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}

