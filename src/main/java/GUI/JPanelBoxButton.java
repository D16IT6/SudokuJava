package GUI;

import Algorithm.Backtracking;
import Algorithm.MRV;
import DeSodoku.DeSudokuClass;

import javax.swing.*;
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
        textArea.setBounds(150, 500, 330, 200);

        buttonSolveWithBackTracking.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(paintCell.isSoloved())
                    JOptionPane.showMessageDialog(null, "Ok","Lỗi",JOptionPane.WARNING_MESSAGE);
                else if(threadS!=null&&threadS.isAlive()){
                JOptionPane.showMessageDialog(null, "Đang chạy BackTracking","Lỗi",JOptionPane.WARNING_MESSAGE);
                }
                else if(threadM!=null&&threadM.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy HeuRisTic","Lỗi",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    LableInput[][] lableTemp = coppyArray.getCopyLableInput(lableInput);
                    boolean check=backtracking.solveSudoku(lableTemp);
                    if (check) {
                        textArea.setText("Số lần nhập của thuật toán BackTracking:" + backtracking.getCount() + "\n" + "Thời gian chạy:" + backtracking.getExecutionTime() + "ms");
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
                    JOptionPane.showMessageDialog(null, "De sai","Lỗi",JOptionPane.ERROR_MESSAGE);
                else if(paintCell.isSoloved())
                    JOptionPane.showMessageDialog(null, "Đã giải","Thông tin",JOptionPane.OK_CANCEL_OPTION);
                else if(threadS!=null&&threadS.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy BackTracking","Lỗi",JOptionPane.WARNING_MESSAGE);
                }
                else if(threadM!=null&&threadM.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy HeuRisTic","Lỗi",JOptionPane.WARNING_MESSAGE);
                }
                else {
                LableInput[][] lableTemp1 = coppyArray.getCopyLableInput(lableInput);//chua toi uu
                if (mrv.sloveSudokuWithMRV(lableTemp1)) {
                    paintCell = new PaintCell(lableInput, mrv.getQueue(), coppyArray.getCopyLableInput(), false);
                    threadM = new Thread(paintCell);
                    threadM.start();
                    textArea.setText("Số lần nhập Heuristic:" + mrv.getCount() + "\n" + "Thời gian chạy:" + mrv.getExecutionTime() + "ms");
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
                    JOptionPane.showMessageDialog(null, "Đang chạy BackTracking","Lỗi",JOptionPane.WARNING_MESSAGE);
                }
                else if(threadM!=null&&threadM.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy HeuRisTic","Lỗi",JOptionPane.WARNING_MESSAGE);
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
                if(threadS!=null&&threadS.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy BackTracking","Lỗi",JOptionPane.WARNING_MESSAGE);
                }
                else if(threadM!=null&&threadM.isAlive()){
                    JOptionPane.showMessageDialog(null, "Đang chạy HeuRisTic","Lỗi",JOptionPane.WARNING_MESSAGE);
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

        URL resourceURL = DeSudokuClass.class.getResource("de5.txt");

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
                    System.out.println(resourceURL);
                    int returnVal = fileDialog.showOpenDialog(frame);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = fileDialog.getSelectedFile();
                        path = file.getAbsolutePath();
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
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 20, 220, 40);
        slider.setBounds(200,420,200,20);
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
        JLabel labelTocdo= new JLabel();
        labelTocdo.setText("Tốc độ");
        labelTocdo.setBounds(420,420,200,20);
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
