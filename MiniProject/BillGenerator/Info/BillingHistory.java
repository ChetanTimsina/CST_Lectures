package Info;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class BillingHistory extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private String customerID;

    /**
     * Create the frame.
     */
    public BillingHistory(String customerID) {
        setResizable(false);
        this.customerID = customerID;
        setTitle("Billing History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C://Users//kalay//OneDrive//Desktop//management//images//history.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblCustomerID = new JLabel("Customer ID: " + customerID);
        lblCustomerID.setFont(new Font("Tahoma", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane();
        
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBackToBillDetails();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblCustomerID)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblCustomerID)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnBack)
                    .addContainerGap())
        );

        table = new JTable();
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);

        loadBillingHistory(customerID);
    }

    private void loadBillingHistory(String customerID) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Billing Month");
        model.addColumn("Consumed Units");
        model.addColumn("Total Charge (Nu.)");
        model.addColumn("Bill Date");
        model.addColumn("Due Date");

        try {
            String url = "jdbc:mysql://localhost:3306/electricity_db;";
            String username = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT billing_month, consumed_units, total_charge, bill_date, due_date FROM bills WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customerID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("billing_month"));
                row.add(resultSet.getString("consumed_units"));
                row.add(resultSet.getString("total_charge"));
                row.add(resultSet.getString("bill_date"));
                row.add(resultSet.getString("due_date"));
                model.addRow(row);
            }

            table.setModel(model);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private void goBackToBillDetails() {
        // Close the current BillingHistory frame and open the BillDetails frame
        dispose(); // Close the current frame
        BillDetails billDetails = new BillDetails(customerID, "", 0, 0.0, "", "");
        billDetails.setVisible(true);
    }
}
