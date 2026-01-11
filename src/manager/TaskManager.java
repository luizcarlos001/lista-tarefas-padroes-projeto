package src.manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import src.model.Status;
import src.model.Tarefa;

public class TaskManager {

    private static TaskManager instance;

    private static final String ARQUIVO = "tarefas.csv";
    private List<Tarefa> tarefas;

    private TaskManager() {
        tarefas = new ArrayList<>();
        carregarDoArquivo();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        salvarNoArquivo();
    }

    public List<Tarefa> listarTarefas() {
        return tarefas;
    }

    public boolean removerTarefa(int index) {
        if (index >= 0 && index < tarefas.size()) {
            tarefas.remove(index);
            salvarNoArquivo();
            return true;
        }
        return false;
    }

    public Tarefa getTarefa(int index) {
        if (index >= 0 && index < tarefas.size()) {
            return tarefas.get(index);
        }
        return null;
    }

    public int quantidadeTarefas() {
        return tarefas.size();
    }

    /* =======================
       Persistência em Arquivo
       ======================= */

    private void salvarNoArquivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Tarefa t : tarefas) {
                writer.println(
                    t.getNome() + ";" +
                    t.getDescricao() + ";" +
                    t.getStatus().name()
                );
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas no arquivo.");
        }
    }

    private void carregarDoArquivo() {
        File file = new File(ARQUIVO);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");

                if (partes.length == 3) {
                    Tarefa tarefa = new Tarefa(partes[0], partes[1]);
                    tarefa.setStatus(Status.valueOf(partes[2]));
                    tarefas.add(tarefa);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar tarefas do arquivo.");
        }
    }

    /* Chame este método após alterar status via Strategy */
    public void salvarAlteracao() {
        salvarNoArquivo();
    }
}
