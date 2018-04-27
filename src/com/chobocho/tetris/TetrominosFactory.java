package com.chobocho.tetris;
import java.util.*;

/**
 * 
 */
public class TetrominosFactory {

    public static Tetrominos create() {
        switch( (int) (Math.random() * 7)) {
            case 0:
                return new OTetrominos();
            case 1:
                return new ITetrominos();
            case 2:
                return new LTetrominos();
            case 3:
                return new JTetrominos();
            case 4:
                return new TTetrominos();
            case 5:
                return new STetrominos();
            case 6:
                return new ZTetrominos();
            default:
                return new ITetrominos();
        }
    }

    private TetrominosFactory() {}
}