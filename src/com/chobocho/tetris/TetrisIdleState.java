package com.chobocho.tetris;
import java.util.*;

/**
 * 
 */
public class TetrisIdleState extends TetrisGameState {

    /**
     * Default constructor
     */
    public TetrisIdleState(Tetris tetris) {
        TetrisLog.d("TetrisIdleState()");
        this.tetris = tetris;
    }
    public boolean isIdleState() {
       return true;
    }
}