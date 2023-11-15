package GUI;

import Algorithm.MRV;
import Algorithm.Service;

import javax.swing.*;
import java.awt.*;

public class GameScreen {
    private JFrame jFrame;
    private  JPanelBoxGame panelBoxGame;
    private Service service;
    private MRV mrv;
    private String Path="D:\\schoolyear3\\nmtrituenhantao\\SudokuJava\\src\\main\\java\\DeSodoku\\de8.txt";
    public GameScreen() {
        jFrame = new JFrame();
        jFrame.setSize(1100, 759);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(new BorderLayout());
        panelBoxGame = new JPanelBoxGame();
        service=new Service();
        mrv = new MRV();
        jFrame.add(panelBoxGame, BorderLayout.WEST);
        jFrame.add(new JPanelBoxButton(panelBoxGame.getSudokuBox(),service,mrv), BorderLayout.EAST);
        ReadFile.setData(panelBoxGame.getSudokuBox(),Path);
        jFrame.setVisible(true);
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public JPanelBoxGame getPanelBoxGame() {
        return panelBoxGame;
    }
}
