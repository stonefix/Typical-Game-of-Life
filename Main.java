
import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        JButton nextButton = new JButton();
        JButton clearButton = new JButton();
        JButton openButton = new JButton();
        JButton saveButton = new JButton();
        JButton analyzeButton = new JButton();
        GameOfLife gameOfLife = new GameOfLife();
        GameOfLifeView view = new GameOfLifeView(gameOfLife);
        CellMouseListener listener = new CellMouseListener(gameOfLife);
        GameOfLifeFrame frame = new GameOfLifeFrame(listener, view, nextButton, clearButton, openButton, saveButton, analyzeButton);
        frame.setVisible(true);
    }
}