package choboTetris;

import javax.swing.*;
import java.awt.*;

public class TetrisMain extends JFrame {
    JLabel statusbar;

    public TetrisMain() {
        statusbar = new JLabel("Press S to play game");
        statusbar.setFont(new Font(statusbar.getFont().getFontName(), Font.PLAIN, 18));
        add(statusbar, BorderLayout.SOUTH);
        TetrisBoardGui tetrisBoardGui  = new TetrisBoardGui(this);
        add(tetrisBoardGui);

        tetrisBoardGui.start();

        setSize(390, 630);
        setTitle("ChoboTetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JLabel getStatusBar() {
        return statusbar;
    }

    public static void main(String[] args) {
        TetrisMain tetrisGui = new TetrisMain();
        tetrisGui.setLocationRelativeTo(null);
        tetrisGui.setVisible(true);
    }
}