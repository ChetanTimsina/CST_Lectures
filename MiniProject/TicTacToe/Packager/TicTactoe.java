package Packager;
/*
 
  Tic Tac Toe game implementation using Java Swing.
  Allows two players to play the game, tracks wins, and displays results.
  Includes database integration to store player information and game results.
  Uses sound effects for button clicks and win/draw events.
 
  This program provides a graphical user interface (GUI) for playing Tic Tac Toe
  with two players. Players can enter their names, choose colors, and start the game.
  The game board consists of a 3x3 grid of buttons, and players take turns marking
  cells with 'X' or 'O' symbols. The game tracks the number of wins for each player
  and displays the results on a separate tab.
 
  Database Integration:
  The program connects to a MySQL database to store player information and game results.
  It includes methods for adding players to the database, updating player wins, and
  retrieving player information by name. The database schema includes a 'players' table
  with columns for player ID, name, chosen color, and number of wins.
 
  Sound Effects:
  Sound effects are used to enhance the user experience. A sound effect is played
  when a button on the game board is clicked, and a different sound effect is played
  when a player wins or the game ends in a draw. These sound effects are loaded from
  audio files and played using the Java Sound API.
 
  Usage:
  To play the game, run the main method of the TicTactoe class. This will open a GUI
  window where players can enter their names, choose colors, and start the game. The
  game board allows players to click on empty cells to make their moves. The game
  continues until a player wins or the game ends in a draw. Players can replay the
  game, return to the player info screen, or exit the program using the buttons
  provided on the result tab.

*/

import javax.swing.*; // Import the Swing package for GUI components.
import java.awt.*; // Import the AWT package for layout managers and other GUI components.
import java.awt.event.ActionEvent; // Import the AWT event package for action events.
import java.awt.event.ActionListener; // Import the AWT event package for action listeners.
import java.io.File; // Import the IO package for file handling.
import java.io.IOException; // Import the IO package for handling IO exceptions.
import javax.imageio.ImageIO; // Import the ImageIO class for image handling.
import javax.sound.sampled.AudioInputStream; // Import the sound package for audio input streams.
import javax.sound.sampled.AudioSystem; // Import the sound package for the audio system.
import javax.sound.sampled.Clip; // Import the sound package for audio clips.
import java.sql.*; // Import the SQL package for database connectivity.

// The TicTacToe class represents a simple Tic Tac Toe game with GUI using Swing.
public class TicTactoe extends JFrame implements ActionListener {

    // GUI components
    private JButton[][] buttons; // Represents the Tic Tac Toe 3x3 grid of buttons for the game board
    private char currentPlayer; // Tracks the current player ('X' or 'O')
    private JLabel statusLabel; // Displays the current status (e.g., which player's turn it is)
    private JTextField player1NameField, player2NameField; // Text fields for player names
    private JComboBox<String> player1ColorBox, player2ColorBox; // Combo boxes for player colors
    private String player1Name, player2Name; // Stores names of Player 1 and Player 2
    private Color player1Color, player2Color; // Stores colors of Player 1 and Player 2
    private int player1Wins, player2Wins; // Number of wins for Player 1 and Player 2
    private Connection connection; // Database connection
    private JTabbedPane tabbedPane; // Container for different panels (Player Info, Game, Result)

    // Constructor to set up the GUI.
    public TicTactoe() {
        setTitle("Tic Tac Toe"); // Title of the window
        setSize(600, 500); // Size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application when window is closed
        setLayout(new BorderLayout()); // Use BorderLayout for the main layout

        // Initialize database connection
        connectDB();

        // Create a tabbed pane to hold different panels
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // Create panels for player info, game, and result
        JPanel playerInfoPanel = createPlayerInfoPanel();
        JPanel gamePanel = createGamePanel();
        JPanel resultPanel = createResultPanel();

        // Add panels to tabbed pane with appropriate titles
        tabbedPane.addTab("Player Info", playerInfoPanel);
        tabbedPane.addTab("Game", gamePanel);
        tabbedPane.addTab("Result", resultPanel);

        // Customize tabbed pane appearance. Set the background color for each tab
        tabbedPane.setMaximumSize(new Dimension(400, 400));
        tabbedPane.setBackgroundAt(0, new Color(173, 216, 230)); // Light blue
        tabbedPane.setBackgroundAt(1, new Color(144, 238, 144)); // Light green
        tabbedPane.setBackgroundAt(2, new Color(255, 228, 181)); // Light yellow

        // Make the frame visible
        setVisible(true);
    }

    // Method to establish database connection
    private void connectDB() {
        try {
            // Establish connection with MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/ttt", "root", "");
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            // Handle database connection error
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    // Method to add a player to the database
    private void addPlayer(String name, String color) {
        try {
            // Prepare SQL query to insert player into database
            String query = "INSERT INTO players (name, color) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, color);
            statement.executeUpdate();
            System.out.println("Player added to database: " + name);
        } catch (SQLException e) {
            // Handle database insertion error
            System.out.println("Error adding player to database: " + e.getMessage());
        }
    }

    // Method to update player's wins in the database
    private void updatePlayerWins(String name) {
        try {
            // Prepare SQL query to update player's wins
            String query = "UPDATE players SET wins = wins + 1 WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Player wins updated: " + name);
        } catch (SQLException e) {
            // Handle database update error
            System.out.println("Error updating player wins: " + e.getMessage());
        }
    }

    // Method to convert color name to Color object
    private Color getColorFromSelection(String colorName) {
        switch (colorName) {
            case "Blue":
                return Color.BLUE;
            case "Red":
                return Color.RED;
            case "Green":
                return Color.GREEN;
            case "White":
                return Color.WHITE;
            case "Black":
                return Color.BLACK;
            default:
                return Color.BLACK;
        }
    }

    // Method to retrieve player from the database by name
    private Player getPlayerByName(String name) {
        try {
            // Prepare SQL query to select player by name
            String query = "SELECT * FROM players WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // If player is found, create Player object with retrieved data
                int id = resultSet.getInt("id");
                String color = resultSet.getString("color");
                int wins = resultSet.getInt("wins");
                return new Player(id, name, color, wins);
            }
        } catch (SQLException e) {
            // Handle database retrieval error
            System.out.println("Error retrieving player: " + e.getMessage());
        }
        return null;
    }

    // Method to create the panel for entering player information
    private JPanel createPlayerInfoPanel() {
        JPanel panel = new JPanel() {
            Image img;

            {
                try {
                    // Load background image for the panel
                    img = ImageIO.read(new File("Image\\loginn.png"));
                    setVisible(true);
                } catch (IOException e) {
                    // Handle image loading error
                    System.out.println("Cannot load the image...");
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw background image if loaded successfully
                if (img != null) {
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Configure panel layout and borders
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Use GridLayout for the input fields
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Create components for player information entry
        // Player 1 input fields
        JLabel player1Label = new JLabel("Player 1 Name:");
        player1NameField = new JTextField();
        JLabel player1ColorLabel = new JLabel("Player 1 Color:");
        player1ColorBox = new JComboBox<>(new String[] { "Blue", "Red", "Green", "White", "Black" });

        // Player 2 input fields
        JLabel player2Label = new JLabel("Player 2 Name:");
        player2NameField = new JTextField();
        JLabel player2ColorLabel = new JLabel("Player 2 Color:");
        player2ColorBox = new JComboBox<>(new String[] { "Blue", "Red", "Green", "White", "Black" });

        // Start button to begin the game
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> startGame());

        // Add components to the panel
        panel.add(player1Label);
        panel.add(player1NameField);
        panel.add(player1ColorLabel);
        panel.add(player1ColorBox);
        panel.add(player2Label);
        panel.add(player2NameField);
        panel.add(player2ColorLabel);
        panel.add(player2ColorBox);
        panel.add(new JLabel()); // Empty cell for alignment
        panel.add(startButton);

        return panel;
    }

    // In the startGame() method, add validation to ensure both player names are
    // entered before starting the game
    private void startGame() {
        // Retrieve player names and colors from input fields
        player1Name = player1NameField.getText();
        player2Name = player2NameField.getText();
        player1Color = getColorFromSelection((String) player1ColorBox.getSelectedItem());
        player2Color = getColorFromSelection((String) player2ColorBox.getSelectedItem());

        // Check if both player names are entered
        if (player1Name.isEmpty() || player2Name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter names for both players.");
            return;
        }

        // Add players to the database
        addPlayer(player1Name, (String) player1ColorBox.getSelectedItem());
        addPlayer(player2Name, (String) player2ColorBox.getSelectedItem());

        // Retrieve player wins from the database
        player1Wins = getPlayerByName(player1Name).getWins();
        player2Wins = getPlayerByName(player2Name).getWins();

        // Switch to the game panel
        tabbedPane.setSelectedIndex(1);
        statusLabel.setText(player1Name + "'s turn");

        // Play a sound effect when the game starts
        playSound("Sounds\\start.wav");

    }

    // Modify the method that creates the game panel to disable the tab until both
    // players have entered their names
    private JPanel createGamePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize the 3x3 grid of buttons
        JPanel gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(3, 3, 5, 5));

        buttons = new JButton[3][3]; // Initialize the array of buttons
        currentPlayer = 'X'; // Set the initial player as 'X'

        // Create buttons for each cell in the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 65));
                buttons[i][j].addActionListener(this);
                buttons[i][j].setBackground(Color.ORANGE);
                gameBoard.add(buttons[i][j]);
            }
        }

        // Create status label for displaying game status
        statusLabel = new JLabel("Player " + currentPlayer + "'s turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components to the game panel
        panel.add(statusLabel, BorderLayout.SOUTH);
        panel.add(gameBoard, BorderLayout.CENTER);

        // Disable the game panel until both players have entered their names
        panel.setEnabled(false);

        return panel;
    }

    // Method to create the panel for displaying game result
    private JPanel createResultPanel() {
        JPanel panel = new JPanel() {
            Image img = null;

            {
                try {
                    // Load background image for the panel
                    img = ImageIO.read(new File(
                            "Image\\Result.jpg"));
                } catch (IOException e) {
                    // Handle image loading error
                    System.out.println("Cannot load the image...");
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw background image if loaded successfully
                if (img != null) {
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Configure panel layout and borders
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components for displaying game result
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(resultLabel);

        JLabel comparisonLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(comparisonLabel);

        JButton replayButton = new JButton("Replay");
        replayButton.addActionListener(e -> replayGame());
        panel.add(replayButton);

        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> goHome());
        panel.add(homeButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        return panel;
    }

    // ActionListener method for handling button clicks
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        int row = -1, col = -1;
        // Find the row and column of the clicked button in the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clickedButton) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // Check if the clicked cell is already occupied
        if (!clickedButton.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "This cell is already occupied! Please choose another one.");
            return;
        }

        // Play sound effect for button click
        playSound("Sounds\\pop.wav");

        // Set the text and color of the clicked button based on current player
        clickedButton.setText(String.valueOf(currentPlayer));
        clickedButton.setForeground(currentPlayer == 'X' ? player1Color : player2Color);

        // Check if the current player wins
        if (checkWin(row, col)) {
            // Play win sound effect
            playSound("Sounds\\suee.wav");
            // Handle player win
            playerWins();
        } else if (checkDraw()) {
            // Handle draw scenario
            gameDraw();
        } else {
            // Switch to the next player's turn
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            statusLabel.setText(getCurrentPlayerName() + "'s turn");
        }
    }

    // Method to handle player win
    private void playerWins() {
        String winnerName = getCurrentPlayerName();
        // Increment player's win count
        if (currentPlayer == 'X') {
            player1Wins++;
        } else {
            player2Wins++;
        }

        // Display win message
        JOptionPane.showMessageDialog(this, winnerName + " wins!");
        // Update result panel with win information
        updateResultPanel(winnerName + " wins!");
        // Update player's win count in the database
        updatePlayerWins(winnerName);
        // Switch to the result view
        tabbedPane.setSelectedIndex(2);
    }

    // Method to handle draw scenario
    private void gameDraw() {
        // Play draw sound effect
        playDrawSound();
        // Display draw message
        JOptionPane.showMessageDialog(this, "It's a draw!");

        // Update result panel with draw information
        updateResultPanel("It's a draw!");
        // Switch to the result view
        tabbedPane.setSelectedIndex(2);
    }

    // Method to update result panel with result information
    private void updateResultPanel(String resultText) {
        JPanel panel = (JPanel) tabbedPane.getComponentAt(2);
        JLabel resultLabel = (JLabel) panel.getComponent(0);
        JLabel comparisonLabel = (JLabel) panel.getComponent(1);

        resultLabel.setText(resultText);
        // Display player win counts in comparison label
        comparisonLabel.setText(player1Name + " wins: " + player1Wins + " | " + player2Name + " wins: " + player2Wins);
    }

    // Method to check if the current player wins
    private boolean checkWin(int row, int col) {
        String symbol = String.valueOf(currentPlayer);

        // Check for win in row
        if (buttons[row][0].getText().equals(symbol) &&
                buttons[row][1].getText().equals(symbol) &&
                buttons[row][2].getText().equals(symbol))
            return true;

        // Check for win in column
        if (buttons[0][col].getText().equals(symbol) &&
                buttons[1][col].getText().equals(symbol) &&
                buttons[2][col].getText().equals(symbol))
            return true;

        // Check for win in diagonals
        if (buttons[0][0].getText().equals(symbol) &&
                buttons[1][1].getText().equals(symbol) &&
                buttons[2][2].getText().equals(symbol))
            return true;
        if (buttons[0][2].getText().equals(symbol) &&
                buttons[1][1].getText().equals(symbol) &&
                buttons[2][0].getText().equals(symbol))
            return true;

        return false;
    }

    // Method to check if the game ends in a draw
    private boolean checkDraw() {
        // Check if all cells are occupied
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals(""))
                    return false;
            }
        }
        return true;
    }

    // Method to reset the game state
    private void resetGame() {
        currentPlayer = 'X';
        statusLabel.setText(player1Name + "'s turn");
        // Reset all buttons in the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(Color.ORANGE);
            }
        }
    }

    // Method to replay the game
    private void replayGame() {
        // Reset game state and switch to game view
        resetGame();
        tabbedPane.setSelectedIndex(1);
    }

    // Method to go back to the home view
    private void goHome() {
        // Reset game state and switch to player info view
        resetGame();
        tabbedPane.setSelectedIndex(0);
    }

    // Method to get the name of the current player
    private String getCurrentPlayerName() {
        return (currentPlayer == 'X') ? player1Name : player2Name;
    }

    // Method to play a sound effect
    public void playSound(String soundFileName) {
        try {
            // Load and play the sound effect file
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            // Handle sound playing error
            System.out.println("Error playing sound: " + ex.getMessage());
        }
    }

    // Method to play the "aww" sound effect for draw
    private void playDrawSound() {
        try {
            // Load and play the sound effect file for draw
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(
                            new File("Sounds\\Draw.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            // Handle sound playing error
            System.out.println("Error playing draw sound: " + ex.getMessage());
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
    }

    // Represents a player in the Tic Tac Toe game.
    public class Player {
        private int id; // Player ID
        private String name; // Player name
        private String color; // Player color
        private int wins; // Number of wins

        // Constructor to initialize a player object.
        public Player(int id, String name, String color, int wins) {
            this.id = id; // player's ID
            this.name = name; // player's name
            this.color = color; // player's chosen color
            this.wins = wins; // player's win count
        }

        // Gets the player's ID and returns the player's ID
        public int getId() {
            return id;
        }

        // Gets the player's name and the player's name
        public String getName() {
            return name;
        }

        // Gets the player's chosen color and the player's color
        public String getColor() {
            return color;
        }

        // Gets the player's win count and return the player's win count
        public int getWins() {
            return wins;
        }
    }
}