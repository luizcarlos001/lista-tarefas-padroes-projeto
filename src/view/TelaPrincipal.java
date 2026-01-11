package src.view;

import javax.swing.JFrame;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Lista de Tarefas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza a janela
    }
}
