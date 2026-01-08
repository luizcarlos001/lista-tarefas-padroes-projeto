package src.strategy;

import src.model.Tarefa;

public interface StatusStrategy {
    void alterarStatus(Tarefa tarefa);
}