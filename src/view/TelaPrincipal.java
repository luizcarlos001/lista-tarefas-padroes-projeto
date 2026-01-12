package src.view;

import javax.swing.*;
import java.awt.*;
import src.manager.TaskManager;
import src.model.Tarefa;
import src.strategy.*;

public class TelaPrincipal extends JFrame {

    private JTextField txtNome;
    private JTextField txtDescricao;
    private JComboBox<String> cbStatus;

    private DefaultListModel<Tarefa> listModel;
    private JList<Tarefa> listaTarefas;

    private TaskManager manager;

    public TelaPrincipal() {
        manager = TaskManager.getInstance();

        setTitle("Lista de Tarefas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        criarPainelFormulario();
        criarLista();
        criarBotoes();

        atualizarLista();
    }

    // ---------- FORMULÁRIO ----------
    private void criarPainelFormulario() {
        JPanel painel = new JPanel(new GridLayout(3, 2));

        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        painel.add(txtDescricao);

        painel.add(new JLabel("Status:"));
        cbStatus = new JComboBox<>(new String[]{"DISPONIVEL", "FAZENDO", "FEITA"});
        painel.add(cbStatus);

        add(painel, BorderLayout.NORTH);
    }

    // ---------- LISTA ----------
    private void criarLista() {
        listModel = new DefaultListModel<>();
        listaTarefas = new JList<>(listModel);
        add(new JScrollPane(listaTarefas), BorderLayout.CENTER);
    }

    // ---------- BOTÕES ----------
    private void criarBotoes() {
        JPanel painel = new JPanel();

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        JButton btnAlterar = new JButton("Alterar Status");

        painel.add(btnAdicionar);
        painel.add(btnRemover);
        painel.add(btnAlterar);

        add(painel, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(e -> adicionarTarefa());
        btnRemover.addActionListener(e -> removerTarefa());
        btnAlterar.addActionListener(e -> alterarStatus());
    }

    // ---------- AÇÕES ----------
    private void adicionarTarefa() {
        String nome = txtNome.getText().trim();
        String desc = txtDescricao.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome não pode ser vazio.");
            return;
        }

        manager.adicionarTarefa(new Tarefa(nome, desc));
        atualizarLista();

        txtNome.setText("");
        txtDescricao.setText("");
    }

    private void removerTarefa() {
        int index = listaTarefas.getSelectedIndex();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa.");
            return;
        }

        manager.removerTarefa(index);
        atualizarLista();
    }

    private void alterarStatus() {
        int index = listaTarefas.getSelectedIndex();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa.");
            return;
        }

        Tarefa tarefa = manager.getTarefa(index);
        String status = (String) cbStatus.getSelectedItem();

        StatusStrategy strategy;

        if ("DISPONIVEL".equals(status)) {
            strategy = new DisponivelStrategy();
        } else if ("FAZENDO".equals(status)) {
            strategy = new FazendoStrategy();
        } else {
            strategy = new FeitaStrategy();
        }

        strategy.alterarStatus(tarefa);
        manager.salvarAlteracao();
        atualizarLista();
    }

    private void atualizarLista() {
        listModel.clear();
        for (Tarefa t : manager.listarTarefas()) {
            listModel.addElement(t);
        }
    }
}