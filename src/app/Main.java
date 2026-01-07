package src.app;

import src.manager.TaskManager;
import src.model.Tarefa;

import java.util.Scanner;

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
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> {
                        System.out.println("\n--- ADICIONAR NOVA TAREFA ---");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("Descrição: ");
                        String desc = scanner.nextLine();

                        manager.adicionarTarefa(new Tarefa(nome, desc));
                        System.out.println("Tarefa adicionada com sucesso.");
                    }

                    case 2 -> {
                        System.out.println("\n--- TAREFAS CADASTRADAS ---");
                        var tarefas = manager.listarTarefas();
                        if (tarefas.isEmpty()) {
                            System.out.println("Nenhuma tarefa cadastrada.");
                        } else {
                            for (int i = 0; i < tarefas.size(); i++) {
                                System.out.println("[" + i + "] " + tarefas.get(i));
                            }
                        }
                    }

                    case 3 -> {
                        System.out.println("\n--- REMOVER TAREFA ---");
                        System.out.print("Informe o índice da tarefa: ");
                        int idx = Integer.parseInt(scanner.nextLine());

                        if (manager.removerTarefa(idx)) {
                            System.out.println("Tarefa removida com sucesso.");
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    }

                    case 0 -> System.out.println("\nEncerrando o sistema...");

                    default -> System.out.println("\nOpção inválida.");
                }

            } catch (Exception e) {
                System.out.println("\nEntrada inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
