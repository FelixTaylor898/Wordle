import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Tile {
    JLabel box;
    String letter;

    public Tile() {
        box = new JLabel();
        box.setPreferredSize(new Dimension(100, 100));
        box.setHorizontalAlignment(SwingConstants.CENTER);
        box.setVerticalAlignment(SwingConstants.CENTER);
        box.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        box.setFont(new Font(Font.DIALOG, Font.BOLD, 45));
        box.setText(null);
        box.setOpaque(true);
        letter = null;
    }

    public void setLetter(String letter) {
        this.letter = letter;
        box.setText(letter);
    }

    public String getLetter() {
        return letter;
    }

    public void setColor(Color color) {
        box.setBackground(color);
    }

    public JLabel getBox() {
        return box;
    }

    public void setBox(JLabel box) {
        this.box = box;
    }

    public Object getColor() {
        return box.getBackground();
    }

}