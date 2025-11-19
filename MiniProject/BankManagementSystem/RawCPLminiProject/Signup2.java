import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;  

public class Signup2 extends JFrame implements ActionListener {
    JComboBox comboBox3, comboBox4, comboBox5;
    JTextField textPan, textAadhar, textNum;
    JRadioButton r1, r2, e1, e2;
    JButton next;
    String formno;
	String dropdown1="";
	String dropdown2="";
	String dropdown3="";
	Connection connection;

    public Signup2(String formno) {
        super("APPLICATION FORM");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150, 5, 100, 100);
        add(image);

        this.formno = formno;

        JLabel l1 = new JLabel("Page 2 :-");
        l1.setFont(new Font("Raleway", Font.BOLD, 20));
        l1.setBounds(300, 30, 600, 30);
        add(l1);

        JLabel l2 = new JLabel("Additional Details");
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        l2.setBounds(300, 60, 600, 30);
        add(l2);

        JLabel l4 = new JLabel("Contact No: ");
        l4.setFont(new Font("Raleway", Font.BOLD, 18));
        l4.setBounds(100, 160, 100, 50);
        add(l4);

        textNum = new JTextField();
        textNum.setFont(new Font("Raleway", Font.BOLD, 18));
        textNum.setBounds(350, 160, 320, 20);
        add(textNum);

        JLabel l5 = new JLabel("Income: ");
        l5.setFont(new Font("Raleway", Font.BOLD, 18));
        l5.setBounds(100, 220, 100, 20);
        add(l5);

        String[] income = {"<1,50,000", "<2,50,000", "5,00,000", "Upto 10,00,000", "Above 10,00,000"};
        comboBox3 = new JComboBox(income);
        comboBox3.setBackground(new Color(252, 208, 76));
        comboBox3.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox3.setBounds(350, 220, 320, 20);
		comboBox3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				dropdown1 = (String) comboBox3.getSelectedItem();
				
			}
		});
        add(comboBox3);

        JLabel l6 = new JLabel("Qualification: ");
        l6.setFont(new Font("Raleway", Font.BOLD, 18));
        l6.setBounds(100, 270, 150, 20);
        add(l6);

        String[] educational = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others"};
        comboBox4 = new JComboBox(educational);
        comboBox4.setBackground(new Color(252, 208, 76));
        comboBox4.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox4.setBounds(350, 270, 320, 20);
		comboBox4.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				dropdown2 = (String) comboBox4.getSelectedItem();
				
			}
		});
        add(comboBox4);

        JLabel l7 = new JLabel("Occupation: ");
        l7.setFont(new Font("Raleway", Font.BOLD, 18));
        l7.setBounds(100, 340, 150, 20);
        add(l7);

        String[] occupation = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        comboBox5 = new JComboBox(occupation);
        comboBox5.setBackground(new Color(252, 208, 76));
        comboBox5.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox5.setBounds(350, 340, 320, 20);
		comboBox5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				dropdown3 = (String) comboBox5.getSelectedItem();
				
			}
		});
        add(comboBox5);

        JLabel l8 = new JLabel("Father's CID: ");
        l8.setFont(new Font("Raleway", Font.BOLD, 18));
        l8.setBounds(100, 390, 150, 20);
        add(l8);

        textPan = new JTextField();
        textPan.setFont(new Font("Raleway", Font.BOLD, 18));
        textPan.setBounds(350, 390, 320, 20);
        add(textPan);

        JLabel l9 = new JLabel("Mother's CID: ");
        l9.setFont(new Font("Raleway", Font.BOLD, 18));
        l9.setBounds(100, 440, 180, 20);
        add(l9);

        textAadhar = new JTextField();
        textAadhar.setFont(new Font("Raleway", Font.BOLD, 18));
        textAadhar.setBounds(350, 440, 320, 20);
        add(textAadhar);

        JLabel l10 = new JLabel("Senior Citizen: ");
        l10.setFont(new Font("Raleway", Font.BOLD, 18));
        l10.setBounds(100, 490, 180, 20);
        add(l10);

        r1 = new JRadioButton("Yes");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(new Color(252, 208, 76));
        r1.setBounds(350, 490, 100, 20);
        add(r1);

        r2 = new JRadioButton("No");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(new Color(252, 208, 76));
        r2.setBounds(460, 490, 100, 20);
        add(r2);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(r1);
        buttonGroup1.add(r2);

        JLabel l11 = new JLabel("Existing Account: ");
        l11.setFont(new Font("Raleway", Font.BOLD, 18));
        l11.setBounds(100, 540, 180, 20);
        add(l11);

        e1 = new JRadioButton("Yes");
        e1.setFont(new Font("Raleway", Font.BOLD, 14));
        e1.setBackground(new Color(252, 208, 76));
        e1.setBounds(350, 540, 100, 20);
        add(e1);

        e2 = new JRadioButton("No");
        e2.setFont(new Font("Raleway", Font.BOLD, 14));
        e2.setBackground(new Color(252, 208, 76));
        e2.setBounds(460, 540, 100, 20);
        add(e2);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(e1);
        buttonGroup2.add(e2);

        JLabel l12 = new JLabel("Form No: ");
        l12.setFont(new Font("Raleway", Font.BOLD, 14));
        l12.setBounds(700, 10, 100, 20);
        add(l12);

        JLabel l13 = new JLabel(formno);
        l13.setFont(new Font("Raleway", Font.BOLD, 14));
        l13.setBounds(760, 10, 60, 20);
        add(l13);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.setBounds(570, 600, 100, 30); // Adjusted size and position for better UI
        next.addActionListener(this);
        add(next);

        setLayout(null);
        setSize(850, 700); // Adjusted size for better UI
        setLocation(450, 80);
        getContentPane().setBackground(new Color(252, 208, 76));
        setVisible(true);
    }

@Override
public void actionPerformed(ActionEvent e) {
    //String inc = comboBox3.getItemAt(comboBox3.getSelectedIndex());//(String) comboBox3.getSelectedItem();
    //String edu = comboBox4.getItemAt(comboBox4.getSelectedIndex());//(String) comboBox4.getSelectedItem();
    //String occ = comboBox5.getItemAt(comboBox5.getSelectedIndex());//(String) comboBox5.getSelectedItem();
    String pan = textPan.getText();
    String aadhar = textAadhar.getText();
    String num = textNum.getText();
    String scitizen = r1.isSelected() ? "Yes" : (r2.isSelected() ? "No" : null);
    String eAccount = e1.isSelected() ? "Yes" : (e2.isSelected() ? "No" : null);

    try {
        if (pan.isEmpty() || aadhar.isEmpty() || num.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fill all the fields");
        } else {
			
            
			connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/bankmanagementsystem",
                "root",
				"");
			Statement statement =connection.createStatement();
            String q = "INSERT INTO Signup2 VALUES('" + num + "','" + dropdown1 + "','" + dropdown2 + "','" + dropdown3 + "','" + pan +"','"+aadhar+"','"+scitizen+"','"+eAccount+"')";
			statement.executeUpdate(q);
            new Signup3(formno);
            setVisible(false);
        }
    } catch (Exception E) {
        E.printStackTrace();
    }
}

    public static void main(String[] args) {
        new Signup2("");
    }
}