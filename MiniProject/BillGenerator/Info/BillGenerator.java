package Info;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BillGenerator extends JFrame {

    private JPanel contentPane;
    private JTextField txtCustomerID;
    private JComboBox<String> comboBoxBillingMonth; 
    private JTextField txtConsumedUnits;
    Statement stmt = null;
    ResultSet rs = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BillGenerator frame = new BillGenerator();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BillGenerator() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C://Users//kalay//OneDrive//Desktop//management//images//generate.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Customer ID:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

        txtCustomerID = new JTextField();
        txtCustomerID.setColumns(18);

        JLabel lblBillingMonth = new JLabel("Billing Month:");
        lblBillingMonth.setFont(new Font("Tahoma", Font.BOLD, 18));

        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        comboBoxBillingMonth = new JComboBox<>(months);

        JLabel lblConsumedUnits = new JLabel("Consumed Units:");
        lblConsumedUnits.setFont(new Font("Tahoma", Font.BOLD, 18));

        txtConsumedUnits = new JTextField();
        txtConsumedUnits.setColumns(10);

        JButton btnGenerateBill = new JButton("Generate Bill");
        btnGenerateBill.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnGenerateBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String url = "jdbc:mysql://localhost:3306/electricity_db;";
                    String username = "root";
                    String dbPassword = "";

                    Connection connection = DriverManager.getConnection(url, username, dbPassword);
                    stmt = connection.createStatement();
                    String query="SELECT * FROM customers";
                    rs=stmt.executeQuery(query);
                    boolean entered = false;
                    while(rs.next()){ 
                        if(txtCustomerID.getText().equals(Integer.toString(rs.getInt("customer_id")))){
                            entered = true;
                            generateBill();
                        }
                    }
                    if(!entered){
                    JOptionPane.showMessageDialog(null, "Customer ID not found");
                    }
                   
                }
                catch(Exception es){
                    System.out.println("Display-Statement cannot be executed"+es);
                }
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBackToHomepage();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(29)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel)
                        .addComponent(lblBillingMonth)
                        .addComponent(lblConsumedUnits))
                    .addGap(38)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(txtConsumedUnits, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addComponent(comboBoxBillingMonth, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addComponent(txtCustomerID, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                    .addGap(139))
                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addContainerGap(120, Short.MAX_VALUE)
                    .addComponent(btnBack)
                    .addGap(18)
                    .addComponent(btnGenerateBill)
                    .addGap(155))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(27)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(txtCustomerID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblBillingMonth)
                        .addComponent(comboBoxBillingMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblConsumedUnits)
                        .addComponent(txtConsumedUnits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnGenerateBill)
                        .addComponent(btnBack))
                    .addGap(17))
        );
        contentPane.setLayout(gl_contentPane);
    }

    private void generateBill() {
        String customerID = txtCustomerID.getText();
        String billingMonth = (String) comboBoxBillingMonth.getSelectedItem();
        String consumedUnitsStr = txtConsumedUnits.getText();

        if (customerID.isEmpty() || billingMonth.isEmpty() || consumedUnitsStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
            return;
        }

        try {
            int consumedUnits = Integer.parseInt(consumedUnitsStr);
            double totalCharge = calculateTotalCharge(consumedUnits);

            String url = "jdbc:mysql://localhost:3306/electricity_db;";
            String username = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, username, password);

            String insertSql = "INSERT INTO bills (customer_id, billing_month, consumed_units, total_charge, bill_date, due_date) VALUES (?, ?, ?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY))";
            PreparedStatement statement = connection.prepareStatement(insertSql);
            statement.setString(1, customerID);
            statement.setString(2, billingMonth);
            statement.setInt(3, consumedUnits);
            statement.setDouble(4, totalCharge);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Bill generated successfully!");

                // Get the current date for billDate and calculate dueDate
                String billDate = java.time.LocalDate.now().toString();
                String dueDate = java.time.LocalDate.now().plusDays(30).toString();

                // Display the BillDetails frame
                new BillDetails(customerID, billingMonth, consumedUnits, totalCharge, billDate, dueDate).setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to generate bill!");
            }

            statement.close();
            connection.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid consumed units value or check for other issues.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private double calculateTotalCharge(int consumedUnits) {
        double totalCharge = 0.0;

        if (consumedUnits <= 100) {
            totalCharge = 0.0;
        } else if (consumedUnits <= 300) {
            totalCharge = (consumedUnits - 100) * 2.66;
        } else if (consumedUnits <= 500) {
            totalCharge = (200 * 2.66) + ((consumedUnits - 300) * 1.75);
        } else {
            totalCharge = (200 * 2.66) + (200 * 1.75) + ((consumedUnits - 500) * 1.96);
        }

        return totalCharge;
    }

    private void goBackToHomepage() {
        // Close the current BillGenerator frame and open the HomePage frame
        dispose(); // Close the current frame
        HomePage homePage = new HomePage(); // Assuming HomePage is the class name for the homepage
        homePage.setVisible(true);
    }
}
