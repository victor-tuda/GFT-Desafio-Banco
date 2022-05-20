import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private static int REGISTRO = 1;
    private String nome;
    private String cpf;
    private int idade;
    private String profissao;
    private List<Conta> contas = new ArrayList<>();

    // Constructor
    public Cliente(String nome, String cpf, int idade, String profissao) {
        REGISTRO++;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.profissao = profissao;
    }

    // Getters
    public List<Conta> getContas() {return contas;}
    public String getCpf() {return cpf;}

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", idade=" + idade +
                ", profissao='" + profissao + '\'' +
                ", contas=" + contas +
                '}';
    }

    public void imprimirInfos(){
        System.out.printf("REGISTRO: %d%n", REGISTRO);
        System.out.printf("NOME: %s%n", this.nome);
        System.out.printf("CPF: %s%n", this.cpf);
        System.out.printf("IDADE: %d%n", this.idade);
        System.out.printf("PROFISSÃO: %s%n", this.profissao);
        System.out.println("Contas: ");
        System.out.println(contas);
    }
}
