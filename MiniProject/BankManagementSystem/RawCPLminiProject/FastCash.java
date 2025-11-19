import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.sql.*;

public class FastCash extends JFrame implements ActionListener {

    JLabel l1, l2;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JTextField t1;
    String pin,amount,accountno;
	Connection connection;
	Statement statement;
	ResultSet resultSet;

    FastCash(String pin,String accountno) {
        this.pin = pin;
		this.accountno=accountno;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 900, 800);
        add(l3);

        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("Nu 100");
        b2 = new JButton("Nu 500");
        b3 = new JButton("Nu 1000");
        b4 = new JButton("Nu 2000");
        b5 = new JButton("Nu 5000");
        b6 = new JButton("Nu 10000");
        b7 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(235, 100, 700, 35);
        l3.add(l1);

        b1.setBounds(170, 200, 150, 35);
        l3.add(b1);

        b2.setBounds(390, 200, 150, 35);
        l3.add(b2);

        b3.setBounds(170, 244, 150, 35);
        l3.add(b3);

        b4.setBounds(390, 244, 150, 35);
        l3.add(b4);

        b5.setBounds(170, 288, 150, 35);
        l3.add(b5);

        b6.setBounds(390, 288, 150, 35);
        l3.add(b6);

        b7.setBounds(290, 332, 150, 35);
        l3.add(b7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        setSize(900,800);
        setLocation(0, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = ((JButton)ae.getSource()).getText().substring(3); //k
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3307/bankmanagementsystem",
				"root",
				"");
				String q="Select * From bank where pin= '"+pin+"'";
				statement=connection.createStatement();
				resultSet=statement.executeQuery(q);
			
            int balance = 0;
            while (resultSet.next()) {
                if (resultSet.getString("mode").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
					try{
                    balance -= Integer.parseInt(resultSet.getString("amount"));
					}catch(Exception e){
						System.out.println("Error here in balanve");
					}
                }
            } 
			//String num = "17";
            if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insuffient Balance");
                return;
            }

            if (ae.getSource() == b7) {
                this.setVisible(false);
				new MainClass(pin).setVisible(true);
            }else{
				connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/bankmanagementsystem",
					"root",
					"");
				statement =connection.createStatement();
				
                statement.executeUpdate("insert into bank values('"+pin+"','"+amount+"','Withdrawl','"+accountno+"')");
                JOptionPane.showMessageDialog(null, "Nu. "+amount+" Debited Successfully");
                    
                setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new FastCash("pin", "accountno").setVisible(true);
    }
}