import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;




public class CurrencyConverterApp1 extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JButton exitButton;
    private JComboBox<String> sourceCurrencyCombo;
    private JComboBox<String> targetCurrencyCombo;
    private JTextField amountField;
    private JButton convertButton;
    private JTextArea resultArea;
    private JComboBox<String> countryComboBox;
    private JTextField rateField;
    private JButton updateButton;
    private final HashMap<String, Double> saarcRates = new HashMap<>();

    public CurrencyConverterApp1() {
        setTitle("Currency Converter");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        createLoginUI();
        createSignupUI();
        createConverterUI();
        createAdminUI();
        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
        initializeRates();
    }

    private void createNavigationMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu navigationMenu = new JMenu("Navigation");
        JMenuItem backToLoginItem = new JMenuItem("Back");

        backToLoginItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Login");
            }
        });

        navigationMenu.add(backToLoginItem);
        menuBar.add(navigationMenu);
        setJMenuBar(menuBar);
    }

    private void createLoginUI() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        JMenuBar menuBar = new JMenuBar();
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog aboutDialog = new JDialog();
                aboutDialog.setTitle("About");
                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                textArea.setText("Overview\n" +
                    "A currency converter allows users to see the exchange rate between two different currencies.\n" +
                    "It shows how much one currency amount like Ngultrum is worth in another currency like Euros.\n" +
                    "The rates are continuously updated to reflect current market values due to fluctuations.\n" +
                    "Currency converters provide a quick and easy way for anyone needing to exchange money between currencies.\n" +
                    "AIM\n" +
                    "The main goal of this mini-project is to create a user-friendly graphical user interface (GUI) in order to enhance practical Java programming skills.\n" +
                    "In order to create a user-friendly and visually appealing interface which requires applying Java coding principles practically.\n" +
                    "Developing an appealing visual interface requires applying Java coding concepts and helping to solidify understanding of GUI development and improve overall Java coding abilities.\n" +
                    "A secondary objective is to build a program that enables currency conversion between two selected currencies using predefined exchange rates.\n" +
                    "Users can choose the currencies and input an amount for conversion.\n" +
                    "The program will calculate and display the equivalent value in the desired target currency.\n" +
                    "Objectives\n" +
                    "The primary goal involves crafting a responsive graphical user interface through Java Swing, JavaFX, or Java AWT.\n" +
                    "While adhering to object-oriented programming tenets such as encapsulation, inheritance, and polymorphism\n" +
                    "Following this, the focus shifts to incorporating event handling and input validation mechanisms to ensure a seamless user interaction\n" +
                    "This entails the integration of various graphical user interface components like buttons, text fields, and drop-down menus to facilitate user engagement.\n" +
                    "The secondary objective entails the establishment of a database infrastructure to house predetermined exchange rates and the formulation of conversion algorithms based on user-selected currencies and predetermined rates.\n" +
                    "Subsequently, the task involves seamlessly merging the conversion functionality with the graphical user interface, enabling users to select currencies and input amounts, and presenting the converted values clearly.\n" +
                    "Finally, robust error handling and input validation mechanisms are implemented alongside provisions for rate updates to enhance the overall reliability and accuracy of the system.\n" +
                    "Requirements\n" +
                    "- Notepad++ or VS code for running the code\n" +
                    "- Mysql or Xampp for storing data and doing query.\n" +
                    "- Use Command Prompt(cmd) to run the code if and only if you are using Notepad++ since code can be directly ran on VS code.");
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aboutDialog.dispose();
                    }
                });
                aboutDialog.setLayout(new BorderLayout());
                aboutDialog.add(new JScrollPane(textArea), BorderLayout.CENTER);
                aboutDialog.add(closeButton, BorderLayout.SOUTH);
                aboutDialog.pack();
                aboutDialog.setLocationRelativeTo(CurrencyConverterApp1.this);
                aboutDialog.setVisible(true);
            }
        });
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        JLabel welcomeLabel = new JLabel("Welcome to the Currency Converter");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.insets = new Insets(10, 10, 10, 10);
        loginPanel.add(welcomeLabel, g);

        JLabel usernameLabel = new JLabel("Username:");
        g.gridwidth = 1;
        g.gridx = 0;
        g.gridy = 1;
        loginPanel.add(usernameLabel, g);

        usernameField = new JTextField(15);
        g.gridx = 1;
        loginPanel.add(usernameField, g);

        JLabel passwordLabel = new JLabel("Password:");
        g.gridx = 0;
        g.gridy = 2;
        loginPanel.add(passwordLabel, g);

        passwordField = new JPasswordField(15);
        g.gridx = 1;
        loginPanel.add(passwordField, g);

        loginButton = new JButton("Login");
        g.gridx = 1;
        g.gridy = 3;
        g.insets = new Insets(20, 0, 0, 0);
        loginPanel.add(loginButton, g);

        signupButton = new JButton("Sign Up");
        g.gridx = 1;
        g.gridy = 4;
        g.insets = new Insets(10, 0, 0, 0);
        loginPanel.add(signupButton, g);

        exitButton = new JButton("Exit");
        g.gridx = 1;
        g.gridy = 5;
        g.insets = new Insets(10, 0, 0, 0);
        loginPanel.add(exitButton, g);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user_name = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (validateLogin(user_name, password)) {
                    createNavigationMenuBar();
                    if (user_name.equals("Admin")) {
                        cardLayout.show(mainPanel, "Admin");
                    } else {
                        cardLayout.show(mainPanel, "Converter");
                    }
                } else {
                    JOptionPane.showMessageDialog(CurrencyConverterApp1.this, "Invalid username or password.");
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SignUp");
            }
        });

        mainPanel.add(loginPanel, "Login");
    }

    private void createSignupUI() {
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        JLabel signupLabel = new JLabel("Create a New Account");
        signupLabel.setFont(new Font("Arial", Font.BOLD, 16));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.insets = new Insets(10, 10, 10, 10);
        signupPanel.add(signupLabel, g);

        JLabel usernameLabel = new JLabel("Username:");
        g.gridwidth = 1;
        g.gridx = 0;
        g.gridy = 1;
        signupPanel.add(usernameLabel, g);

        JTextField newUsernameField = new JTextField(15);
        g.gridx = 1;
        signupPanel.add(newUsernameField, g);

        JLabel passwordLabel = new JLabel("Password:");
        g.gridx = 0;
        g.gridy = 2;
        signupPanel.add(passwordLabel, g);

        JPasswordField newPasswordField = new JPasswordField(15);
        g.gridx = 1;
        signupPanel.add(newPasswordField, g);

        JButton createAccountButton = new JButton("Create Account");
        g.gridx = 1;
        g.gridy = 3;
        g.insets = new Insets(20, 0, 0, 0);
        signupPanel.add(createAccountButton, g);

        JButton backButton = new JButton("Back");
        g.gridx = 1;
        g.gridy = 4;
        g.insets = new Insets(10, 0, 0, 0);
        signupPanel.add(backButton, g);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Login");
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = newUsernameField.getText();
                String newPassword = new String(newPasswordField.getPassword());

                if (createAccount(newUsername, newPassword)) {
                    JOptionPane.showMessageDialog(CurrencyConverterApp1.this, "Account created successfully.");
                    cardLayout.show(mainPanel, "Login");
                } else {
                    JOptionPane.showMessageDialog(CurrencyConverterApp1.this, "Failed to create account.");
                }
            }
        });

        mainPanel.add(signupPanel, "SignUp");
    }

    private void createConverterUI() {
        JPanel converterPanel = new JPanel();
        converterPanel.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        JLabel sourceCurrencyLabel = new JLabel("Source Currency:");
        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(sourceCurrencyLabel, g);

        sourceCurrencyCombo = new JComboBox<>();
        g.gridx = 1;
        inputPanel.add(sourceCurrencyCombo, g);

        JLabel targetCurrencyLabel = new JLabel("Target Currency:");
        g.gridx = 0;
        g.gridy = 1;
        inputPanel.add(targetCurrencyLabel, g);

        targetCurrencyCombo = new JComboBox<>();
        g.gridx = 1;
        inputPanel.add(targetCurrencyCombo, g);

        JLabel amountLabel = new JLabel("Amount:");
        g.gridx = 0;
        g.gridy = 2;
        inputPanel.add(amountLabel, g);

        amountField = new JTextField(20); 
        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(amountField, g);

        convertButton = new JButton("Convert");
        g.gridx = 1;
        g.gridy = 3;
        inputPanel.add(convertButton, g);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrencies();
            }
        });

        converterPanel.add(inputPanel, BorderLayout.NORTH);
        converterPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        mainPanel.add(converterPanel, "Converter");
    }

    private void createAdminUI() {
        JPanel adminPanel = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        JLabel adminLabel = new JLabel("Admin Panel");
        adminLabel.setFont(new Font("Arial", Font.BOLD, 16));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.insets = new Insets(10, 10, 10, 10);
        adminPanel.add(adminLabel, g);

        JLabel countryLabel = new JLabel("Country:");
        g.gridwidth = 1;
        g.gridx = 0;
        g.gridy = 1;
        adminPanel.add(countryLabel, g);

        countryComboBox = new JComboBox<>(saarcRates.keySet().toArray(new String[0]));
        g.gridx = 1;
        adminPanel.add(countryComboBox, g);

        JLabel rateLabel = new JLabel("Rate in 1 USD:");
        g.gridx = 0;
        g.gridy = 2;
        adminPanel.add(rateLabel, g);

        rateField = new JTextField(20);
        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL; 
        adminPanel.add(rateField, g);

        updateButton = new JButton("Update Rate");
        g.gridx = 1;
        g.gridy = 3;
        g.insets = new Insets(20, 0, 0, 0);
        adminPanel.add(updateButton, g);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRate();
            }
        });

        mainPanel.add(adminPanel, "Admin");
    }

    private void initializeRates() {
        String url = "jdbc:mysql://localhost:3306/CurrencyConverterDB";
        String user = "root";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String query = "SELECT country, Rate_in_1USD FROM 1usd";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String country = rs.getString("country");
                double rate = rs.getDouble("Rate_in_1USD");
                saarcRates.put(country, rate);
            }

            sourceCurrencyCombo.setModel(new DefaultComboBoxModel<>(saarcRates.keySet().toArray(new String[0])));
            targetCurrencyCombo.setModel(new DefaultComboBoxModel<>(saarcRates.keySet().toArray(new String[0])));
            countryComboBox.setModel(new DefaultComboBoxModel<>(saarcRates.keySet().toArray(new String[0])));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validateLogin(String user_name, String password) {
        String url = "jdbc:mysql://localhost:3306/CurrencyConverterDB";
        String user = "root";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String query = "SELECT * FROM users WHERE user_name = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user_name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean createAccount(String user_name, String password) {
        String url = "jdbc:mysql://localhost:3306/CurrencyConverterDB";
        String user = "root";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String query = "INSERT INTO users (user_name, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user_name);
            stmt.setString(2, password);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void convertCurrencies() {
        String sourceCurrency = (String) sourceCurrencyCombo.getSelectedItem();
        String targetCurrency = (String) targetCurrencyCombo.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());

        double sourceRate = saarcRates.get(sourceCurrency);
        double targetRate = saarcRates.get(targetCurrency);
        double convertedAmount = amount * (targetRate / sourceRate);

        resultArea.setText(String.format("%.2f %s = %.2f %s", amount, sourceCurrency, convertedAmount, targetCurrency));
    }

    private void updateRate() {
        String country = (String) countryComboBox.getSelectedItem();
        double newRate = Double.parseDouble(rateField.getText());

        String url = "jdbc:mysql://localhost:3306/CurrencyConverterDB";
        String user = "root";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String query = "UPDATE 1usd SET Rate_in_1USD = ? WHERE country = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, newRate);
            stmt.setString(2, country);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                saarcRates.put(country, newRate);
                JOptionPane.showMessageDialog(this, "Rate updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Rate update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error. Rate update failed.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid rate format. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterApp1 app = new CurrencyConverterApp1();
            app.setVisible(true);
        });
    }
}
