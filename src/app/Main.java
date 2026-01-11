package src.app;

import java.util.List;
import java.util.Scanner;
import src.manager.TaskManager;
import src.model.Tarefa;
import src.strategy.DisponivelStrategy;
import src.strategy.FazendoStrategy;
import src.strategy.FeitaStrategy;
import src.strategy.StatusStrategy;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskManager manager = TaskManager.getInstance();

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n==============================");
            System.out.println("        LISTA DE TAREFAS      ");
            System.out.println("==============================");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Remover tarefa");
            System.out.println("4 - Alterar status da tarefa");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {

                    case 1:
                        System.out.println("\n--- ADICIONAR NOVA TAREFA ---");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("Descrição: ");
                        String desc = scanner.nextLine();

                        manager.adicionarTarefa(new Tarefa(nome, desc));
                        System.out.println("Tarefa adicionada com sucesso.");
                        break;

                    case 2:
                        System.out.println("\n--- TAREFAS CADASTRADAS ---");
                        List<Tarefa> tarefasListagem = manager.listarTarefas();

                        if (tarefasListagem.isEmpty()) {
                            System.out.println("Nenhuma tarefa cadastrada.");
                        } else {
                            for (int i = 0; i < tarefasListagem.size(); i++) {
                                System.out.println("[" + i + "] " + tarefasListagem.get(i));
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n--- REMOVER TAREFA ---");
                        if (manager.quantidadeTarefas() == 0) {
                            System.out.println("Nenhuma tarefa para remover.");
                            break;
                        }

                        System.out.print("Informe o índice da tarefa: ");
                        int idxRemover = Integer.parseInt(scanner.nextLine());

                        if (manager.removerTarefa(idxRemover)) {
                            System.out.println("Tarefa removida com sucesso.");
                        } else {
                            System.out.println("Índice inválido.");
                        }
                        break;

                    case 4:
                        System.out.println("\n--- ALTERAR STATUS ---");
                        if (manager.quantidadeTarefas() == 0) {
                            System.out.println("Nenhuma tarefa cadastrada.");
                            break;
                        }

                        List<Tarefa> tarefasStatus = manager.listarTarefas();
                        for (int i = 0; i < tarefasStatus.size(); i++) {
                            System.out.println("[" + i + "] " + tarefasStatus.get(i));
                        }

                        System.out.print("Informe o índice da tarefa: ");
                        int idxStatus = Integer.parseInt(scanner.nextLine());

                        Tarefa tarefa = manager.getTarefa(idxStatus);
                        if (tarefa == null) {
                            System.out.println("Índice inválido.");
                            break;
                        }

                        System.out.println("\nEscolha o novo status:");
                        System.out.println("1 - DISPONIVEL");
                        System.out.println("2 - FAZENDO");
                        System.out.println("3 - FEITA");
                        System.out.print("Opção: ");
                        int opStatus = Integer.parseInt(scanner.nextLine());

                        StatusStrategy strategy = null;
                        if (opStatus == 1) {
                            strategy = new DisponivelStrategy();
                        } else if (opStatus == 2) {
                            strategy = new FazendoStrategy();
                        } else if (opStatus == 3) {
                            strategy = new FeitaStrategy();
                        }

                        if (strategy == null) {
                            System.out.println("Status inválido.");
                            break;
                        }

                        strategy.alterarStatus(tarefa);
                        System.out.println("Status alterado com sucesso.");
                        System.out.println("Atual: " + tarefa);
                        break;

                    case 0:
                        System.out.println("\nEncerrando o sistema...");
                        break;

                    default:
                        System.out.println("\nOpção inválida.");
                        break;
                }

            } catch (Exception e) {
                System.out.println("\nEntrada inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
