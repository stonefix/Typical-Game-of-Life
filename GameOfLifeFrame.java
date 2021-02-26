import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameOfLifeFrame extends JFrame {

    private final JButton saveButton;
    private final JButton analyzeButton;
    private transient GameOfLife gameOfLife;
    private JPanel bottom = new JPanel();
    private GameOfLifeView view;

    private static Logger log = Logger.getLogger(GameOfLifeFrame.class.getName());

    private JFileChooser fileChooser = new JFileChooser();
    private transient FileNameExtensionFilter filterExt = new FileNameExtensionFilter(".rtf", ".rtf");

    private StringBuilder res = new StringBuilder();
    private StringBuilder analyze = new StringBuilder();
    private File file;
    private transient BufferedWriter bw;
    private transient BufferedReader br;

    public GameOfLifeFrame(CellMouseListener listener, GameOfLifeView gameOfLifeView, JButton nextButton, JButton clearButton, JButton openButton, JButton saveButton, JButton analyzeButton) {

        this.gameOfLife = gameOfLifeView.getGrid();
        this.view = gameOfLifeView;
        this.saveButton = saveButton;
        this.analyzeButton = analyzeButton;

        setSize(620, 720);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gameOfLifeView.addMouseListener(listener);
        add(gameOfLifeView, BorderLayout.CENTER);
        bottom.setLayout(new GridLayout(3, 2));

        clearButton.setText("Clear");
        clearButton.addActionListener(ActionEvent -> clearBoard());
        bottom.add(clearButton);
        nextButton.setText("Next");
        nextButton.addActionListener(ActionEvent -> displayNextGen());
        bottom.add(nextButton);

        openButton.setText("Open");
        openButton.addActionListener(e -> {
            int response = fileChooser.showOpenDialog(getParent());
            try {
                file = new File(fileChooser.getSelectedFile().getCanonicalPath());
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if (response == JFileChooser.APPROVE_OPTION) {
                openConfiguration(file);
            }
        });
        bottom.add(openButton);

        saveButton.setText("Save");
        saveButton.addActionListener(e -> {
            fileChooser.setFileFilter(filterExt);
            int response = fileChooser.showSaveDialog(getParent());
            try {
                if (fileChooser.getSelectedFile() != null) {
                    file = new File(fileChooser.getSelectedFile().getCanonicalPath() + filterExt.getExtensions()[0]);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if (response == JFileChooser.APPROVE_OPTION) {
                saveBoard(file);
            }
        });
        bottom.add(saveButton);

        analyzeButton.setText("Analyze");
        analyzeButton.addActionListener(e -> {
            fileChooser.setFileFilter(filterExt);
            int response = fileChooser.showSaveDialog(getParent());
            try {
                file = new File(fileChooser.getSelectedFile().getCanonicalPath()  + filterExt.getExtensions()[0]);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            if (response == JFileChooser.APPROVE_OPTION) {
                saveAnalyze(file);
            }
        });
        bottom.add(analyzeButton);

        add(bottom, BorderLayout.SOUTH);

        setTitle("Life");
    }

    private void displayNextGen() {
        gameOfLife.goToNextGeneration();
        view.repaint();

        analyze.append("Generation: ");
        analyze.append(gameOfLife.getGeneration());
        analyze.append("\n");

        analyze.append("Count: ");
        analyze.append(gameOfLife.getCountLives());
        analyze.append("\n");
        analyze.append("\n");

        for (int i = 0; i < GameOfLife.getROW(); i++) {
            for (int j = 0; j < GameOfLife.getCOL(); j++) {
                String str = (gameOfLife.getCell(j, i) ? 1 : 0) + " ";
                res.append(str);
            }
            res.append("\n");
        }
        res.append("\n");
        log.info(String.valueOf(res));
    }

    private void openConfiguration(File file){
        try{
            String st;
            br = new BufferedReader(new FileReader(file));
            String[] row;
            boolean[] values = new boolean[40];
            int counter = 0;
            while ((st = br.readLine()) != null){
                row = st.split(" ");
                for (int i = 0; i < 40; i++) {
                    values[i] = booleanFromString(row[i]);
                }
                this.gameOfLife.setRow(counter, values);
                counter++;
            }

            this.view.setGrid(gameOfLife);
            this.view.repaint();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private boolean booleanFromString(String s) {
        return s.equals("1");
    }

    private void saveBoard(File file) {
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(res.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAnalyze(File file) {
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(analyze.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearBoard() {
        gameOfLife.clearGrid();
        view.repaint();
    }

}
