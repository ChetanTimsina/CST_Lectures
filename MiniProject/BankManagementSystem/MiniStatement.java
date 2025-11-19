import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener{
 
    JButton b1, b2;
    JLabel l1;
    MiniStatement(String pin){
        super("Mini Statement");
        getContentPane().setBackground(Color.WHITE);
        setSize(400,600);
        setLocation(20,20);
        
        l1 = new JLabel();
        add(l1);
        
        JLabel l2 = new JLabel("ATM Bank");
        l2.setBounds(150, 20, 100, 20);
        add(l2);
        
        JLabel l3 = new JLabel();
        l3.setBounds(20, 80, 300, 20);
        add(l3);
        
        JLabel l4 = new JLabel();
        l4.setBounds(20, 400, 300, 20);
        add(l4);
        
        try{
            Conn c = new Conn();
            ResultSet s = c.s.executeQuery("select * from login where pin = '"+pin+"'");
            while(s.next()){
                l3.setText("Card Number:    " + s.getString("cardno").substring(0, 4) + "XXXXXXXX" + rs.getString("cardno").substring(12));
            }
        }catch(Exception e){}
        	 
        try{
            int balance = 0;
            Conn c1  = new Conn();
            ResultSet s = c1.s.executeQuery("SELECT * FROM bank where pin = '"+pin+"'");
            while(s.next()){
                l1.setText(l1.getText() + "<html>"+s.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("mode") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if(s.getString("mode").equals("Deposit")){
                    balance += Integer.parseInt(s.getString("amount"));
                }else{
                    balance -= Integer.parseInt(s.getString("amount"));
                }
            }
            l4.setText("Your total Balance is Nu. "+balance);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        setLayout(null);
        b1 = new JButton("Exit");
        add(b1);
        
        b1.addActionListener(this);
        
        l1.setBounds(20, 140, 400, 200);
        b1.setBounds(20, 500, 100, 25);
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    
    public static void main(String[] args){
        new MiniStatement("").setVisible(true);
    }
    
}
