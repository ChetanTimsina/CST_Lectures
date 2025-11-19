/*Game interacts with the database to store and retrieve high scores,
 maintaining resource efficiency and robustness.*/

package snakegame;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3307/snake_game_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
//insert high score into the database
    public void insertScore(int score) throws SQLException {
        String query = "INSERT INTO high_scores (score) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, score);
            preparedStatement.executeUpdate();
        }
    }
 // Get the highest score from the database
    public int getHighScore() throws SQLException {
        int highScore = 0;
        String query = "SELECT MAX(score) AS high_score FROM high_scores";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                highScore = resultSet.getInt("high_score");
            }
        }
        return highScore;
    }
 // Close the database connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
