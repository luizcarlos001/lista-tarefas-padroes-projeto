package src.manager;

import src.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Tarefa> tarefas;

    public TaskManager() {
        tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return tarefas;
    }
}
