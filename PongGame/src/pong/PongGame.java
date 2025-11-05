package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class PongGame extends JFrame implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private int paddle1Y = 150;
    private int paddle2Y = 150;
    private int ballX = 250;
    private int ballY = 250;
    private int ballSize = 30; // increased ball size
    private int paddleWidth = 20;
    private int paddleHeight = 150; // increased paddle size
    private int ballXSpeed = -2;
    private int ballYSpeed = -2;
    private int paddleSpeed = 15; // increased paddle speed
    private int player1Score = 0;
    private int player2Score = 0;

    private PongPanel pongPanel;

    public PongGame() {
        setTitle("Pong Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        pongPanel = new PongPanel();
        add(pongPanel);

        Timer timer = new Timer(5, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        if (ballY <= 0 || ballY >= pongPanel.getHeight() - ballSize) {
            ballYSpeed = -ballYSpeed;
        }

        if (ballX <= paddleWidth && ballY + ballSize >= paddle1Y && ballY <= paddle1Y + paddleHeight) {
            // Increase ball speed when hitting the left paddle
            ballXSpeed = Math.abs(ballXSpeed) + 1; // You can adjust the increase in speed
        }

        if (ballX >= pongPanel.getWidth() - paddleWidth - ballSize &&
                ballY + ballSize >= paddle2Y && ballY <= paddle2Y + paddleHeight) {
            // Increase ball speed when hitting the right paddle
            ballXSpeed = -Math.abs(ballXSpeed) - 1; // You can adjust the increase in speed
        }

        if (ballX < 0) {
            player2Score++;
            resetBall();
        }

        if (ballX > pongPanel.getWidth()) {
            player1Score++;
            resetBall();
        }

        pongPanel.repaint();
    }
    
    private void resetBall() {
        ballX = pongPanel.getWidth() / 2;
        ballY = pongPanel.getHeight() / 2;
        ballXSpeed = -2; // Set to your default speed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && paddle2Y > 0) {
            paddle2Y -= paddleSpeed;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN && paddle2Y < pongPanel.getHeight() - paddleHeight) {
            paddle2Y += paddleSpeed;
        }

        if (e.getKeyCode() == KeyEvent.VK_W && paddle1Y > 0) {
            paddle1Y -= paddleSpeed;
        }

        if (e.getKeyCode() == KeyEvent.VK_S && paddle1Y < pongPanel.getHeight() - paddleHeight) {
            paddle1Y += paddleSpeed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private class PongPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.BLUE);
            g.fillRect(10, paddle1Y, paddleWidth, paddleHeight);

            g.setColor(Color.RED);
            g.fillRect(getWidth() - paddleWidth - 10, paddle2Y, paddleWidth, paddleHeight);

            g.setColor(Color.WHITE);
            g.fillOval(ballX, ballY, ballSize, ballSize);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Player 1: " + player1Score, 10, getHeight() - 30);
            g.drawString("Player 2: " + player2Score, getWidth() - 120, getHeight() - 30);

            Toolkit.getDefaultToolkit().sync();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PongGame pongGame = new PongGame();
            pongGame.setVisible(true);
        });
    }
}