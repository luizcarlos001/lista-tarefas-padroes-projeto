package src.strategy;

import src.model.Status;
import src.model.Tarefa;

public class DisponivelStrategy implements StatusStrategy {

    @Override
    public void alterarStatus(Tarefa tarefa) {
        if (tarefa != null) {
            tarefa.setStatus(Status.DISPONIVEL);
        }
    }
}
