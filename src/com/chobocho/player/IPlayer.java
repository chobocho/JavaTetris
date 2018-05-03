package com.chobocho.player;

import java.awt.*;

public interface IPlayer {
    public void onDraw(Graphics g, int startX, int startY, int blockwidth, int blockHeight);
    public void onPressKey(int k);

    public void init();
    public void moveLeft();
    public void moveRight();
    public void moveDown();
    public void rotate();
    public void moveBottom();
    public void play();
    public void pause();
    public void resume();

    public boolean isIdleState();
    public boolean isGameOverState();
    public boolean isPlayState();
    public boolean isPauseState();

    public boolean isEnableShadow();
    public void enableShadow();
    public void disableShadow();
}
