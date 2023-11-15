package GUI;

import javax.swing.*;
import java.awt.*;

public class JButtonGUI extends JPanel {
    private String text;

    public JButtonGUI(String _title) {
        this.text = _title;
    }
    public JButtonGUI() {

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, getWidth(), getHeight()/2);

        // Set text color
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        int x = (getWidth() - g.getFontMetrics().stringWidth(text))/2;
        int y = (getHeight() + g.getFontMetrics().getAscent()) / 4;
        g.drawString(text, x, y);

        // Set border color
        g.setColor(new Color(31, 141, 248));
        g.drawRect(0, 0, getWidth()-1, getHeight()/2-1);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 100);
    }
}
