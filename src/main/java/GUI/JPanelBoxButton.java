package GUI;

import Algorithm.Backtracking;
import Algorithm.MRV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JPanelBoxButton extends JPanel {
    private LableInput[][] lableInput;
    private Backtracking backtracking;
    private  JPanelBoxGame panelBoxGame;
    private MRV mrv;
    private PaintCell paintCell;
    private CoppyArray coppyArray;
    private JFrame frame;

    public JPanelBoxButton(LableInput[][] lableInput, Backtracking _backtracking,MRV _mrv,JPanelBoxGame _panelBoxGame,JFrame _frame) {
        this.lableInput = lableInput;
        this.backtracking = _backtracking;
        this.mrv =_mrv;
        this.panelBoxGame=_panelBoxGame;
        this.frame=_frame;
        this.coppyArray = new CoppyArray();
        this.setPreferredSize(new Dimension(500, 800));
        this.setLayout(null);

        JButtonGUI buttonSolveWithBackTracking = new JButtonGUI("BackTracKing");
        buttonSolveWithBackTracking.setBounds(250, 20, 150, 50);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(150, 500, 330, 200);

        buttonSolveWithBackTracking.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LableInput[][] lableTemp1 = coppyArray.getCopyLableInput(lableInput);
                if(backtracking.solveSudoku(lableTemp1))
                {
                    LableInput[][] lableTemp = coppyArray.getCopyLableInput(lableInput);
                    long startTime=System.currentTimeMillis();;
                    backtracking.solveSudoku(lableTemp);
                    long endTime=System.currentTimeMillis();
                    long  executionTime=endTime-startTime;
                    paintCell = new PaintCell(lableInput, backtracking.getQueue(), coppyArray.getCopyLableInput(),true);
                    Thread thread = new Thread(paintCell);
                    thread.start();
                    textArea.setText("Số lần nhập của thuật toán BackTracking:"+backtracking.getCount()+"\n"+"Thời gian chạy:"+executionTime+"ms");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Không thể giải được đề");
                }


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
        buttonSolveWithMrv.setBounds(250, 120, 150, 50);
        buttonSolveWithMrv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                LableInput[][] lableTemp1 = coppyArray.getCopyLableInput(lableInput);//chua toi uu
                if(mrv.sloveSudokuWithMRV(lableTemp1))
                {
                    LableInput[][] lableTemp = coppyArray.getCopyLableInput(lableInput);
                    long startTime=System.currentTimeMillis();;
                    mrv.sloveSudokuWithMRV(lableTemp);
                    long endTime=System.currentTimeMillis();
                    long  executionTime=endTime-startTime;
                    paintCell = new PaintCell(lableInput, mrv.getQueue(), coppyArray.getCopyLableInput(),false);
                    Thread thread = new Thread(paintCell);
                    thread.start();
                    textArea.setText("Số lần nhập Heuristic:"+mrv.getCount()+"\n"+"Thời gian chạy:"+executionTime+"ms");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Không thể giải được đề");
                }
//                LableInput[][] lableTemp = coppyArray.getCopyLableInput(lableInput);
//                if (mrv.sloveSudokuWithMRV(lableTemp)) {
//                    long startTime = System.currentTimeMillis();
//                    paintCell = new PaintCell(lableInput, mrv.getQueue(), coppyArray.getCopyLableInput(), false);
//                    Thread thread = new Thread(paintCell);
//                    thread.start();
//                    try {
//                        thread.join(); // Đợi thread hoàn thành trước khi tính thời gian
//                    } catch (InterruptedException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                    long endTime = System.currentTimeMillis();
//                    long executionTime = endTime - startTime;
//                    textArea.setText("Số lần nhập Heuristic: " + mrv.getCount() + "\n" + "Thời gian chạy: " + executionTime + "ms");
//                } else {
//                    JOptionPane.showMessageDialog(null, "Không thể giải được đề");
//                }
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

        JButtonGUI btnUnSlove = new JButtonGUI("Xoá giải");
        btnUnSlove.setBounds(250, 220, 150, 50);
        btnUnSlove.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
        JButtonGUI btnReset = new JButtonGUI("Reset");
        btnReset.setBounds(250, 320, 150, 50);

        btnReset.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("vao  cho se");
                ReadFile.ResetData(lableInput);
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

        final JFileChooser fileDialog = new JFileChooser();
        JButtonGUI btnChoseeFile = new JButtonGUI("Chọn đề");
        btnChoseeFile.setBounds(250, 420, 150, 50);

            btnChoseeFile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("vao  cho se");
                int returnVal = fileDialog.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileDialog.getSelectedFile();
                    System.out.println(file.getAbsolutePath());
                    String Path = file.getAbsolutePath();
                    ReadFile.setData(panelBoxGame.getSudokuBox(),Path);
                } else {
                    System.out.println("Chua chon duoc");
                }
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

        this.add(buttonSolveWithBackTracking);
        this.add(buttonSolveWithMrv);
        this.add(btnUnSlove);
        this.add(btnReset);
        this.add(btnChoseeFile);
        this.add(textArea);
    }
}
