package GUI;

import javax.swing.*;
import java.awt.*;

public class GameScreen {
    private JFrame jFrame;
    private  JPanelBoxGame panelBoxGame;
    private Service service;
    public GameScreen() {
        jFrame = new JFrame();
        jFrame.setSize(1100, 759);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(new BorderLayout());
        panelBoxGame = new JPanelBoxGame();
        service=new Service();
        jFrame.add(panelBoxGame, BorderLayout.WEST);
        jFrame.add(new JPanelBoxButton(panelBoxGame.getSudokuBox(),service), BorderLayout.EAST);
        ReadFile.setDataSpace(panelBoxGame.getSudokuBox(),"D:\\java\\laptrinhhethong\\soduku.txt");
        jFrame.setVisible(true);
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public JPanelBoxGame getPanelBoxGame() {
        return panelBoxGame;
    }
}
