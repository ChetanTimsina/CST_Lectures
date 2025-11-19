package Info;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomePage extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldUserName;
    private JPasswordField textFieldPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomePage frame = new HomePage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public HomePage() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C://Users//kalay//OneDrive//Desktop//management//images//login.png");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setOpaque(false); // Make the panel transparent to show the background

        JLabel lblNewLabel = new JLabel("User Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

        JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));

        textFieldUserName = new JTextField();
        textFieldUserName.setColumns(10);
        textFieldUserName.setBackground(Color.LIGHT_GRAY);
        textFieldUserName.setForeground(Color.BLACK);

        textFieldPassword = new JPasswordField();
        textFieldPassword.setColumns(10);
        textFieldPassword.setBackground(Color.LIGHT_GRAY);
        textFieldPassword.setForeground(Color.BLACK);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = textFieldUserName.getText();
                String password = new String(textFieldPassword.getPassword());

                // Check login credentials
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    String url = "jdbc:mysql://localhost:3306/electricity_db;";
                    String username = "root";
                    String dbPassword = "";

                    Connection connection = DriverManager.getConnection(url, username, dbPassword);
                    stmt = connection.createStatement();
                    String query = "SELECT * FROM customers WHERE name = '" + userName + "' AND password = '" + password + "'";
                    rs = stmt.executeQuery(query);

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    BillGenerator frame = new BillGenerator();
                                    frame.setVisible(true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password!");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));

        JButton btnSignin = new JButton("Signin");
        btnSignin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            SignUp frame = new SignUp();
                            frame.setVisible(true);
                            setVisible(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        btnSignin.setFont(new Font("Tahoma", Font.BOLD, 12));

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(150)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(btnSignin, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblNewLabel)
                                .addComponent(lblNewLabel_1))
                            .addGap(18)
                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(textFieldPassword)
                                .addComponent(textFieldUserName))))
                    .addContainerGap(150, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(100)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(textFieldUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_1)
                        .addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnLogin)
                        .addComponent(btnSignin))
                    .addContainerGap(100, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 600, 50);
        contentPane.add(tabbedPane);

        JButton btnAbout = new JButton("About");
        btnAbout.setFocusable(false);
        btnAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showImage("C:/Users/kalay/OneDrive/Desktop/management/images/About.png");
            }
        });
        tabbedPane.addTab("About", null, btnAbout, null);

        JButton btnContact = new JButton("Contact");
        btnContact.setFocusable(false);
        btnContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showImage("C:/Users/kalay/OneDrive/Desktop/management/images/contact.png");
            }
        });
        tabbedPane.addTab("Contact", null, btnContact, null);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        tabbedPane.addTab("Exit", null, btnExit, null);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(75)
                    .addComponent(panel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addGap(75))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }

    private void showImage(String imagePath) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(0, 0, 900, 500);
        frame.setResizable(false);
        JPanel imagePanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(new ImageIcon(imagePath));
        imagePanel.add(label, BorderLayout.CENTER);

        JButton btnBack = new JButton("Back");
        btnBack.setFocusable(false);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        imagePanel.add(btnBack, BorderLayout.SOUTH);

        frame.getContentPane().add(imagePanel);
        frame.setVisible(true);
    }
}
