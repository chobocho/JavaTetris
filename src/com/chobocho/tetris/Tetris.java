package com.chobocho.tetris;


public class Tetris implements ITetris {
    public static final int EMPTY = 0;

    private int score;
    private int level;
    private int speed;
    private boolean isEnableShadow = true;

    private TetrisIdleState idleState;
    private TetrisPlayState playState;
    private TetrisPauseState pauseState;
    private TetrisGameOverState gameOverState;

    private TetrisBoard board;
    private TetrisGameState gameState;

    private ITetrisObserver observer;

    public Tetris(int width, int height) {
        TetrisLog.d("Create new Tetris : " + width + " x " + height);

        board = new TetrisBoard(width, height, this);

        idleState = new TetrisIdleState(this);
        pauseState = new TetrisPauseState(this);
        playState = new TetrisPlayState(this, this.board);
        gameOverState = new TetrisGameOverState(this);
    }

    public void init() {
        TetrisLog.d("Tetris.Init()");
        score = 0;
        level = 1;
        gameState = idleState;
        board.init();
        gameState.update();
    }

    public void play() {
        TetrisLog.d("Tetris.play()");
        score = 0;
        level = 1;
        setState(playState);
        gameState.init();
        gameState.update();
    }

    public void pause() {
        TetrisLog.d("Tetris.pause()");
        setState(pauseState);
        gameState.update();
    }

    public void resume() {
        TetrisLog.d("Tetris.resume()");
        setState(playState);
        gameState.update();
    }

    public void moveLeft() {
        gameState.moveLeft();
        gameState.update();
    }

    public void moveRight() {
        gameState.moveRight();
        gameState.update();
    }

    public void moveDown() {
        if (gameState.gameOver()) {
            setState(gameOverState);
        } else {
            gameState.moveDown();
        }
        gameState.update();
    }

    public void rotate() {
        gameState.rotate();
        gameState.update();
    }

    public void moveBottom() {
        gameState.moveBottom();
        gameState.update();
    }

    public void setState(TetrisGameState state) {
        this.gameState = state;
    }

    public ITetrisObserver getObserver() {
        return this.observer;
    }

    public int getWidth() {
        return board.getWidth();
    }
    public int getHeight() {
        return board.getHeight();
    }
    public int getScore() { return this.score; }
    public int addSore(int score) { return this.score += score; }
    public int[][] getBoard() {
        return board.getBoard();
    }
    public Tetrominos getCurrentBlock() { return gameState.getCurrentTetrominos(); }
    public Tetrominos getNextBlock() { return gameState.getNextTetrominos(); }
    public Tetrominos getShadowBlock() { return gameState.getShodowTetrominos(); }

    public boolean isEnableShadow(){ return this.isEnableShadow; }
    public void enableShadow() {
        this.isEnableShadow = true;
        gameState.update();
    }
    public void disableShadow() {
        this.isEnableShadow = false;
        gameState.update();
    }

    public void register(ITetrisObserver observer) {
        TetrisLog.d("Registered view!");
        this.observer = observer;
    }

    public boolean isIdleState() { return gameState.isIdleState(); }
    public boolean isGameOverState() { return gameState.isGameOverState(); }
    public boolean isPlayState() { return gameState.isPlayState(); }
    public boolean isPauseState() { return gameState.isPauseState(); }
}