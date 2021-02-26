import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;


public class GameOfLifeView extends JComponent {

    public static final int CELL_SIZE = 14;
    public static final int BORDERED_CELL_SIZE = 15;
    private static final Color LIVE_COLOR = Color.GREEN;
    private static final Color DEAD_COLOR = Color.GRAY;

    private GameOfLife gameOfLife;

    public GameOfLifeView(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintLifeStatus(g);
    }

    private void paintLifeStatus(Graphics g) {
        for (int i = 0; i < GameOfLife.getROW(); i++) {
            for (int j = 0; j < GameOfLife.getCOL(); j++) {
               g.setColor(gameOfLife.getCell(i,j) ? LIVE_COLOR : DEAD_COLOR);
               g.fillRect(i * BORDERED_CELL_SIZE, j * BORDERED_CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public GameOfLife getGrid() {
        return gameOfLife;
    }

    public void setGrid(GameOfLife gameOfLife){
        this.gameOfLife = gameOfLife;
    }

}
