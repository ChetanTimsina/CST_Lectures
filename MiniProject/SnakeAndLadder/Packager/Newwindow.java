package Packager;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import Packager.packagers.*;

class FileMenu extends Menu implements ActionListener {
    Newwindow nw;

    FileMenu(Newwindow n) {
        super("File");
        nw = n;
        MenuItem mi;
        add(mi = new MenuItem("Save"));
        mi.addActionListener(this);
        add(mi = new MenuItem("Open"));
        mi.addActionListener(this);
        add(mi = new MenuItem("Exit"));
        mi.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("Exit")) {
            nw.setVisible(false);
            nw.dispose();
            System.exit(0);
        } else if (str.equals("Open")) {
            FileDialog fd = new FileDialog(nw, "Open File", FileDialog.LOAD);
            fd.setVisible(true);

            // String directory = fd.getDirectory();
            // String file = fd.getFile();
        }
    }
}

class HelpMenu extends Menu implements ActionListener {
    Newwindow nw;

    HelpMenu(Newwindow n) {
        super("Help");
        nw = n;
        MenuItem mi;
        add(mi = new MenuItem("Basic Information"));
        mi.addActionListener(this);
        add(mi = new MenuItem("Instruction Manual"));
        mi.addActionListener(this);
        addSeparator();
        Menu subMenu = new Menu("FAQ");
        subMenu.add(mi = new MenuItem("Contact Info"));
        mi.addActionListener(this);
        subMenu.add(mi = new MenuItem("Ask Your Question"));
        mi.addActionListener(this);
        add(subMenu);
    }

    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("Basic Information")) {
            JOptionPane.showMessageDialog(null, "Snakes and Ladders: Basic Information\n" +
                    "Overview\n" +
                    "Snakes and Ladders is a classic board game that originated in ancient India.\n" +
                    "It is a simple race game played on a square board with numbered squares.\n" +
                    "Some squares have ladders that help players advance, while others have snakes that send players back.\n" +
                    "History\n" +
                    "- Origin: Created in India as 'Moksha Patam,' symbolizing virtues (ladders) and vices (snakes).\n" +
                    "- Evolution: Adapted in England and the U.S., becoming the modern Snakes and Ladders.\n" +
                    "Game Board\n" +
                    "- Structure: Typically a 10x10 grid with 100 numbered squares.\n" +
                    "- Ladders: Allow players to move up the board.\n" +
                    "- Snakes: Make players move down the board.\n" +
                    "Objective\n" +
                    "Reach the final square (usually 100) first.\n" +
                    "Educational Aspect\n" +
                    "- Morality Lesson: Teaches consequences of good and bad deeds.\n" +
                    "- Basic Skills: Develops counting, number recognition, and understanding of probability.\n" +
                    "Popularity\n" +
                    "Widely enjoyed by children and families, available in physical and digital formats.\n" +
                    "");
        } else if (str.equals("Instruction Manual")) {
            JOptionPane.showMessageDialog(null, "How to Play Snakes and Ladders\n" +
                    "\n" +
                    "Setup\n" +
                    "Each player places their token on the starting square (usually labeled '1').\n" +
                    "Players take turns rolling a single die.\n" +
                    "\n" +
                    "Rolling the Die\n" +
                    "On a player's turn, they roll the die and move their token forward the number of squares indicated by the die.\n" +
                    "\n" +
                    "Ladders\n" +
                    "If a player's token lands on a square with the base of a ladder, they immediately move their token to the top of the ladder.\n" +
                    "\n" +
                    "Snakes\n" +
                    "If a player's token lands on the head of a snake, they immediately move their token to the tail of the snake.\n" +
                    "\n" +
                    "Winning the Game\n" +
                    "The first player to reach the final square (typically 100) wins the game.\n");
        } else if (str.equals("Contact Info")) {
            JOptionPane.showMessageDialog(null,"Name: Chetan Timsina\n" +
            "Contact Info: 17237917\n" +
            "Email: 02230120.cst@rub.edu.bt"+ "\n\nName: Ngawang Pema\n" +
            "Contact Info: 17312751\n" +
            "Email: 02230137.cst@rub.edu.bt" );
                                
        } else if (str.equals("Ask Your Question")) {
            String message;
            message = JOptionPane.showInputDialog(null);
            JOptionPane.showMessageDialog(null, "Your Question \""+message+"\" have been saved, We will contact you soon.");
        }
    }
}

public class Newwindow extends JFrame implements ActionListener  {
    JButton diceButton = new JButton();
    JButton playerbutton1,playerbutton2,playerbutton3,playerbutton4,img6Button,img7Button,img8Button;
    JButton Exit;
    JTextArea RolledNo = new JTextArea();
    BufferedImage img;
    ImageIcon PlayerIcon;
    JTextArea PlayerGreen,PlayerYellow,PlayerRed,PlayerPurple,DisplayTurn;
    int turn=1;
    class GameBoardPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            
           
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);
            
            
        //     g.setColor(Color.black);
        //     for (int i = 0; i <= 10; i++) {
        //         g.drawLine(100 + i * 40, 50, 100 + i * 40, 450); 
        //         g.drawLine(100, 50 + i * 40, 500, 50 + i * 40);
        //     }
        //     // snakes head
        //     g.setColor(Color.red);
        //     g.fillOval(140,50,40,40);
        //     g.setColor(Color.white);
        //     g.fillOval(145, 55, 30, 30);
        //     g.setColor(Color.green);
        //     g.fillOval(300,50,40,40);
        //     g.setColor(Color.white);
        //     g.fillOval(305, 55, 30, 30);
        //     g.setColor(Color.blue);
        //     g.fillOval(420,50,40,40);
        //     g.setColor(Color.white);
        //     g.fillOval(425, 55, 30, 30);
        //     g.setColor(Color.black);
        //     g.fillOval(100,90,40,40);
        //     g.setColor(Color.white);
        //     g.fillOval(105, 95, 30, 30);
        //     g.setColor(Color.pink);
        //     g.fillOval(380,170,40,40);
        //     g.setColor(Color.white);
        //     g.fillOval(385, 175, 30, 30);
        //     g.setColor(Color.yellow);//dsfghjk
        //     g.fillOval(420,330,40,40);
        //     g.setColor(Color.white);
        //     g.fillOval(425, 335, 30, 30);
        //     g.setColor(Color.cyan);
        //     g.fillOval(380,370,40,40);
        //     g.setColor(Color.white);
        //     g.fillOval(385, 375, 30, 30);

        //     //snake tail
        //     g.setColor(Color.red);
        //     g.fillOval(260,410,40,40);
        //     g.setColor(Color.green);
        //     g.fillOval(140,250,40,40);
        //     g.setColor(Color.blue);
        //     g.fillOval(460,170,40,40);
        //     g.setColor(Color.black);
        //     g.fillOval(140,170,40,40);
        //     g.setColor(Color.pink);
        //     g.fillOval(300,250,40,40);
        //     g.setColor(Color.yellow);
        //     g.fillOval(180,410,40,40);
        //     g.setColor(Color.cyan);
        //     g.fillOval(340,410,40,40);

        //     // ladder tail
        //     g.setColor(Color.red);
        //     g.fillRect(460,50,40,40);
        //     g.setColor(Color.green);
        //     g.fillRect(220,90,40,40);
        //     g.setColor(Color.blue);
        //     g.fillRect(380,130,40,40);
        //     g.setColor(Color.black);
        //     g.fillRect(260,210,40,40);
        //     g.setColor(Color.pink);
        //     g.fillRect(340,170,40,40);
        //     g.setColor(Color.yellow);
        //     g.fillRect(220,330,40,40);
        //     g.setColor(Color.cyan);
        //     g.fillRect(460,290,40,40);

        //     //ladder head
        //     g.setColor(Color.red);
        //     g.fillRect(460,130,40,40);
        //     g.setColor(Color.white);
        //     g.fillRect(465, 135, 30, 30);
        //     g.setColor(Color.green);
        //     g.fillRect(380,290,40,40);
        //     g.setColor(Color.white);
        //     g.fillRect(385, 295, 30, 30);
        //     g.setColor(Color.blue);
        //     g.fillRect(300,330,40,40);
        //     g.setColor(Color.white);
        //     g.fillRect(305, 335, 30, 30);
        //     g.setColor(Color.black);
        //     g.fillRect(180,330,40,40);
        //     g.setColor(Color.white);
        //     g.fillRect(185, 335, 30, 30);
        //     g.setColor(Color.pink);
        //     g.fillRect(420,250,40,40);
        //     g.setColor(Color.white);
        //     g.fillRect(425, 255, 30, 30);
        //     g.setColor(Color.yellow);
        //     g.fillRect(140,410,40,40);
        //     g.setColor(Color.white);
        //     g.fillRect(145, 415, 30, 30);
        //     g.setColor(Color.cyan);
        //     g.fillRect(420,370,40,40);
        //     g.setColor(Color.white);
        //     g.fillRect(425, 375, 30, 30);

        //     g.setColor(Color.black);
        //     int x = 75, y = 435;
        //     boolean right =true;
        //     for(int i=1; i <= 100; i++){
        //         if (right) {
        //             if (x + 40 > 478) {  
        //                 y = y - 40; 
        //                 right = false;  
        //             } else {
        //                 x = x + 40;  
        //             }
        //         } else {
        //             if (x - 40 < 78) {  
        //                 y = y - 40;  
        //                 right = true; 
        //             } else {
        //                 x = x - 40; 
        //             }
        //         }
        //         g.drawString(Integer.toString(i), x, y);
        //     }
        //     g.drawRect(20, 370, 80, 80);
        //     g.drawLine(60,370,60,450);
        //     g.drawLine(20, 410, 100, 410);
        Image img = new ImageIcon("Icon/board.jpeg").getImage();
        g.drawImage(img, 100, 20, null);
        Image img3 = new ImageIcon("Icon/Player.png").getImage();
        g.drawImage(img3, 0, 140, null);
        Image img4 = new ImageIcon("Icon/Turn.png").getImage();
        g.drawImage(img4, 0, 290, null);
        Image img5 = new ImageIcon("Icon/Logo.png").getImage();
        g.drawImage(img5, 700, 20, null);
        }
    }

    public Newwindow() {
        //////////////////////////////////////////////////////////////
        ImageIcon icon = new ImageIcon("Icon/Snake.png");
        setIconImage(icon.getImage());
        setTitle("Snake and Ladder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 1000);
        setLayout(null);
        setVisible(true);

        try {
            BufferedImage img = ImageIO.read(new File("Icon/Dice.png"));
            Image resizedImg = img.getScaledInstance(34, 34, Image.SCALE_SMOOTH);
            ImageIcon DiceIcon = new ImageIcon(resizedImg);
            diceButton.setIcon(DiceIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        diceButton.setSize(34, 34);
        diceButton.addActionListener(this);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);
        panel2.setBounds(0,560,100,60);
        add(panel2);
        
        Exit = new JButton("Exit");
        Exit.setBounds(700,650,100,50);
        Exit.addActionListener(this);
        add(Exit);

         //player 1
         JLayeredPane imagePanel1 = new JLayeredPane();     
         JLabel l = new JLabel();
         PlayerIcon = new ImageIcon("Icon/PlayerIcon1.png");
         l.setIcon(PlayerIcon);
         imagePanel1.add(l);
 
         //player 2
         JLayeredPane imagePanel2 = new JLayeredPane();     
         JLabel m = new JLabel();
         PlayerIcon = new ImageIcon("Icon/PlayerIcon2.png");
         m.setIcon(PlayerIcon);
         imagePanel2.add(m);
 
         //player 3
         JLayeredPane imagePanel3 = new JLayeredPane();     
         JLabel n = new JLabel();
         PlayerIcon = new ImageIcon("Icon/PlayerIcon3.png");
         n.setIcon(PlayerIcon);
         imagePanel3.add(n);
 
         //player 4
         JLayeredPane imagePanel4 = new JLayeredPane();     
         JLabel o = new JLabel();
         PlayerIcon = new ImageIcon("Icon/PlayerIcon4.png");
         o.setIcon(PlayerIcon);
         imagePanel4.add(o);

        ImageIcon img6 = new ImageIcon("Icon/snakewave.gif");
        img6Button = new JButton(img6);
        img6Button.setBounds(0,350,100,118);
        add(img6Button);
        ImageIcon img7 = new ImageIcon("Icon/anime.gif");
        img7Button = new JButton(img7);
        img7Button.setBounds(0,20,100,118);
        add(img7Button);
        ImageIcon img8 = new ImageIcon("Icon/Start.gif");
        img8Button = new JButton(img8);
        img8Button.setBounds(0,468,100,90);
        img8Button.addActionListener(this);
        add(img8Button);
        DisplayTurn = new JTextArea(100,30);
        DisplayTurn.setBounds(0,320,100,20);
        DisplayTurn.setBackground(Color.black);
        DisplayTurn.setForeground(Color.green);
        DisplayTurn.setText("Green player");
        add(DisplayTurn);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(diceButton);
        buttonPanel.setBounds(550 ,650,100,50);
        RolledNo.setBounds(530,650,50,50);
        RolledNo.setFont(new Font("Arial",Font.BOLD,45));
        
        PlayerGreen = new JTextArea(100,20);
        PlayerGreen.setText("Green player");
        PlayerGreen.setBounds(0,170,100,20);
        PlayerGreen.setBackground(Color.black);
        PlayerGreen.setForeground(Color.green);
        add(PlayerGreen);

        PlayerYellow = new JTextArea(100,20);
        PlayerYellow.setText("Yellow player");
        PlayerYellow.setBounds(0,200,100,20);
        PlayerYellow.setBackground(Color.black);
        PlayerYellow.setForeground(Color.yellow);
        add(PlayerYellow);

        PlayerRed = new JTextArea(100,20);
        PlayerRed.setText("Red player");
        PlayerRed.setBounds(0,230,100,20);
        PlayerRed.setBackground(Color.black);
        PlayerRed.setForeground(Color.red);
        add(PlayerRed);

        PlayerPurple = new JTextArea(100,20);
        PlayerPurple.setText("Purple player");
        PlayerPurple.setBounds(0,260,100,20);
        PlayerPurple.setBackground(Color.black);
        PlayerPurple.setForeground(new Color(128, 0, 128));
        add(PlayerPurple);

        
        GameBoardPanel gameBoardPanel = new GameBoardPanel();
        gameBoardPanel.setBounds(0,0,850,800);
        add(buttonPanel, BorderLayout.SOUTH);
        add(gameBoardPanel, BorderLayout.CENTER);
        //player 1
        playerbutton1 = new JButton();
        try {
            BufferedImage img1 = ImageIO.read(new File("Icon/PlayerIcon1.png"));
            Image resizedImg1 = img1.getScaledInstance(34, 34, Image.SCALE_SMOOTH);
            ImageIcon p1icon = new ImageIcon(resizedImg1);
            playerbutton1.setIcon(p1icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // player 1
        playerbutton1.setBounds(71,591,28,28);//20,370 -- diff 40
        playerbutton1.addActionListener(this);
        playerbutton1.setFocusable(true);
        // player 2
        playerbutton2 = new JButton();
        try {
            BufferedImage img2 = ImageIO.read(new File("Icon/PlayerIcon2.png"));
            Image resizedImg2 = img2.getScaledInstance(34, 34, Image.SCALE_SMOOTH);
            ImageIcon p2icon = new ImageIcon(resizedImg2);
            playerbutton2.setIcon(p2icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // player 2
        playerbutton2.setBounds(41,591,28,28);//20,370 -- diff 40
        playerbutton2.addActionListener(this);

        // player 3
        playerbutton3 = new JButton();
        try {
            BufferedImage img3 = ImageIO.read(new File("Icon/PlayerIcon3.png"));
            Image resizedImg3 = img3.getScaledInstance(34, 34, Image.SCALE_SMOOTH);
            ImageIcon p3icon = new ImageIcon(resizedImg3);
            playerbutton3.setIcon(p3icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // player 3
        playerbutton3.setBounds(71,561,28,28);//20,370 -- diff 40
        playerbutton3.addActionListener(this);

        // player 4
        playerbutton4 = new JButton();
        try {
            BufferedImage img4 = ImageIO.read(new File("Icon/PlayerIcon4.png"));
            Image resizedImg4 = img4.getScaledInstance(34, 34, Image.SCALE_SMOOTH);
            ImageIcon p4icon = new ImageIcon(resizedImg4);
            playerbutton4.setIcon(p4icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // player 4
        playerbutton4.setBounds(41,561,28,28);//20,370 -- diff 40
        playerbutton4.addActionListener(this);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
        playerbutton1.setBorder(lineBorder);
        playerbutton2.setBorder(lineBorder);
        playerbutton3.setBorder(lineBorder);
        playerbutton4.setBorder(lineBorder);


        add(playerbutton1);
        add(playerbutton2);
        add(playerbutton3);
        add(playerbutton4);
        add(RolledNo);
        //////////////////////////////////////////////////////////////
        FileMenu fm = new FileMenu(this);
        HelpMenu hm = new HelpMenu(this);
        MenuBar mb = new MenuBar();
        mb.add(fm);
        mb.add(hm);
        setMenuBar(mb);
    }
    int currentgx=71,currentgy=591;
    int currentyx=41,currentyy=591;
    int currentrx=71,currentry=561;
    int currentpx=41,currentpy=561;
    boolean rightg=true,righty=true,rightr=true,rightp=true;
    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == diceButton) {
        Random rand = new Random();
        int diceRoll = rand.nextInt(6) + 1;
        // JOptionPane.showMessageDialog(null, "Dice Roll: " + diceRoll);
        RolledNo.setText(Integer.toString(diceRoll));
        if(turn == 4){
            DisplayTurn.setText(PlayerGreen.getText());
            DisplayTurn.setBounds(0,320,100,20);
            DisplayTurn.setBackground(Color.black);
            DisplayTurn.setForeground(Color.green);
            add(DisplayTurn);
        }
        else if(turn == 1){
            DisplayTurn.setText(PlayerYellow.getText());
            DisplayTurn.setBounds(0,320,100,20);
            DisplayTurn.setForeground(Color.yellow);
            add(DisplayTurn);
        }
        else if(turn == 2){
            DisplayTurn.setText(PlayerRed.getText());
            DisplayTurn.setBounds(0,320,100,20);
            DisplayTurn.setForeground(Color.red);
            add(DisplayTurn);
        }
        else if(turn == 3){
            DisplayTurn.setText(PlayerPurple.getText());
            DisplayTurn.setText(PlayerPurple.getText());
            DisplayTurn.setBounds(0,320,100,20);
            DisplayTurn.setForeground(new Color(128, 0, 128));
            add(DisplayTurn);
        }
        playerMovement pm;
        if(turn == 1){
            // playerbutton1.setBounds(53+40*DiceRoll,413,35,35);
            // add(playerbutton1);
                pm = new playerMovement(rightg, diceRoll, currentgx, currentgy,turn);
                rightg = pm.getright();
                currentgx= pm.getcurrentx();
                currentgy= pm.getcurrenty();
                turn = 2;
            }
            else if(turn == 2){
                pm = new playerMovement(righty, diceRoll, currentyx, currentyy,turn);
                righty = pm.getright();
                currentyx= pm.getcurrentx();
                currentyy= pm.getcurrenty();
                turn = 3;
            }
            else if(turn == 3){
                pm = new playerMovement(rightr, diceRoll, currentrx, currentry,turn);
                rightr = pm.getright();
                currentrx= pm.getcurrentx();
                currentry= pm.getcurrenty();
                turn = 4;
            }
            else if(turn == 4){
                pm = new playerMovement(rightp, diceRoll, currentpx, currentpy,turn);
                rightp = pm.getright();
                currentpx= pm.getcurrentx();
                currentpy= pm.getcurrenty();
                turn = 1;
            }
            playerbutton1.setLocation(currentgx, currentgy);
            playerbutton2.setLocation(currentyx, currentyy);
            playerbutton3.setLocation(currentrx, currentry);
            playerbutton4.setLocation(currentpx, currentpy);
                SwingUtilities.invokeLater(() -> {
                playerbutton1.setLocation(currentgx, currentgy);
                playerbutton1.revalidate();
                playerbutton2.setLocation(currentyx, currentyy);
                playerbutton2.revalidate();
                playerbutton3.setLocation(currentrx, currentry);
                playerbutton3.revalidate();
                playerbutton4.setLocation(currentpx, currentpy);
                playerbutton4.revalidate();
                DisplayTurn.revalidate();
                RolledNo.revalidate();
                playerbutton1.repaint();
                playerbutton2.repaint();
                playerbutton3.repaint();
                playerbutton4.repaint();
                RolledNo.repaint();
                DisplayTurn.repaint();
                });
            ImageIcon Win = new ImageIcon("Icon/win.gif");
            if(currentpx == 101 && currentpy == 21){
                playsong();
                JOptionPane.showMessageDialog(null,"Purple wins","Win Screen",JOptionPane.INFORMATION_MESSAGE,Win);
                
            }
            else if(currentrx == 131 && currentry == 21){
                playsong();
                JOptionPane.showMessageDialog(null,"Red wins","Win Screen",JOptionPane.INFORMATION_MESSAGE,Win);
            }
            else if(currentyx == 101 && currentyy == 51){
                playsong();
                JOptionPane.showMessageDialog(null,"Yellow wins","Win Screen",JOptionPane.INFORMATION_MESSAGE,Win);
            }
            else if(currentgx == 131 && currentgy == 51){
                playsong();
                JOptionPane.showMessageDialog(null,"Green wins","Win Screen",JOptionPane.INFORMATION_MESSAGE,Win);
            }
    } 
    else if (e.getSource() == playerbutton1) {
        JOptionPane.showMessageDialog(null, PlayerGreen.getText());
    }
    else if (e.getSource() == playerbutton2) {
        JOptionPane.showMessageDialog(null, PlayerYellow.getText());
    }
    else if (e.getSource() == playerbutton3) {
        JOptionPane.showMessageDialog(null,PlayerRed.getText());
    }
    else if (e.getSource() == playerbutton4) {
        JOptionPane.showMessageDialog(null, PlayerPurple.getText());
    }
    else if(e.getSource() == Exit){
        setVisible(false);
        dispose();
        System.exit(0);
    }
    else if (e.getSource() == img8Button){
        SwingUtilities.invokeLater(() -> {
            currentgx=71;
            currentgy=591;
            currentyx=41;
            currentyy=591;
            currentrx=71;
            currentry=561;
            currentpx=41;
            currentpy=561;
            rightg=true;
            righty=true;
            rightr=true;
            rightp=true;
            turn=1;
            DisplayTurn.setText(PlayerGreen.getText());
            DisplayTurn.setForeground(Color.green);
            playerbutton1.setLocation(71, 591);
            playerbutton1.revalidate();
            playerbutton2.setLocation(41, 591);
            playerbutton2.revalidate();
            playerbutton3.setLocation(71, 561);
            playerbutton3.revalidate();
            playerbutton4.setLocation(41, 561);
            playerbutton4.revalidate();
            DisplayTurn.revalidate();
            RolledNo.revalidate();
            playerbutton1.repaint();
            playerbutton2.repaint();
            playerbutton3.repaint();
            playerbutton4.repaint();
            DisplayTurn.repaint();
            RolledNo.repaint();
            });
    }
    
}
public void playsong(){
    try{
        File file = new File("Music/WinSong.wav");
        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file);
        Clip clip2 = AudioSystem.getClip();
        clip2.open(audioStream2);
        clip2.start();
        }
        catch(Exception ed){
            System.out.println("Error in playing song");
        }
}
    public static void main(String[] args) {
                new Newwindow();
            }
}
