package Info;

import java.awt.EventQueue;
import javax.swing.*;

// import HomePage;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldID;
    private JTextField textFieldFirstName;
    private JTextField textFieldMiddleName;
    private JTextField textFieldLastName;
    private JTextField textFieldUsername;
    private JTextField textFieldPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SignUp frame = new SignUp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SignUp() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:/Users/kalay/OneDrive/Desktop/management/images/signing.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblID = new JLabel("Consumer Number:");
        lblID.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblID.setBounds(57, 71, 127, 13);
        contentPane.add(lblID);

        textFieldID = new JTextField();
        textFieldID.setBounds(194, 68, 143, 19);
        contentPane.add(textFieldID);
        textFieldID.setColumns(10);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblFirstName.setBounds(57, 116, 127, 13);
        contentPane.add(lblFirstName);

        textFieldFirstName = new JTextField();
        textFieldFirstName.setBounds(194, 113, 143, 19);
        contentPane.add(textFieldFirstName);
        textFieldFirstName.setColumns(18);

        JLabel lblMiddleName = new JLabel("Middle Name:");
        lblMiddleName.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblMiddleName.setBounds(57, 157, 127, 18);
        contentPane.add(lblMiddleName);

        textFieldMiddleName = new JTextField();
        textFieldMiddleName.setBounds(194, 154, 143, 19);
        contentPane.add(textFieldMiddleName);
        textFieldMiddleName.setColumns(18);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblLastName.setBounds(57, 194, 127, 13);
        contentPane.add(lblLastName);

        textFieldLastName = new JTextField();
        textFieldLastName.setBounds(194, 191, 143, 19);
        contentPane.add(textFieldLastName);
        textFieldLastName.setColumns(18);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblUsername.setBounds(57, 231, 127, 13);
        contentPane.add(lblUsername);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(194, 229, 143, 19);
        contentPane.add(textFieldUsername);
        textFieldUsername.setColumns(18);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblPassword.setBounds(57, 267, 127, 13);
        contentPane.add(lblPassword);

        textFieldPassword = new JTextField();
        textFieldPassword.setBounds(194, 265, 143, 19);
        contentPane.add(textFieldPassword);
        textFieldPassword.setColumns(18);

        JButton btnSignup1 = new JButton("Sign Up");
        btnSignup1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnSignup1.setBounds(424, 323, 85, 21);
        contentPane.add(btnSignup1);

        btnSignup1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUpUser();
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        btnBack.setBounds(0, 323, 85, 21);
        btnBack.addActionListener(e -> {
            dispose();
            EventQueue.invokeLater(() -> {
                try {
                    HomePage frame = new HomePage();
                    frame.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });
        contentPane.add(btnBack);
    }

    private void signUpUser() {
        String url = "jdbc:mysql://localhost:3306/electricity_db;";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String id = textFieldID.getText();
            String Cid= textFieldID.getText();
            String firstName = textFieldFirstName.getText();
            String middleName = textFieldMiddleName.getText();
            String lastName = textFieldLastName.getText();
            String passwordInput = textFieldPassword.getText();

            String insertSql = "INSERT INTO customers (customer_id,name, consumer_number, billing_unit, meter_number, current_reading, last_reading, password) VALUES (?,?, ?, '0', '', 0, 0, ?)";
            PreparedStatement statement = connection.prepareStatement(insertSql);
            statement.setString(1,Cid);
            statement.setString(2, firstName + " " + middleName + " " + lastName);
            statement.setString(3, id);
            statement.setString(4, passwordInput);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sign up Successful!");

            EventQueue.invokeLater(() -> {
                try {
                    HomePage frame = new HomePage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            setVisible(false);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}