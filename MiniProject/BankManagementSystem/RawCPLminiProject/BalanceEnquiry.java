
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BalanceEnquiry extends JFrame implements ActionListener {

    private final String pin;
    private final JLabel balanceLabel;
	Connection connection;
	PreparedStatement preparedstatement;
	ResultSet resultSet;

    public BalanceEnquiry(String pin) {
        this.pin = pin;

        ImageIcon atmIcon = new ImageIcon(ClassLoader.getSystemResource("atm2.png"));
        Image scaledImage = atmIcon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, 1550, 830);
        add(backgroundLabel);

        JLabel balanceTitleLabel = new JLabel("Current Balance Nu: ");
        balanceTitleLabel.setForeground(Color.WHITE);
        balanceTitleLabel.setFont(new Font("System", Font.BOLD, 16));
        balanceTitleLabel.setBounds(430, 180, 700, 35);
        backgroundLabel.add(balanceTitleLabel);

        balanceLabel = new JLabel();
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("System", Font.BOLD, 16));
        balanceLabel.setBounds(430, 220, 400, 35);
        backgroundLabel.add(balanceLabel);

        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);

        updateBalance();
    }

    private void updateBalance() {
        
        try 
		{	
			int balance=0;
			//Conn connection = new Conn();
			connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3307/bankmanagementsystem",
                            "root",
                            "");
							
            String q="SELECT * FROM bank WHERE pin = ?";
			preparedstatement=connection.prepareStatement(q);
			preparedstatement.setString(1,pin);
			resultSet=preparedstatement.executeQuery();
			
            while (resultSet.next()) {
                if (resultSet.getString("mode").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
				
            }
            balanceLabel.setText(String.valueOf(balance));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new MainClass(pin);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
