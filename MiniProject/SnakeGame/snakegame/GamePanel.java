package snakegame;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.sql.SQLException;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static int DELAY;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    int highScore = 0;//setting default highscore zero
    Timer timer;
    DatabaseManager dbManager;
    Random random;

    GamePanel(int delay) {
        DELAY = delay;
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.pink);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        dbManager = new DatabaseManager();// Initialize database manager
        startGame();
    }
//to start a new game
    public void startGame() {
        newApple();
        running = true;
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
//Draw game elements
    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        } else {
            gameOver(g);//to show game over screen
        }
    }
//to generate a new apple at random position after the snake devours it
    public void newApple() {
        appleX = random.nextInt((int) SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt((int) SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }
//to move the snake 
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U': y[0] -= UNIT_SIZE; break;
            case 'D': y[0] += UNIT_SIZE; break;
            case 'L': x[0] -= UNIT_SIZE; break;
            case 'R': x[0] += UNIT_SIZE; break;
        }
    }
// to check if the snake has eaten the apple
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
//check for the collision with walls or itself
    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
            try {
                dbManager.insertScore(applesEaten);//Insert score into database
                highScore = dbManager.getHighScore();//Get high score from database
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
//display gameover screen
    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Press SPACE to Restart", (SCREEN_WIDTH - metrics3.stringWidth("Press SPACE to Restart")) / 2, SCREEN_HEIGHT / 2 + 75);

        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics4 = getFontMetrics(g.getFont());
        g.drawString("High Score: " + highScore, (SCREEN_WIDTH - metrics4.stringWidth("High Score: " + highScore)) / 2, SCREEN_HEIGHT / 2 + 150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move(); // Move the snake
            checkApple(); // Check if apple is eaten
            checkCollisions(); // Check for collisions
        }
        repaint();// Repaint the game panel
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (!running) {
                        DELAY = SnakeGame.selectDifficulty();// Select new difficulty
                        startGame();// Restart the game
                    }
                    break;
            }
        }
    }
}
