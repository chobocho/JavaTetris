package com.chobocho.tetris;

public class TetrisPlayState extends TetrisGameState {
    private Tetrominos currentTetrominos;
    private Tetrominos nextTetrominos;
    private Tetrominos shadowTetrominos;
    private TetrisBoard tetrisBoard;
    private int additionalPoint = 1;

    public TetrisPlayState(Tetris tetris, TetrisBoard board) {
        this.tetris = tetris;
        this.tetrisBoard = board;
        currentTetrominos = TetrominosFactory.create();
        nextTetrominos = TetrominosFactory.create();
        shadowTetrominos = TetrominosFactory.clone(currentTetrominos);
    }

    public void init() {
        this.tetrisBoard.init();
        currentTetrominos = TetrominosFactory.create();
        shadowTetrominos = TetrominosFactory.clone(currentTetrominos);
        nextTetrominos = TetrominosFactory.create();
        additionalPoint = 1;
    }

    public void moveLeft() {
       TetrisLog.d("TetrisPlayState.moveLeft()");
        currentTetrominos.moveLeft();
        if (tetrisBoard.isAcceptable(currentTetrominos) == false) {
            currentTetrominos.moveRight();
            TetrisLog.d("Not Accept");
        } else {
            TetrisLog.d("Accept");
        }
    }

    public void moveRight() {
        TetrisLog.d("TetrisPlayState.moveRight()");
        currentTetrominos.moveRight();
        if (tetrisBoard.isAcceptable(currentTetrominos) == false) {
            currentTetrominos.moveLeft();
            TetrisLog.d("Not Accept");
        } else {
            TetrisLog.d("Accept");
        }
    }

    public void rotate() {
        TetrisLog.d("TetrisPlayState.rotate()");
        currentTetrominos.rotate();
        if (tetrisBoard.isAcceptable(currentTetrominos) == false) {
            currentTetrominos.preRotate();
            TetrisLog.d("Not Accept");
        } else {
            TetrisLog.d("Accept");
        }
    }


    public void moveDown() {
        TetrisLog.d("TetrisPlayState.moveDown()");
        currentTetrominos.moveDown();
        if (tetrisBoard.isAcceptable(currentTetrominos) == false) {
            currentTetrominos.moveUp();
            TetrisLog.d("Can not move down");
            fixCurrentBlock();
            updateBoard();
            updateBlock() ;
        } else {
            TetrisLog.d("Accept");
        }
    }


    public void moveBottom() {
        TetrisLog.d("TetrisPlayState.moveBottom()");
        while(tetrisBoard.isAcceptable(currentTetrominos)) {
            currentTetrominos.moveDown();
        }
        if (tetrisBoard.isAcceptable(currentTetrominos) == false) {
            currentTetrominos.moveUp();
        }
    }


    public void fixCurrentBlock() {
        tetrisBoard.addTetrominos(currentTetrominos);
    }

    public void updateBlock() {
        currentTetrominos = nextTetrominos;
        shadowTetrominos = TetrominosFactory.clone(currentTetrominos);
        nextTetrominos = TetrominosFactory.create();
    }

    public boolean gameOver() {
        TetrisLog.d("Check Game over!");
        return (tetrisBoard.isAcceptable(currentTetrominos) == false);
    }

    public void updateBoard() {
        int removedLine = tetrisBoard.arrange();
        int point = calculatorScore(removedLine);
        tetris.addSore(point);
    }

    private int calculatorScore(int removedLineCount) {
        if (removedLineCount == 0) {
            additionalPoint = 1;
            return 0;
        }

        int lineScore = 22;
        if (removedLineCount >= 4) {
            removedLineCount = 4;
            lineScore = 888;
        } else {
            lineScore *= removedLineCount;
        }
        if (additionalPoint > 10000) {
            additionalPoint = 10000;
        }
        additionalPoint <<= removedLineCount;
        TetrisLog.d("calculatorScore : " + additionalPoint + " : " + removedLineCount);
        return  (removedLineCount * 10 * additionalPoint + lineScore);
    }

    public Tetrominos getCurrentTetrominos() {
        return currentTetrominos;
    }

    public Tetrominos getNextTetrominos() {
        return nextTetrominos;
    }

    public Tetrominos getShodowTetrominos() {
        moveShadowBottom();
        return shadowTetrominos;
    }

    public void moveShadowBottom() {
        TetrisLog.d("TetrisPlayState.moveShadowBottom()");

        shadowTetrominos.clone(currentTetrominos);

        while(tetrisBoard.isAcceptable(shadowTetrominos)) {
            shadowTetrominos.moveDown();
        }
        if (tetrisBoard.isAcceptable(shadowTetrominos) == false) {
            shadowTetrominos.moveUp();
        }
    }

    public boolean isPlayState() {
        return true;
    }
}