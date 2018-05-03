package com.chobocho.tetris;
import java.util.*;

/**
 * 
 */
public abstract class TetrisGameState {
    protected ITetris tetris;

    public TetrisGameState() {
    }

    public void init() {

    }

    public void rotate() {
        // TODO implement here
    }

    public void moveLeft() {
        // TODO implement here
    }

    public void moveRight() {
        // TODO implement here
    }

    public void moveDown() {
        // TODO implement here
    }

    public void fixCurrentBlock() {

    }

    public void moveBottom() {
        // TODO implement here
    }

    public void updateBlock() {
    }

    public boolean gameOver() {
        return false;
    }

    public void updateBoard() {

    }

    public Tetrominos getCurrentTetrominos() {
        return null;
    }

    public Tetrominos getNextTetrominos() {
        return null;
    }

    public Tetrominos getShodowTetrominos() {
        return null;
    }

    public void update() {
        TetrisLog.d("TetrisGameState.update()");
        if (tetris != null) {
            tetris.getObserver().update();
        }
    }

    public boolean isIdleState() { return false; }
    public boolean isGameOverState() { return false; }
    public boolean isPlayState() { return false; }
    public boolean isPauseState() { return false; }
}