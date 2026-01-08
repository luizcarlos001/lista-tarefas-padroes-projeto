package src.strategy;

import src.model.Status;
import src.model.Tarefa;

public class FazendoStrategy implements StatusStrategy {

    @Override
    public void alterarStatus(Tarefa tarefa) {
        if (tarefa != null) {
            tarefa.setStatus(Status.FAZENDO);
        }
    }
}
