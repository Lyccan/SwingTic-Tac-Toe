import javax.swing.*;
import java.awt.*;
import java.util.*;
import Decoration.Warn;

public class TicTacToe extends JFrame {

    public TicTacToe() {
        setAmbient();
    }

    private void setAmbient() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(550, 550);
        this.setLocation(screenSize.width/2 - getWidth()/2, screenSize.height/2 - getHeight()/2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Tic-Tac-Toe");
        ImageIcon icon = new ImageIcon("../Swing TicTacToe/images/icon.png");
        this.setIconImage(icon.getImage());
        UIManager.put("Button.select", Color.TRANSLUCENT);
        UIManager.put("Button.focus", Color.TRANSLUCENT);

        JPanel pnl = new JPanel();
        pnl.setLayout(null);

        setUp(pnl);

        add(pnl);
        this.setVisible(true);
    }

    private final ArrayList<Byte> xPlays = new ArrayList<>();
    private final ArrayList<Byte> oPlays = new ArrayList<>();
    private final byte[][] winCombinations = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {6,4,2},
            {0,4,8}
    };

    private void clearGame(JPanel pnl) {
        pnl.removeAll();
        setUp(pnl);
        xPlays.clear();
        oPlays.clear();
        revalidate();
        repaint();
    }



    private void checkWIn() {
        Set<Byte> setX = new HashSet<>(xPlays);
        Set<Byte> setO = new HashSet<>(oPlays);

        boolean winO = false;
        boolean winX = false;
        for (byte[] winCombination : winCombinations) {
            for (byte i = 0; i < 3; i++) {
                if (setX.containsAll(Arrays.asList(winCombination[0], winCombination[1], winCombination[2]))) {
                    winX = true;
                } else if (setO.containsAll(Arrays.asList(winCombination[0], winCombination[1], winCombination[2]))) {
                    winO = true;
                }
            }

        }
        if (xPlays.size() + oPlays.size() > 8 && !winO && !winX) {
            new Warn("DRAW");
            clearGame((JPanel) getContentPane().getComponent(0));
        } else if (winO) {
            new Warn("O");
            clearGame((JPanel) getContentPane().getComponent(0));
        }
        else if(winX) {
            new Warn("X");
            clearGame((JPanel) getContentPane().getComponent(0));
        }

    }


    private void setUp(JPanel pnl) {
        byte count = 0;
        JButton[] bt = new JButton[9];

        Random random = new Random();
        final boolean[] XO = {random.nextBoolean()};
        final boolean[] clicado = new boolean[9];


        for (byte i = 0; i < 3; i++) {

            for (byte j = 0; j < 3; j++) {
                bt[count] = new JButton();
                bt[count].setContentAreaFilled(false);
                bt[count].setOpaque(false);
                bt[count].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                bt[count].setFocusPainted(false);
                bt[count].setFont(new Font("Sans Serif", Font.BOLD, 30));
                bt[count].setBounds((550/2 - (95*3)/2) + (100* j), (550/2 - 95 * 2) +(100* i), 95, 95);
                pnl.add(bt[count]);
                byte finalCount = count;
                bt[count].addActionListener((e) -> {
                    if(XO[0] && !clicado[finalCount]) {
                        bt[finalCount].setText("X");
                        bt[finalCount].setForeground(Color.blue);
                        xPlays.add(finalCount);
                        checkWIn();
                        XO[0] = false;
                    }
                    else if (!XO[0] && !clicado[finalCount]) {
                        bt[finalCount].setText("O");
                        bt[finalCount].setForeground(Color.red);
                        oPlays.add(finalCount);
                        checkWIn();
                        XO[0] = true;
                    }
                    else {
                        new Warn("INV");

                    }
                    clicado[finalCount] = true;
                });
                count++;
            }
        }
        JButton playAgain = new JButton("Reset");
        playAgain.setBounds(175, 425, 200, 50);
        playAgain.setBackground(Color.black);
        playAgain.setForeground(Color.white);
        playAgain.setFocusPainted(false);
        playAgain.setBorder(BorderFactory.createLineBorder(Color.black));
        playAgain.addActionListener((e) -> {
            clearGame(pnl);
            new Warn("RESET");
        });

        JLabel title = new JLabel("Tic Tac Toe");
        title.setBounds(220, 10, 200, 50);
        title.setFont(new Font("Times", Font.ITALIC, 20));

        pnl.add(playAgain);
        pnl.add(title);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

}
