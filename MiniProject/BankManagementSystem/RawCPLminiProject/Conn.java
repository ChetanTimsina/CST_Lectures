import java.sql.*;  

public class Conn{
    Connection connection;
    Statement statement;
    public Conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/bankmanagementsystem",
                "root",
				"");
			//statement =connection.createStatement(); 
			System.out.println("Connection Successful");
            
        }catch(Exception e){ 
            System.out.println("Error in connecton");
        }  
    }  
}  