package com.chobocho.player;

import java.awt.event.KeyEvent;

public class PlayerOneAction implements IPlayerAction {
   IPlayer player = null;

    public void onKeyEvent(int keycode) {
        System.out.println("Tetris (d) Player1 Press key : " + keycode);

        if (player == null) {
            return;
        }

        if (player.isIdleState()) {
            if (keycode == 's' || keycode == 'S'){
                System.out.println("Start!");
                player.play();
            }
            return;
        }

        if (player.isGameOverState()) {
            if (keycode == 's' || keycode == 'S'){
                player.init();
            }
            return;
        }

        if (player.isPauseState()) {
            if (keycode =='p' || keycode == 'P'){
                player.resume();
            }
            return;
        }

        if (player.isPlayState() == false) {
            return;
        }

        switch (keycode) {
            case KeyEvent.VK_LEFT:
                player.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                player.moveRight();
                break;
            case KeyEvent.VK_DOWN:
                player.moveDown();
                break;
            case KeyEvent.VK_UP:
                player.rotate();
                break;
            case KeyEvent.VK_SPACE:
                player.moveBottom();
                break;
            case 'p':
            case'P':
                player.pause();
                break;
        }
    }

    public void setPlayer(IPlayer player) {
        this.player = player;
    }

}
