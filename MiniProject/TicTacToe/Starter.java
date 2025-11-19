import javax.swing.SwingUtilities;

import Packager.TicTactoe;

public class Starter {
     public static void main(String[] args) {
          // Run the application on the Event Dispatch Thread
          SwingUtilities.invokeLater(() -> {
               // Create an instance of TicTacToe and play a sound effect
               TicTactoe ticTacToe = new TicTactoe();
               ticTacToe.playSound("C:\\Users\\dell\\Downloads\\the.wav");
          });
     }
}
