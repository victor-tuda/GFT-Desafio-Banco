public class ContaCorrente extends Conta {
    public ContaCorrente(Cliente cliente) {
        super.tipo = "cc";
        super.cliente = cliente;

        System.out.println("==== CONTA CORRENTE ====");
        imprimirInfos();
    }
}
