import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener{

    String pin,amount;
    TextField textField;
	Connection connection;
	Statement statement;
	ResultSet resultSet;
    JButton b1, b2;
    public Withdrawl(String pin){
        this.pin=pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS Nu.10,000");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460,180,700,35);
        l3.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460,220,400,35);
        l3.add(label2);


        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,260,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(textField);

        b1 = new JButton("WITHDRAW");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
		b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700,406,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
		b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1) {
            try {
				int balance = 0;
				String amount = textField.getText();
						connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3307/bankmanagementsystem",
						"root",
						"");
						statement =connection.createStatement();
						String q2="select * from bank where pin = '" + pin + "'";
						statement =connection.createStatement();
						resultSet=statement.executeQuery(q2);
                    while (resultSet.next()) {
                        if (resultSet.getString("mode").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                if (textField.getText().equals("")) {
					// if amount is not entered 
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to withdraw");
                } 
				else if(Integer.parseInt(amount)>10000){
					// if entered amount is more than ten thousand
					JOptionPane.showMessageDialog(null, "Please enter the Amount not more than 10000");
				}
				else if(balance < Integer.parseInt(amount)){
					// if remaining balance is less than  entered amount
                    JOptionPane.showMessageDialog(null, "Insuffient Balance");
                    return;
				}
				else{
					String w="Withdraw"; 
					String q1="insert into bank values('" + pin + "', '" + amount + "', '"+w+"','')";
					statement.executeUpdate(q1);
					JOptionPane.showMessageDialog(null, "Nu. " + amount + " Debited Successfully");
				}
                setVisible(false);
                new MainClass(pin);
				
            
			} catch (Exception E) {
				System.out.println(E.getMessage());
            }
        } else if (e.getSource()==b2) {
            setVisible(false);
            new MainClass(pin);
        }
    }
    public static void main(String[] args) {
        new Withdrawl("");
    }
}
