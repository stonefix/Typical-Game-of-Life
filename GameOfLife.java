
public class GameOfLife {

    private final static int ROW = 40;
    private final static int COL = 40;
    private boolean[][] gridArray = new boolean[getROW()][getCOL()];
    private int generation = 0;


    public void goToNextGeneration() {
        boolean[][] futureArray = new boolean[getROW()][getCOL()];
        for (int i = 0; i < getROW(); i++) {
            for (int j = 0; j < getCOL(); j++) {
                int aliveNeighbors = calculateAliveNeighbors(i, j);
                futureArray[i][j] = aliveNeighbors == 3 || (gridArray[i][j] && aliveNeighbors == 2);
            }
        }
        gridArray = futureArray;
        this.generation = this.getGeneration() + 1;
    }

    public int calculateAliveNeighbors(int row, int column) {
        int aliveNeighbors = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((row + i) != -1 && (column + j) != -1
                        && (row + i != row || column + j != column)
                        && row + i != getROW() && column + j != getCOL()
                        && gridArray[row + i][column + j]) {
                    aliveNeighbors++;
                }
            }
        }
        return aliveNeighbors;
    }

    public void clearGrid() {
        for (int i = 0; i < getROW(); i++) {
            for (int j = 0; j < getCOL(); j++) {
                gridArray[i][j] = false;
            }
        }
        generation = 0;
    }

    public boolean getCell(int row, int col) {
        return gridArray[row][col];
    }

    public void setCell(int row, int col, boolean value) {
        gridArray[row][col] = value;
    }

    public void setRow(int row, boolean[] value) {
        for (int i = 0; i < COL; i++) {
            gridArray[row][i] = value[i];
        }
    }

    public int getCountLives() {
        int count = 0;
        for (int i = 0; i < getROW(); i++) {
            for (int j = 0; j < getCOL(); j++) {
                if (gridArray[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getGeneration() {
        return generation;
    }

    public static int getROW() {
        return ROW;
    }

    public static int getCOL() {
        return COL;
    }


}
