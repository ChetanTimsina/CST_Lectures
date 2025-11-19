import java.sql.*;
import java.util.ArrayList;
import java.io.*;

public class Database {
    Connection con;
    Statement stmt;
    PreparedStatement ps;
    ResultSet rs;

    public void connect_db() {
        try {
            // For MySQL DB Connectivity
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musiclist", "root", "");
            System.out.println("Connected to Database!");
            stmt = con.createStatement(); // creating SQL statement
        } catch (Exception e) {
            System.out.println("Error in Connection. Check your database connectivity.");
            e.printStackTrace();
        }
    }

    // Method to insert data into the 'music' table from a file
    public void readFileAndInsertData() {
        String fileName = "assets/playlist.txt"; // Adjust the file name as needed
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    int SL_no = Integer.parseInt(data[0].trim());
                    String title = data[1].trim();
                    insertMusicData(SL_no, title);
                }
            }
            System.out.println("Data inserted from file successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file or inserting data into database.");
            e.printStackTrace();
        }
    }

    // Method to insert a single record into the 'music' table
    public void insertMusicData(int SL_no, String title) {
        try {
            // Establish database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musiclist", "root", "");

            // Prepare the SQL statement
            String query = "INSERT INTO music (SL_no, title ) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            // Set the values for the parameters
            ps.setInt(1, SL_no);
            ps.setString(2, title);
            

            // Execute the insert statement
            ps.executeUpdate();

            // Close the PreparedStatement and Connection
            ps.close();
            con.close();

            System.out.println("Inserted data into database: " + SL_no + ", " + title );
        } catch (SQLException e) {
            System.out.println("Error inserting data into database.");
            e.printStackTrace();
        }
    }
}
