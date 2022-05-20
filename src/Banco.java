import java.util.ArrayList;
import java.util.List;

public class Banco {
    protected String nome;
    protected List<Cliente> clientes = new ArrayList<>();
    protected List<Conta> contas = new ArrayList<>();

    public Banco(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "nome='" + nome + '\'' +
                ", clientes=" + clientes +
                ", contas=" + contas +
                '}';
    }
}
