package src.app;

import javax.swing.SwingUtilities;
import src.view.TelaPrincipal;

public class MainSwing {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });

    }
}