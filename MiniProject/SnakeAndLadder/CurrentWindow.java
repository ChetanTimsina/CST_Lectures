import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import Packager.Newwindow;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;


public class CurrentWindow extends JFrame implements ActionListener{
    Connection con;
    Statement stmt;
    PreparedStatement ps;
    ResultSet rs; 
    JLabel Namelabel, Passwordlabel;
    JButton login,Signin;
    JTextArea name;
    JPasswordField password;
    // List<User> users = new ArrayList<User>();
    // User user;
    public void connectdb(){
        try{
            //For MySQL DB Connectivity
          con=DriverManager.getConnection("jdbc:mysql://localhost:3307/userinfo", "root","");
          System.out.println("Connected to database");
          stmt=con.createStatement();
        }
        catch(Exception e){
            System.out.println("Error in Connection. Check your database connectivity."+e);
        }
    }
    CurrentWindow() {
        connectdb();
        Namelabel = new JLabel("Name: ");
        Passwordlabel = new JLabel("Password: ");
        login = new JButton("Login");
        login.addActionListener(this);
        Signin = new JButton("Sign in");
        Signin.addActionListener(this);
        name = new JTextArea(1, 20); 
        password = new JPasswordField(20);
        ImageIcon icon = new ImageIcon("Icon/Snake.png");
        ImageIcon bg = new ImageIcon(ClassLoader.getSystemResource("Icon/backg.jpeg"));
        Image bgg=bg.getImage().getScaledInstance(520, 420, Image.SCALE_DEFAULT);
        ImageIcon bggg = new ImageIcon(bgg);
        JLabel imLabel = new JLabel(bggg);
        imLabel.setBounds(0,0,520,420);
        setVisible(true);
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login and Sign up");
        setSize(520, 420);
        setLayout(null); 
        setResizable(false);
        Namelabel.setBounds(150,100,130,20);
        Namelabel.setForeground(Color.white);
        name.setBounds(220,100,120,20);
        Passwordlabel.setBounds(150,140,130,20);
        Passwordlabel.setForeground(Color.white);
        password.setBounds(220,140,120,20);
        login.setBounds(150,180,90,20);
        Signin.setBounds(250,180,90,20);
        add(Namelabel);
        add(name);
        add(Passwordlabel);
        add(password);
        add(login);
        add(Signin);
        // setBounds(50,50,200,200);
        add(imLabel);
    }

    public static void main(String[] args)  throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        File file = new File("Music/Level.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrentWindow();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            // for(User u: users){
            //     if(u.getName().equals(name.getText())){
            //         new Newwindow();
            //         return;
            //     }
            //     else{
            //         new CurrentWindow();
            //     }
            // }
            try{
                String query="SELECT * FROM `usertable`";
                ps=con.prepareStatement(query);
                rs=ps.executeQuery();
                while(rs.next()){ 
                    String passwordText = new String(password.getPassword());
                    if(rs.getString("name").equals(name.getText()) && rs.getString("password").equals(passwordText)){
                        new Newwindow();
                        return;
                    }
                    else{
                        new CurrentWindow();
                    }
                }
            }
            catch(Exception es){
                System.out.println("Display-Statement cannot be executed");
            }
        }
        if(e.getSource()==Signin){
            String username = name.getText();
            String passwordText = new String(password.getPassword());

            if(username.isEmpty() || passwordText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter username and password");
                return;
            }

            // user = new User(username, passwordText);
            // users.add(user);

            try{
                String query="INSERT INTO `usertable` (`name`, `password`) VALUES (?, ?)";
                ps=con.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, passwordText);
                if(ps.executeUpdate()>=1){
                    JOptionPane.showMessageDialog(this, "User info Saved Successfully!");
                }else
                    JOptionPane.showMessageDialog(this, "Couldn't Save the user info successfully!");
            }
            catch(Exception ed){
                System.out.println("Insert-Statement cannot be executed"+ed);
            }

            name.setText("");
            password.setText("");
        }
    }

    public void closeResources(){
        try{
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
            if(stmt!=null)
                stmt.close();
            if(con!=null)
                con.close();
        }
        catch(Exception e){
            System.out.println("Error in closing resources");
        }
    }
}
