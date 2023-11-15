package GUI;

import java.awt.*;
import java.awt.event.*;

public class LableInput extends Canvas {
    private boolean isSelected = false;
    private boolean isHover = false;
    private boolean isData = false;

    public boolean isData() {
        return isData;
    }

    public void setData(boolean data) {
        isData = data;
    }

    private String text = "";

    public boolean isHover() {
        return isHover;
    }

    public void setHover(boolean hover) {
        isHover = hover;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public LableInput() {
        this.setPreferredSize(new Dimension(80, 80));
        this.setBackground(new Color(255, 255, 255));
        this.setFocusable(true);
        this.setFont(new Font("Arial", Font.PLAIN, 30));

        addKeyListener(new CanvasKeyListener());
        addMouseListener(new CanvasMouseListener());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (isData) {
            g.setColor(new Color(31, 141, 248));
            g.fillRect(0, 0, getWidth(), getHeight());
        } else if (isHover) {
            g.setColor(new Color(204, 238, 248));
            g.fillRect(0, 0, getWidth(), getHeight());
        } else if (isSelected) {
            // Vẽ nền màu xanh khi được chọn
            g.setColor(Color.blue);
            g.fillRect(0, 0, getWidth(), getHeight());
            // Vẽ chữ trung tâm
        } else {
            // Nếu không được chọn, vẽ nền màu trắng
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());

            // Vẽ chữ trung tâm

        }
        g.setColor(Color.black);
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = (getHeight() + fm.getAscent()) / 2;
        g.drawString(text, x, y);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    private class CanvasKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (isSelected) {
                text = e.getKeyChar()+"";
                repaint();
            }
        }
    }

    private class CanvasMouseListener extends MouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            requestFocusInWindow();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            isSelected = true;
            repaint(); // Cần vẽ lại để cập nhật thay đổi
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            isSelected = true;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            isSelected = false;
            repaint(); // Cần vẽ lại để cập nhật thay đổi
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
        }
    }
}
