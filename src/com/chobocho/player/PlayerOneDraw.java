package com.chobocho.player;

import com.chobocho.tetris.ITetris;
import com.chobocho.tetris.Tetris;
import com.chobocho.tetris.Tetrominos;

import java.awt.*;

public class PlayerOneDraw implements IPlayerDraw {
    ITetris tetris;

    public void setTetris(ITetris tetris) {
        this.tetris = tetris;
    }

    public void onDraw(Graphics g, int startX, int startY, int blockWidth, int blockHeight) {
        // Draw board
        int i = 0, j = 0;

        int[][] board = tetris.getBoard();

        for (i = 0; i < tetris.getWidth(); i++) {
            for (j = 0; j < tetris.getHeight(); j++) {
                drawRectangle(g, startX + i * blockWidth,
                        startY + j * blockHeight, board[j][i], blockWidth, blockHeight);
            }
        }

        if (tetris.isPlayState()) {
            System.out.println("Tetris (d) PlayerOne DrawBlock!");
            Tetrominos shadowBlock = tetris.getShadowBlock();
            int[][] sBlock = shadowBlock.getBlock();
            int sw = shadowBlock.getWidth();
            int sh = shadowBlock.getHeight();
            int sx = shadowBlock.getX();
            int sy = shadowBlock.getY();
            int sType = shadowBlock.getType();

            for (i = 0; i < sw; i++) {
                for (j = 0; j < sh; j++) {
                    if (sBlock[j][i] != Tetris.EMPTY) {
                        drawShadowRectangle(g, startX + (sx + i) * blockWidth,
                                startY + (sy+j) * blockHeight, sType, blockWidth, blockHeight);
                    }
                }
            }

            Tetrominos currentBlock = tetris.getCurrentBlock();
            int[][] block = currentBlock.getBlock();
            int w = currentBlock.getWidth();
            int h = currentBlock.getHeight();
            int x = currentBlock.getX();
            int y = currentBlock.getY();
            int type = currentBlock.getType();

            for (i = 0; i < w; i++) {
                for (j = 0; j < h; j++) {
                    if (block[j][i] != Tetris.EMPTY) {
                        drawRectangle(g, startX + (x + i) * blockWidth,
                                startY + (y+j) * blockHeight, type, blockWidth, blockHeight);
                    }
                }
            }


            Tetrominos aNextblock = tetris.getNextBlock();
            int[][] nextBlock = aNextblock.getBlock();
            int nw = aNextblock.getWidth();
            int nh = aNextblock.getHeight();
            int nx = aNextblock.getX();
            int ny = aNextblock.getY();
            int ntype = aNextblock.getType();
            int nextBlockX = startX + blockWidth * (tetris.getWidth()-1);
            int nextBlockY = startY + blockHeight * 4;

            for (i = 0; i < nw; i++) {
                for (j = 0; j < nh; j++) {
                    if (nextBlock[j][i] != Tetris.EMPTY) {
                        drawRectangle(g, nextBlockX + (nx + i) * blockWidth/2,
                                nextBlockY + (ny+j) * blockHeight/2, ntype, blockWidth/2, blockHeight/2);
                    }
                }
            }

        }

    }

    private void drawRectangle(Graphics g, int x, int y, int type, int blockWidth, int blockHeight)
    {
        Color colors[] = { new Color(90, 90, 90), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[type];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, blockWidth - 2, blockHeight - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + blockHeight - 1, x, y);
        g.drawLine(x, y, x + blockWidth - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + blockHeight - 1,
                x + blockWidth - 1, y + blockHeight - 1);
        g.drawLine(x + blockWidth - 1, y + blockHeight - 1,
                x + blockWidth - 1, y + 1);
    }

    private void drawShadowRectangle(Graphics g, int x, int y, int type, int blockWidth, int blockHeight)
    {
        Color colors[] = { new Color(90, 90, 90), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[type];

        g.setColor(color);
        //g.fillRect(x + 1, y + 1, blockWidth - 2, blockHeight - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + blockHeight - 1, x, y);
        g.drawLine(x, y, x + blockWidth - 1, y);

        //g.setColor(color.darker());
        g.drawLine(x + 1, y + blockHeight - 1,
                x + blockWidth - 1, y + blockHeight - 1);
        g.drawLine(x + blockWidth - 1, y + blockHeight - 1,
                x + blockWidth - 1, y + 1);
    }

}
