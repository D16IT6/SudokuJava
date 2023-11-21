package GUI;

import Algorithm.Backtracking;
import Algorithm.MRV;
import DeSodoku.DeSudokuClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

public class JPanelBoxButton extends JPanel {
    private final LableInput[][] lableInput;
    private File file;
    private final Backtracking backtracking;
    private final JPanelBoxGame panelBoxGame;
    private final MRV mrv;
    private PaintCell paintCell;
    private final CoppyArray coppyArray;
    private final JFrame frame;
    private String path;
    private Thread threadS;
    private Thread threadM;



    public JPanelBoxButton(LableInput[][] lableInput, Backtracking _backtracking, MRV _mrv, JPanelBoxGame _panelBoxGame, JFrame _frame) {
        this.lableInput = lableInput;
        this.backtracking = _backtracking;
        this.mrv = _mrv;
        this.panelBoxGame = _panelBoxGame;
        this.frame = _frame;
        this.coppyArray = new CoppyArray();
        this.setPreferredSize(new Dimension(500, 800));
        this.setLayout(null);
        this.paintCell=new PaintCell();

        JButtonGUI buttonSolveWithBackTracking = new JButtonGUI("BackTracKing");
        buttonSolveWithBackTracking.setBounds(250, 20, 150, 50);

        JTextArea textArea = new JTextArea();

        int padding=10;
        // Đặt viền cho JTextArea
        textArea.setBorder(new EmptyBorder(padding, padding+20, padding, padding));
        textArea.setFont(new Font("Arial", Font.BOLD , 12));
        textArea.setBounds(150, 500, 330, 200);

        buttonSolveWithBackTracking.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!ModelData.checkValidate(lableInput))
                    JOptionPane.showMessageDialog(null, "Đề sai","Lỗi",JOptionPane.ERROR_MESSAGE);
                else if(paintCell.isSoloved())
                    JOptionPane.showMessageDialog(null, "Đã giải","Thông tin",JOptionPane.OK_CANCEL_OPTION);
                else if(threadS!=null&&threadS.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy BackTracking","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                }
                else if(threadM!=null&&threadM.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy Heuristic","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    LableInput[][] lableTemp = coppyArray.getCopyLableInput(lableInput);
                    boolean check=backtracking.solveSudoku(lableTemp);
                    if (check) {
                        textArea.setText("Số lần nhập của thuật toán BackTracking:" + backtracking.getCount() +" lần"+ "\n" + "Thời gian chạy:" + backtracking.getExecutionTime() + "ms");
                        backtracking.setCount(0);
                        paintCell = new PaintCell(lableInput, backtracking.getQueue(), coppyArray.getCopyLableInput(), true);
                        threadS = new Thread(paintCell);
                        threadS.start();

                    } else {
                        JOptionPane.showMessageDialog(null, "Không thể giải được đề");
                    }
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
        buttonSolveWithMrv.setBounds(250, 100, 150, 50);
        buttonSolveWithMrv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!ModelData.checkValidate(lableInput))
                    JOptionPane.showMessageDialog(null, "Đề sai","Lỗi",JOptionPane.ERROR_MESSAGE);
                else if(paintCell.isSoloved())
                    JOptionPane.showMessageDialog(null, "Đã giải","Thông tin",JOptionPane.OK_CANCEL_OPTION);
                else if(threadS!=null&&threadS.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy BackTracking","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                }
                else if(threadM!=null&&threadM.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy Heuristic","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                }
                else {
                LableInput[][] lableTemp = coppyArray.getCopyLableInput(lableInput);
                if (mrv.sloveSudokuWithMRV(lableTemp)) {
                    paintCell = new PaintCell(lableInput, mrv.getQueue(), coppyArray.getCopyLableInput(), false);
                    threadM = new Thread(paintCell);
                    threadM.start();
                    textArea.setText("Số lần nhập thuật toán Heuristic:" + mrv.getCount() +" lần"+ "\n" + "Thời gian chạy: " + mrv.getExecutionTime() + " ms");
                    mrv.setCount(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể giải được đề");
                }}
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
        btnUnSlove.setBounds(250, 180, 150, 50);
        btnUnSlove.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paintCell.setSoloved(false);
                if(threadS!=null&&threadS.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy BackTracking","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                }
                else if(threadM!=null&&threadM.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy Heuristic","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    if (ModelData.checkData(lableInput))
                        ModelData.setData(lableInput, paintCell.getOrigin());
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
        JButtonGUI btnReset = new JButtonGUI("Reset");
        btnReset.setBounds(250, 260, 150, 50);

        btnReset.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paintCell.setSoloved(false);
                if(threadS!=null&&threadS.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy BackTracking","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                }
                else if(threadM!=null&&threadM.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy Heuristic","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                }
                else
                ModelData.ResetData(lableInput);
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

        final JFileChooser fileDialog = new JFileChooser(new File(System.getProperty("user.dir"))+"\\src\\main\\java\\DeSodoku");
        JButtonGUI btnChoseeFile = new JButtonGUI("Chọn đề");
        btnChoseeFile.setBounds(250, 340, 150, 50);

        btnChoseeFile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(threadS!=null&&threadS.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy BackTracking","Lỗi",JOptionPane.WARNING_MESSAGE);
                }
                else if(threadM!=null&&threadM.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy HeuRisTic","Lỗi",JOptionPane.WARNING_MESSAGE);
                }
                else {

                    int returnVal = fileDialog.showOpenDialog(frame);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = fileDialog.getSelectedFile();
                        path = file.getAbsolutePath();
                        paintCell.setSoloved(false);
                        ModelData.setData(panelBoxGame.getSudokuBox(), path);
                    }
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
        JLabel labelTocdo= new JLabel();
        labelTocdo.setText("Tốc độ");
        labelTocdo.setFont(new Font("Arial", Font.BOLD , 16));
        labelTocdo.setBounds(400,420,200,20);
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 20, 220, 40);
        slider.setBounds(150,420,230,20);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                int value = source.getValue();
                if(paintCell!=null)
                {
                    paintCell.setSpeed(value);
                }
            }
        });

        this.add(buttonSolveWithBackTracking);
        this.add(buttonSolveWithMrv);
        this.add(btnUnSlove);
        this.add(btnReset);
        this.add(btnChoseeFile);
        this.add(slider);
        this.add(labelTocdo);
        this.add(textArea);

    }
}
