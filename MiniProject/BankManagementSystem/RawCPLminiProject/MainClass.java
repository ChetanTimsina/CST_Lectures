import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass extends JFrame implements ActionListener {
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton fastCashButton;
    private JButton balanceEnquiryButton;
    private JButton exitButton;
    private String pin, accountno;

    public MainClass(String pin) {
        super("Bank Management System");
        this.pin = pin;

        ImageIcon atmIcon = new ImageIcon(ClassLoader.getSystemResource("atm2.png"));
        Image scaledImage = atmIcon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel atmLabel = new JLabel(scaledIcon);
        atmLabel.setBounds(0, 0, 1550, 830);
        add(atmLabel);

        JLabel label = new JLabel("Please Select Your Transaction");
        label.setBounds(430, 180, 700, 35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System", Font.BOLD, 28));
        atmLabel.add(label);

        depositButton = createButton("DEPOSIT", 410, 274);
		depositButton.addActionListener(this);
        withdrawButton = createButton("CASH WITHDRAWAL", 700, 274);
		withdrawButton.addActionListener(this);
        fastCashButton = createButton("FAST CASH", 410, 318);
		fastCashButton.addActionListener(this);
        balanceEnquiryButton = createButton("BALANCE ENQUIRY", 700, 318);
		balanceEnquiryButton.addActionListener(this);
        exitButton = createButton("EXIT", 550, 406);
		exitButton.addActionListener(this);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    private JButton createButton(String label, int x, int y) {
        JButton button = new JButton(label);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(65, 125, 128));
        button.setBounds(x, y, 150, 35);
        button.addActionListener(this);
        add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            new Deposit(pin);
            setVisible(false);
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == withdrawButton) {
            new Withdrawl(pin);
            setVisible(false);
        } else if (e.getSource() == balanceEnquiryButton) {
            new BalanceEnquiry(pin);
            setVisible(false);
        } else if (e.getSource() == fastCashButton) {
            new FastCash(pin,"123");
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MainClass("");
    }
}

