package src.manager;

import java.util.ArrayList;
import java.util.List;

import src.model.Tarefa;

public class TaskManager {

    private static TaskManager instance;
    private List<Tarefa> tarefas;

    private TaskManager() {
        tarefas = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return tarefas;
    }
}
