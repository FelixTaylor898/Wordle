import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Felix Taylor
 * @version 9/12/23
 * 
 *          Wordle game frame.
 */
public class Game {

    static String word;
    static String[] letters;
    static JFrame frame;
    static JPanel panel, tilePanel;
    static int row;
    static int column = 0;
    static boolean stop;
    static Tile[][] tiles = new Tile[6][5];

    public Game() {
        stop = false;
        if (frame != null)
            frame.dispose();
        frame = new JFrame("Wordle");
        JButton b = new JButton("Restart");
        b.setBackground(Color.WHITE);
        b.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        b.setFocusable(false);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Game();
            }
        });

        tilePanel = new JPanel(new GridLayout(6, 5));
        row = 0;
        column = 0;
        word = WordUtil.random();
        System.out.println(word);
        letters = word.split("");
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                tiles[x][y] = new Tile();
                tilePanel.add(tiles[x][y].getBox());
            }
        }

        panel = new JPanel(new BorderLayout());
        panel.add(b, BorderLayout.NORTH);
        panel.add(tilePanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(new KeyboardListener());
    }

    /**
     * @param letter - letter typed by the user
     *               If the game isn't stopped and the row isn't
     *               full, show the letter on the grid.
     */
    public static void type(String letter) {
        if (!stop && column < 5) {
            tiles[row][column].setLetter(letter);
            column++;
        }
    }

    /**
     * If game isn't stopped and there are
     * letters in the grid, erase the most
     * recent letter.
     */
    public static void backspace() {
        if (!stop && column > 0) {
            column--;
            tiles[row][column].setLetter(null);
        }
    }

    /**
     * If the game isn't stopped, check if the inputs are in
     * the correct positions and change the colors accordingly.
     * Then, check if there are letters in the original string but
     * not in the correct position. If five correct positions are found,
     * win the game. If the game isn't won and the player filled the bottom
     * row, lose the game.
     */
    public static void enter() {
        if (stop)
            return;
        Map<String, Integer> map = new HashMap<>();
        int green = 0;
        if (column == 5) {

            StringBuilder enteredWord = new StringBuilder();
            for (int k = 0; k < 5; k++) {
                enteredWord.append(tiles[row][k].getLetter());
            }

            // Validate the entered word
            if (!WordUtil.checkString(enteredWord.toString())) {
                JOptionPane.showMessageDialog(null, "Invalid word! Try again.");
                return;
            }

            for (int k = 0; k < 5; k++) {
                if (letters[k].equals(tiles[row][k].getLetter())) {
                    tiles[row][k].setColor(Color.green);
                    green++;

                } else {
                    map.put(letters[k], map.getOrDefault(letters[k], 0) + 1);
                }
            }
            for (int k = 0; k < 5; k++) {
                if (tiles[row][k].getColor().equals(Color.green))
                    continue;
                if (map.containsKey(tiles[row][k].getLetter()) && map.get(tiles[row][k].getLetter()) > 0) {
                    tiles[row][k].setColor(Color.yellow);
                    map.put(tiles[row][k].getLetter(), map.get(tiles[row][k].getLetter()) - 1);
                } else {
                    tiles[row][k].setColor(Color.gray);
                }
            }
            if (green == 5 || row == 5) {
                stop = true;
                JOptionPane.showMessageDialog(null, green == 5 ? "You win!" : "Word was " + word);
            }
            row++;
            column = 0;
        }
    }

}