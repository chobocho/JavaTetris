import com.chobocho.tetris.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisBoardGui extends JPanel implements ActionListener, ITetrisObserver {
    final int BOARD_WIDTH = 10;
    final int BOARD_HEIGHT = 20;

    ITetris tetris;
    JLabel statusbar;
    Timer tetrisTimer;
    int   gameSpeed = 0;

    private Image screenBuffer = null;
    private Graphics graphicsBuffer = null;


    public TetrisBoardGui(TetrisMain parent) {
        tetris = new Tetris(BOARD_WIDTH, BOARD_HEIGHT);
        tetris.register(this);
        tetrisTimer = new Timer(0, this);
        statusbar = parent.getStatusBar();
        setFocusable(true);
        addKeyListener(new TetrisKeyAdapter());
    }

    public void update() {
        System.out.println("Tetris (d) View.update()");
        repaint();
    }
    private int blockWidth() { return (int) getSize().getWidth() / BOARD_WIDTH; }
    private int blockHeight() { return (int) getSize().getHeight() / BOARD_HEIGHT; }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Tetris (d) There is event");
        if (tetris == null ) {
            return;
        }
        if (tetris.isPlayState()) {
            tetris.moveDown();
            gameSpeed = 700 - (tetris.getScore() / 100000);
            tetrisTimer.setDelay(gameSpeed);
            statusbar.setText(Integer.toString(tetris.getScore()));
        } else if (tetris.isGameOverState()) {
            tetrisTimer.stop();
            statusbar.setText("Game over!");
        }

    }

    public void start()
    {
        tetris.init();
        statusbar.setText("Press S to start game!");
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Dimension size = getSize();

        int width = (int)size.getWidth();
        int height = (int)size.getHeight();

        if (screenBuffer == null) {
            screenBuffer = createImage(width, height);
        }

        graphicsBuffer = screenBuffer.getGraphics();
        graphicsBuffer.setColor(Color.DARK_GRAY);
        graphicsBuffer.fillRect(0, 0, width, height);


        int boardY = (int) size.getHeight() - BOARD_HEIGHT * blockHeight();
        int boardX = (int) (size.getWidth() - BOARD_WIDTH * blockHeight())/2;

        int i = 0;
        int j = 0;


        int[][] m_Board = tetris.getBoard();

        // Draw board
        for (i = 0; i < BOARD_WIDTH; i++) {
            for (j = 0; j < BOARD_HEIGHT; j++) {
                drawRectangle(graphicsBuffer, boardX + i * blockWidth(),
                        boardY + j * blockHeight(), m_Board[j][i]);
            }
        }

        if (tetris.isPlayState()) {
            System.out.println("Tetris (d) DrawBlock!");
            Tetrominos block = tetris.getCurrentBlock();
            int[][] m_block = block.getBlock();
            int w = block.getWidth();
            int h = block.getHeight();
            int x = block.getX();
            int y = block.getY();
            int type = block.getType();

            for (i = 0; i < w; i++) {
                for (j = 0; j < h; j++) {
                    if (m_block[j][i] != Tetris.EMPTY) {
                        drawRectangle(graphicsBuffer, boardX + (x + i) * blockWidth(),
                                boardY + (y+j) * blockHeight(), type);
                    }
                }
            }
        }
        g.drawImage(screenBuffer, 0, 0, null);
    }

    private void drawRectangle(Graphics g, int x, int y, int type)
    {
        Color colors[] = { new Color(90, 90, 90), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[type];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, blockWidth() - 2, blockHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + blockHeight() - 1, x, y);
        g.drawLine(x, y, x + blockWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + blockHeight() - 1,
                x + blockWidth() - 1, y + blockHeight() - 1);
        g.drawLine(x + blockWidth() - 1, y + blockHeight() - 1,
                x + blockWidth() - 1, y + 1);
    }

    class TetrisKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            int keycode = e.getKeyCode();

            if (tetris == null) {
                return;
            }

             if (tetris.isIdleState()) {
                if (keycode == 's' || keycode == 'S'){
                     System.out.println("Start!");
                     tetris.play();
                     tetrisTimer.start();
                 }
                 return;
             }

            if (tetris.isGameOverState()) {
                if (keycode == 's' || keycode == 'S'){
                    tetris.init();
                    statusbar.setText("Press S to start game");
                }
                return;
            }

            if ( tetris.isPauseState()) {
                if (keycode =='p' || keycode == 'P'){
                    tetris.resume();
                    tetrisTimer.start();
                }
                return;
            }

            System.out.println("Tetris (d) Press key : " + keycode);

            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    tetris.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    tetris.moveRight();
                    break;
                case KeyEvent.VK_DOWN:
                    tetris.moveDown();
                    break;
                case KeyEvent.VK_UP:
                    tetris.rotate();
                    break;
                case KeyEvent.VK_SPACE:
                    tetris.moveBottom();
                    tetris.moveDown();
                    break;
                case 'p':
                case'P':
                    tetris.pause();
                    tetrisTimer.stop();
                    statusbar.setText("Press S to resume game");
                    break;
            }

        }
    }
}