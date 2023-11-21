package GUI;

import Algorithm.Backtracking;
import Algorithm.MRV;

import javax.swing.*;
import java.awt.*;

public class GameScreen {
    private JFrame jFrame;
    private  JPanelBoxGame panelBoxGame;
    private Backtracking Backtracking;
    private MRV mrv;
    private String Path="D:\\schoolyear3\\nmtrituenhantao\\SudokuJava\\src\\main\\java\\DeSodoku\\de8.txt";
    public GameScreen() {
        jFrame = new JFrame();
        jFrame.setTitle("Sodoku_LaTienAnh_VuDucThang");
        jFrame.setSize(1100, 759);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(new BorderLayout());
        panelBoxGame = new JPanelBoxGame();
        Backtracking=new Backtracking();
        mrv = new MRV();
        jFrame.add(panelBoxGame, BorderLayout.WEST);
        jFrame.add(new JPanelBoxButton(panelBoxGame.getSudokuBox(),Backtracking,mrv,panelBoxGame,jFrame), BorderLayout.EAST);
        jFrame.setVisible(true);
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public JPanelBoxGame getPanelBoxGame() {
        return panelBoxGame;
    }
}
