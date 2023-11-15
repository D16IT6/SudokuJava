package GUI;

import Algorithm.MRV;
import Algorithm.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JPanelBoxButton extends JPanel {
    private LableInput[][] lableInput;
    private Service service;
    private PaintCell paintCell;
    private CoppyArray coppyArray;

    public JPanelBoxButton(LableInput[][] lableInput, Service service,MRV mrv) {
        this.lableInput = lableInput;
        this.service = service;
        this.coppyArray = new CoppyArray();
        this.setPreferredSize(new Dimension(500, 800));
        this.setLayout(null);

        JButtonGUI buttonSolveWithBackTracking = new JButtonGUI("BackTracKing");
        buttonSolveWithBackTracking.setBounds(250, 250, 150, 100);

        this.add(buttonSolveWithBackTracking);

        buttonSolveWithBackTracking.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click button");
                LableInput[][] lableTemp = coppyArray.getCopyLableInput(lableInput);
                service.solveSudoku(lableTemp);
                System.out.println(service.getCount());
                paintCell = new PaintCell(lableInput, service.getQueue(), coppyArray.getCopyLableInput());
                Thread thread = new Thread(paintCell);
                thread.start();
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
        });

        JButtonGUI buttonSolveWithMrv = new JButtonGUI("Heuristic");
        buttonSolveWithMrv.setBounds(250, 100, 150, 100);
        buttonSolveWithMrv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click button heu");
                LableInput[][] lableTemp = coppyArray.getCopyLableInput(lableInput);
                mrv.sloveSudokuWithMRV(lableTemp);
                System.out.println(mrv.getCount());
                paintCell = new PaintCell(lableInput, mrv.getQueue(), coppyArray.getCopyLableInput());
                Thread thread = new Thread(paintCell);
                thread.start();
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
        });
        this.add(buttonSolveWithMrv);
    }
}
