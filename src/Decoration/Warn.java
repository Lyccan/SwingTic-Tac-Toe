package Decoration;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Warn {

    public Warn(String vencedor) {
        Toaster toaster = new Toaster();
        if (vencedor.equals("X")) {
            toaster.showWarn("X WINS! CLEANING...", 1500);
        }
        else if(vencedor.equals("O")) {
            toaster.showWarn("O WINS! CLEANING...", 1500);
        }
        else if(vencedor.equals("DRAW")) {
            toaster.showWarn("DRAW! CLEANING...", 1500);
        }
        else if(vencedor.equals("RESET")) {
            toaster.showWarn("CLEANING...", 1500);
        }
        else {
            toaster.showWarn("INVALID MOVEMENT!", 1500);
        }
    }

private static class Toaster {
    private JWindow window;
    private JLabel warnContent;

    public Toaster() {
        window = new JWindow();
        window.setLayout(new GridBagLayout());
        window.setBackground(Color.black);

        JPanel pnl = new JPanel();
        pnl.setBackground(Color.black);
        pnl.setBorder(BorderFactory.createLineBorder(Color.black));

        warnContent = new JLabel();
        warnContent.setFont(new Font("Arial", Font.BOLD, 16));
        warnContent.setForeground(Color.white);

        pnl.add(warnContent);
        window.add(pnl);
        window.setSize(300, 50);
    }


    public void showWarn(String message, int duration) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - window.getWidth()) / 2;
        int y = (screenSize.height - window.getHeight() - 50);

        window.setLocation(x, y);
        window.setVisible(true);
        warnContent.setText(message);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                window.setVisible(false);
            }
        }, duration);

    }

  }

}
