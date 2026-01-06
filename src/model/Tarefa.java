package src.model;

public class Tarefa {

    private String nome;
    private String descricao;
    private Status status;

    public Tarefa(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.status = Status.DISPONIVEL;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                " | Descrição: " + descricao +
                " | Status: " + status;
    }
}
