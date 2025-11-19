package Info;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class BillDetails extends JFrame {

    private JPanel contentPane;
    String customerID;

    public BillDetails(String customerID, String billingMonth, int consumedUnits, double totalCharge, String billDate, String dueDate) {
        this.customerID = customerID;
        setTitle("Bill Details");
        setResizable(false);

        setBounds(100, 100, 450, 300);
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

        JLabel lblCustomerID = new JLabel("Customer ID:");
        lblCustomerID.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblCustomerIDValue = new JLabel(customerID);
        lblCustomerIDValue.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblBillingMonth = new JLabel("Billing Month:");
        lblBillingMonth.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblBillingMonthValue = new JLabel(billingMonth);
        lblBillingMonthValue.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblConsumedUnits = new JLabel("Consumed Units:");
        lblConsumedUnits.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblConsumedUnitsValue = new JLabel(String.valueOf(consumedUnits));
        lblConsumedUnitsValue.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblTotalCharge = new JLabel("Total Charge(Nu.):");
        lblTotalCharge.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblTotalChargeValue = new JLabel(String.format("%.2f", totalCharge));
        lblTotalChargeValue.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblBillDate = new JLabel("Bill Date:");
        lblBillDate.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblBillDateValue = new JLabel(billDate);
        lblBillDateValue.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblDueDate = new JLabel("Due Date:");
        lblDueDate.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblDueDateValue = new JLabel(dueDate);
        lblDueDateValue.setFont(new Font("Tahoma", Font.BOLD, 15));

        JButton btnHistory = new JButton("History");
        btnHistory.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBillingHistory();
                setVisible(false);
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBackToBillGenerator();
                setVisible(false);
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(30)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblCustomerID)
                        .addComponent(lblBillingMonth)
                        .addComponent(lblConsumedUnits)
                        .addComponent(lblTotalCharge)
                        .addComponent(lblBillDate)
                        .addComponent(lblDueDate))
                    .addGap(50)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblDueDateValue)
                        .addComponent(lblBillDateValue)
                        .addComponent(lblTotalChargeValue)
                        .addComponent(lblConsumedUnitsValue)
                        .addComponent(lblBillingMonthValue)
                        .addComponent(lblCustomerIDValue))
                    .addContainerGap(150, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addContainerGap(30, Short.MAX_VALUE)
                    .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(btnHistory, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                    .addGap(150))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(20)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCustomerID)
                        .addComponent(lblCustomerIDValue))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBillingMonth)
                        .addComponent(lblBillingMonthValue))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblConsumedUnits)
                        .addComponent(lblConsumedUnitsValue))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTotalCharge)
                        .addComponent(lblTotalChargeValue))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBillDate)
                        .addComponent(lblBillDateValue))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDueDate)
                        .addComponent(lblDueDateValue))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBack)
                        .addComponent(btnHistory))
                    .addContainerGap(25, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
    }

    private void openBillingHistory() {
        // Create an instance of the BillingHistory class and display it
        BillingHistory billingHistory = new BillingHistory(customerID);
        billingHistory.setVisible(true);
    }

    private void goBackToBillGenerator() {
        // Close the current BillDetails frame and open the BillGenerator frame
        dispose(); // Close the current frame
        BillGenerator billGenerator = new BillGenerator();
        billGenerator.setVisible(true);
    }
}
