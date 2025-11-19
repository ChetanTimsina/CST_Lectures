import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.sql.*;  

public class Signup extends JFrame implements ActionListener {
    JRadioButton r1,r2,m1,m2,m3;
    JButton next;
	Connection connection;
    JTextField textName ,textFname, textEmail,textAdd,textcity,textState,textPin,textCID;
    Random ran = new Random();
    long first4 =(ran.nextLong() % 9000L) +1000L;
    String first = " " + Math.abs(first4);
    public Signup(){
        super ("APPLICATION FORM");
		// loading image to Frame 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(25,10,100,100);
        add(image);

		// Jlabels 
        JLabel label1 = new JLabel("APPLICATION FORM NO."+ first);
        label1.setBounds(160,20,600,40);
        label1.setFont(new Font("Raleway",Font.BOLD,38));
        add(label1);

        JLabel label2 = new JLabel("Page 1");
        label2.setFont(new Font("Ralway",Font.BOLD, 22));
        label2.setBounds(330,70,600,30);
        add(label2);

        JLabel label3 = new JLabel("Personal Details");
        label3.setFont(new Font("Raleway", Font.BOLD,22));
        label3.setBounds(290,90,600,30);
        add(label3);

        JLabel labelName = new JLabel("Name :");
        labelName.setFont(new Font("Raleway", Font.BOLD, 18));
        labelName.setBounds(100,130,100,20);
        add(labelName);

        textName = new JTextField();
        textName.setFont(new Font("Raleway",Font.BOLD, 14));
        textName.setBounds(300,130,400,20);
        add(textName);

        JLabel labelfName = new JLabel("Father's Name :");
        labelfName.setFont(new Font("Raleway", Font.BOLD, 18));
        labelfName.setBounds(100,170,200,20);
        add(labelfName);

        textFname = new JTextField();
        textFname.setFont(new Font("Raleway",Font.BOLD, 14));
        textFname.setBounds(300,170,400,20);
        add(textFname);

        JLabel CID = new JLabel("CID No.");
        CID.setFont(new Font("Raleway", Font.BOLD, 18));
        CID.setBounds(100,250,200,20);
        add(CID);
		
		textCID = new JTextField();
        textCID.setFont(new Font("Raleway",Font.BOLD, 14));
        textCID.setBounds(300,250,400,20);
        add(textCID);

        JLabel labelG = new JLabel("Gender");
        labelG.setFont(new Font("Raleway", Font.BOLD, 18));
        labelG.setBounds(100,210,200,20);
        add(labelG);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Raleway", Font.BOLD,14));
        r1.setBackground(new Color(222,255,228));
        r1.setBounds(300,210,60,20);
        add(r1);

        r2 = new JRadioButton("Female");
        r2.setBackground(new Color(222,255,228));
        r2.setFont(new Font("Raleway", Font.BOLD,14));
        r2.setBounds(450,210,90,20);
        add(r2);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);

        JLabel labelEmail = new JLabel("Email address :");
        labelEmail.setFont(new Font("Raleway", Font.BOLD, 18));
        labelEmail.setBounds(100,290,200,20);
        add(labelEmail);

        textEmail = new JTextField();
        textEmail.setFont(new Font("Raleway",Font.BOLD, 14));
        textEmail.setBounds(300,290,400,20);
        add(textEmail);


        JLabel labelMs = new JLabel("Marital Status :");
        labelMs.setFont(new Font("Raleway", Font.BOLD, 18));
        labelMs.setBounds(100,330,200,20);
        add(labelMs);

        m1 = new JRadioButton("Married");
        m1.setBounds(300,330,100,30);
        m1.setBackground(new Color(222,255,228));
        m1.setFont(new Font("Raleway", Font.BOLD,14));
        add(m1);

        m2 = new JRadioButton("Unmarried");
        m2.setBackground(new Color(222,255,228));
        m2.setBounds(450,330,100,30);
        m2.setFont(new Font("Raleway", Font.BOLD,14));
        add(m2);

        m3 = new JRadioButton("Other");
        m3.setBackground(new Color(222,255,228));
        m3.setBounds(635,330,100,20);
        m3.setFont(new Font("Raleway", Font.BOLD,14));
        add(m3);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(m1);
        buttonGroup1.add(m2);
        buttonGroup1.add(m3);

        JLabel labelAdd = new JLabel("Dzongkhag :");
        labelAdd.setFont(new Font("Raleway", Font.BOLD, 18));
        labelAdd.setBounds(100,370,200,20);
        add(labelAdd);

        textAdd = new JTextField();
        textAdd.setFont(new Font("Raleway",Font.BOLD, 14));
        textAdd.setBounds(300,370,400,20);
        add(textAdd);

        JLabel labelCity = new JLabel("Dungkhag :");
        labelCity.setFont(new Font("Raleway", Font.BOLD, 18));
        labelCity.setBounds(100,410,200,20);
        add(labelCity);

        textcity = new JTextField();
        textcity.setFont(new Font("Raleway",Font.BOLD, 14));
        textcity.setBounds(300,410,400,20);
        add(textcity);

        JLabel labelPin = new JLabel("Gewog :");
        labelPin.setFont(new Font("Raleway", Font.BOLD, 18));
        labelPin.setBounds(100,450,200,20);
        add(labelPin);

        textPin = new JTextField();
        textPin.setFont(new Font("Raleway",Font.BOLD, 14));
        textPin.setBounds(300,450,400,20);
        add(textPin);

        JLabel labelstate = new JLabel("Chiwog :");
        labelstate.setFont(new Font("Raleway", Font.BOLD, 18));
        labelstate.setBounds(100,490,200,20);
        add( labelstate);

        textState = new JTextField();
        textState.setFont(new Font("Raleway",Font.BOLD, 14));
        textState.setBounds(300,490,400,20);
        add(textState);

        next = new JButton("Next");
        next.setFont(new Font("Raleway",Font.BOLD, 14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(620,540,80,20);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(new Color(222,255,228));
        setLayout(null);
        setSize(850,620);
        setLocation(360,40);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
		// to sdtore the information entered by users
        String formno = first;
        String name = textName.getText();
        String fname = textFname.getText();
        String cid = textCID.getText();
        String gender = null;
        if(r1.isSelected()){
            gender = "Male";
        }else if (r2.isSelected()){
            gender = "Female";
        }
        String email = textEmail.getText();
        String marital =null;
        if (m1.isSelected()){
            marital = "Married";
        } else if (m2.isSelected()) {
            marital = "Unmarried";
        } else if (m3.isSelected()) {
            marital = "Other";
        }

        String dzongkhag = textAdd.getText();
        String dungkhag = textcity.getText();
        String gewog = textPin.getText();
        String chiwog = textState.getText();

        try{
            if (textName.getText().equals("")){
				// if fileds are left empty
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            }
			else {
				// connecting to MySQL
				connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/bankmanagementsystem",
                "root",
				"");
				Statement statement =connection.createStatement();
				// inserting into signup table
                String q = "insert into signup values("+formno+", '"+name+"','"+fname+"',"+cid+",'"+gender+"','"+email+"','"+marital+"', '"+dzongkhag+"', '"+dungkhag+"','"+gewog+"','"+chiwog+"' )";
                statement.executeUpdate(q);
                new Signup2(formno);
                setVisible(false);
				
			}
        }
        catch (Exception E){
			System.out.println("AT signup");
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Signup();
    }
}