## Lista de Tarefas – Padrões de Projeto (Singleton e Strategy)

Aplicação desenvolvida em Java, com versão em console e interface gráfica (Swing), para gerenciamento de tarefas.
O sistema permite cadastrar, listar, remover e alterar o status de tarefas, utilizando padrões de projeto para organizar e estruturar a lógica da aplicação.

### Objetivo do Sistema

O sistema tem como objetivo facilitar o gerenciamento de tarefas do usuário, permitindo:

- Cadastrar tarefas contendo nome, descrição e status.

- Listar todas as tarefas cadastradas, exibindo seu status atual.

- Remover tarefas existentes.

- Alterar o status das tarefas de forma organizada e extensível.

- Manter os dados salvos entre execuções por meio de persistência em arquivo CSV.

### Funcionalidades Implementadas

- Cadastro de tarefas.

- Listagem de tarefas.

- Remoção de tarefas.

- Alteração de status da tarefa.

- Persistência automática em arquivo (tarefas.csv).

- Execução via terminal e via interface gráfica Swing.

- Status possíveis das tarefas
    -   DISPONIVEL
    - FAZENDO
    - FEITA

 ### Estrutura do Projeto

```text
src/
├── app/
│   ├── Main.java
│   └── MainSwing.java
├── manager/
│   └── TaskManager.java
├── model/
│   ├── Tarefa.java
│   └── Status.java
├── strategy/
│   ├── StatusStrategy.java
│   ├── DisponivelStrategy.java
│   ├── FazendoStrategy.java
│   └── FeitaStrategy.java
└── view/
    └── TelaPrincipal.java
```


## Padrões de Projeto Utilizados
#### Singleton – Gerenciamento das Tarefas

O padrão Singleton garante que exista apenas uma única instância da classe responsável por gerenciar as tarefas durante toda a execução da aplicação.

Onde foi aplicado:

- Classe: TaskManager
- Pacote: src.manager

Como foi implementado:

- Atributo estático privado que guarda a instância:
- private static TaskManager instance;


Construtor privado, impedindo criação externa:
```text
private TaskManager() {
    tarefas = new ArrayList<>();
    carregarDoArquivo();
}
```

Método público estático para acesso à instância:

```text
public static TaskManager getInstance() {
    if (instance == null) {
        instance = new TaskManager();
    }
    return instance;
}
```
#### Por que foi utilizado?

- Centraliza o gerenciamento das tarefas.
- Evita múltiplas listas de tarefas no sistema.
- Garante que console e Swing compartilhem os mesmos dados.
- Centraliza a persistência no arquivo tarefas.csv.

#### Exemplo de uso

No console:
```text
TaskManager manager = TaskManager.getInstance();

```
Na interface gráfica:
```text
manager = TaskManager.getInstance();
```

#### Strategy – Alteração de Status da Tarefa

O padrão Strategy permite encapsular diferentes comportamentos em classes separadas, possibilitando escolher o comportamento adequado em tempo de execução.

#### Onde foi aplicado:

- Interface: StatusStrategy (pacote src.strategy)

#### Implementações:

- DisponivelStrategy
- FazendoStrategy
- FeitaStrategy

#### Como funciona:

A interface define o contrato comum:
```text
public interface StatusStrategy {
    void alterarStatus(Tarefa tarefa);
}

```
#### Cada classe concreta implementa uma forma específica de alterar o status:

- DisponivelStrategy → define status como DISPONIVEL
- FazendoStrategy → define status como FAZENDO
- FeitaStrategy → define status como FEITA

#### Uso no Console

O usuário escolhe o status por meio de uma opção numérica.
O sistema instancia a estratégia correspondente e aplica à tarefa:
```text
StatusStrategy strategy = new FazendoStrategy();
strategy.alterarStatus(tarefa);
manager.salvarAlteracao();
```
#### Uso no Swing

O status é escolhido por meio de um JComboBox.
A estratégia correta é selecionada dinamicamente e aplicada à tarefa.

#### Por que foi utilizado

- Evita grandes blocos de if/else ou switch.
- Facilita manutenção e extensão do código.
- Permite adicionar novos status sem alterar a lógica principal.

### Conceitos de Programação Orientada a Objetos Utilizados
- Classes e Objetos
- Tarefa representa o conceito central do sistema.
- Possui atributos nome, descricao e status.

#### Enumeração

#### A classe Status define os estados possíveis da tarefa:
```text
DISPONIVEL, FAZENDO, FEITA
```
- Padrões de Projeto
```text
Singleton: controle centralizado do sistema.

Strategy: variação de comportamento para alteração de status.
```

### Armazenamento Permanente

As tarefas são armazenadas no arquivo tarefas.csv.

O arquivo é atualizado automaticamente ao:

- Adicionar tarefa.

- Remover tarefa.

- Alterar status.

Ao iniciar o sistema, o arquivo é lido e os dados são carregados novamente.

### Interface Gráfica (Swing)

A interface gráfica foi desenvolvida utilizando a biblioteca javax.swing, com os seguintes componentes:

- JFrame

- JTextField

- JButton

- JComboBox

- JList

- JOptionPane

A interface reutiliza exatamente a mesma lógica de negócio da aplicação em console, respeitando os princípios de reutilização e organização do código.

### Como Compilar e Executar
Requisitos

- JDK 8 ou superior

- Java configurado no PATH do sistema

Execução via terminal
java -jar lista-tarefas.jar

Execução via código-fonte (console)

```text
javac src/app/Main.java
java src.app.Main
```

Execução via interface gráfica
java src.app.MainSwing


O arquivo tarefas.csv será criado automaticamente após o primeiro cadastro.

### Considerações Finais

A aplicação demonstra o uso prático dos padrões de projeto Singleton e Strategy
em um sistema real, garantindo organização do código, reutilização da lógica de
negócio e facilidade de manutenção, tanto na versão em console quanto na interface gráfica
