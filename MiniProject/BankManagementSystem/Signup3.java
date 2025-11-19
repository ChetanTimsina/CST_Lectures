import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.sql.*;

public class Signup3 extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6;
    JButton s, c;
    String formno;
    Connection connection;

    public Signup3(String formno) {
        this.formno = formno;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150, 5, 100, 100);
        add(image);

        JLabel l1 = new JLabel("Page 3:");
        l1.setFont(new Font("Raleway", Font.BOLD, 20));
        l1.setBounds(280, 40, 400, 30);
        add(l1);

        JLabel l2 = new JLabel("Account Details");
        l2.setFont(new Font("Raleway", Font.BOLD, 20));
        l2.setBounds(280, 70, 400, 30);
        add(l2);

        JLabel l3 = new JLabel("Account Type:");
        l3.setFont(new Font("Raleway", Font.BOLD, 18));
        l3.setBounds(100, 140, 200, 20);
        add(l3);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r1.setBackground(new Color(215, 252, 252));
        r1.setBounds(100, 180, 150, 20);
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setBackground(new Color(215, 252, 252));
        r2.setBounds(350, 180, 300, 20);
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        r3.setBackground(new Color(215, 252, 252));
        r3.setBounds(100, 220, 250, 20);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        r4.setBackground(new Color(215, 252, 252));
        r4.setBounds(350, 220, 250, 20);
        add(r4);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);
        buttonGroup.add(r3);
        buttonGroup.add(r4);

        JLabel l4 = new JLabel("Card Number:");
        l4.setFont(new Font("Raleway", Font.BOLD, 18));
        l4.setBounds(100, 260, 200, 20);
        add(l4);

        JLabel l5 = new JLabel("(Your 16-digit Card Number)");
        l5.setFont(new Font("Raleway", Font.BOLD, 12));
        l5.setBounds(100, 290, 200, 20);
        add(l5);

        JLabel l6 = new JLabel("XXXX-XXXX-XXXX-4841");
        l6.setFont(new Font("Raleway", Font.BOLD, 18));
        l6.setBounds(330, 260, 250, 20);
        add(l6);

        JLabel l7 = new JLabel("(It would appear on atm card/cheque Book and Statements)");
        l7.setFont(new Font("Raleway", Font.BOLD, 12));
        l7.setBounds(330, 290, 500, 20);
        add(l7);

        JLabel l8 = new JLabel("PIN:");
        l8.setFont(new Font("Raleway", Font.BOLD, 18));
        l8.setBounds(100, 330, 200, 20);
        add(l8);

        JLabel l9 = new JLabel("XXXX");
        l9.setFont(new Font("Raleway", Font.BOLD, 18));
        l9.setBounds(330, 330, 200, 20);
        add(l9);

        JLabel l10 = new JLabel("(4-digit Password)");
        l10.setFont(new Font("Raleway", Font.BOLD, 12));
        l10.setBounds(100, 360, 200, 20);
        add(l10);

        JLabel l11 = new JLabel("Services Required:");
        l11.setFont(new Font("Raleway", Font.BOLD, 18));
        l11.setBounds(100, 400, 200, 20);
        add(l11);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(new Color(215, 252, 252));
        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c1.setBounds(100, 450, 200, 20);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(new Color(215, 252, 252));
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setBounds(350, 450, 200, 20);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(new Color(215, 252, 252));
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setBounds(100, 480, 200, 20);
        add(c3);

        c4 = new JCheckBox("EMAIL Alerts");
        c4.setBackground(new Color(215, 252, 252));
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setBounds(350, 480, 200, 20);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setBackground(new Color(215, 252, 252));
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setBounds(100, 520, 200, 20);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBackground(new Color(215, 252, 252));
        c6.setFont(new Font("Raleway", Font.BOLD, 16));
        c6.setBounds(350, 520, 200, 20);
        add(c6);

        JCheckBox c7 = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.", true);
        c7.setBackground(new Color(215, 252, 252));
        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBounds(100, 560, 600, 20);
        add(c7);

        JLabel l12 = new JLabel("Form No : ");
        l12.setFont(new Font("Raleway", Font.BOLD, 14));
        l12.setBounds(700, 10, 100, 20);
        add(l12);

        JLabel l13 = new JLabel(formno);
        l13.setFont(new Font("Raleway", Font.BOLD, 14));
        l13.setBounds(760, 10, 60, 20);
        add(l13);

        s = new JButton("Submit");
        s.setFont(new Font("Raleway", Font.BOLD, 14));
        s.setBackground(Color.BLACK);
        s.setForeground(Color.WHITE);
        s.setBounds(250, 580, 100, 20);
        s.addActionListener(this);
        add(s);

        c = new JButton("Cancel");
        c.setFont(new Font("Raleway", Font.BOLD, 14));
        c.setBackground(Color.BLACK);
        c.setForeground(Color.WHITE);
        c.setBounds(420, 580, 100, 20);
        c.addActionListener(this);
        add(c);

        getContentPane().setBackground(new Color(215, 252, 252));
        setSize(850, 650);
        setLayout(null);
        setLocation(400, 20);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accounttype = "";
        if (r1.isSelected()) {
            accounttype = "Saving Account";
        } else if (r2.isSelected()) {
            accounttype = "Fixed Deposit Account";
        } else if (r3.isSelected()) {
            accounttype = "Current Account";
        } else if (r4.isSelected()) {
            accounttype = "Recurring Deposit Account";
        }
	
		// using  Random to generate account number or card number and pin 
        Random ran = new Random();
        long first7 = (ran.nextLong() % 90000L) + 1409960000L;
        String cardnumber = "" + Long.toString(Math.abs(first7));

        long first3 = (ran.nextLong() % 9000L) + 1000L;
        String pin = "" + Long.toString(Math.abs(first3));

        String facility = "";
        if (c1.isSelected()) {
            facility = "ATM CARD ";
        }
        if (c2.isSelected()) {
            facility += "Internet Banking ";
        }
        if (c3.isSelected()) {
            facility += "Mobile Banking ";
        }
        if (c4.isSelected()) {
            facility += "EMAIL Alerts ";
        }
        if (c5.isSelected()) {
            facility += "Cheque Book ";
        }
        if (c6.isSelected()) {
            facility += "E-Statement ";
        }

        try {
            if (e.getSource() == s) {
				System.out.println("Submit button clicked");
				
                if (accounttype.equals("")) {
                    JOptionPane.showMessageDialog(null,"Fill all the fields");
                }
				else {
                    JOptionPane.showMessageDialog(null, "Card Number : " + cardnumber + "\n Pin : " + pin);
                    connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3307/bankmanagementsystem",
                            "root",
                            "");
                    Statement statement = connection.createStatement();
                    String q = "insert into signup3 values('" + formno + "','" + accounttype + "','" + facility + "','" + cardnumber + "','" + pin + "')";
                    String q1 = "insert into login values('" + formno + "','" + cardnumber + "','" + pin + "')";
                    statement.executeUpdate(q);
                    statement.executeUpdate(q1);

                    System.out.println("Connection Successful");
					setVisible(false);
					
                    new Deposit(pin);
					
                    
                }
            } else{
				System.out.println("Button clicked");
                System.exit(0);
            }
        }
		catch (Exception ex) {
			System.out.println("Error in connection");
            ex.printStackTrace();
        }
	}
	
    public static void main(String[] args) {
        new Signup3("");
    }
}