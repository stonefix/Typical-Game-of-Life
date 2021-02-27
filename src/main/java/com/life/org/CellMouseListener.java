package com.life.org;

import java.awt.event.MouseEvent;

public class CellMouseListener implements java.awt.event.MouseListener {

    private final GameOfLife gameOfLife;

    public CellMouseListener(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = e.getX();
        int col = e.getY();

        int rowIndex = row / GameOfLifeView.BORDERED_CELL_SIZE;
        int colIndex = col / GameOfLifeView.BORDERED_CELL_SIZE;

        gameOfLife.setCell(rowIndex, colIndex, !gameOfLife.getCell(rowIndex, colIndex));
        e.getComponent().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
