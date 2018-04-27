package com.chobocho.tetris;
import java.util.*;

/**
 * 
 */
public class JTetrominos extends Tetrominos {

    public JTetrominos() {
        this.block = new int[][][]{
                {
                        {0, 1, 0},
                        {0, 1, 0},
                        {1, 1, 0},
                },
                {
                        {1, 0, 0},
                        {1, 1, 1},
                        {0, 0, 0},
                },
                {
                        {1, 1, 0},
                        {1, 0, 0},
                        {1, 0, 0},
                },
                {
                        {1, 1, 1},
                        {0, 0, 1},
                        {0, 0, 0},
                }
        };
        numOfBlockType = 4;
        x = 4;
        y = 0;
        r = 0;
        w = 3;
        h = 3;
        type = 4;
    }

    public int[][] getBlock() {
        return block[r];
    }
}