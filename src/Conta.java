public abstract class Conta implements IConta {
    private static int SEQUENCIAL = 1;

    protected String tipo;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    // Constructor
    public Conta() {
        this.numero = SEQUENCIAL++;
    }

    // Getters
    public int getNumero() {
        return numero;
    }
    public double getSaldo() {return saldo;}

    // Setters
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Funções
    @Override
    public void sacar(double valor){
        saldo -= valor;
    }

    @Override
    public void depositar(double valor){
        saldo += valor;
    }

    @Override
    public void transferir(double valor, Conta contaDestino){
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", saldo=" + saldo +
                ", cliente=" + cliente.getCpf() +
                '}';
    }

    @Override
    public void imprimirInfos(){
        System.out.printf("TIPO: %s%n", this.tipo);
        System.out.printf("NÚMERO: %d%n", this.numero);
        System.out.printf("SALDO: %.2f%n", this.saldo);
        System.out.printf("CLIENTE: %s%n", this.cliente.getCpf());
    }
}
