public class ContaPoupanca extends Conta{
    public ContaPoupanca(Cliente cli) {
        super.tipo = "cp";
        super.cliente = cli;

        System.out.println("==== CONTA POUPANÇA ====");
        imprimirInfos();
    }
}
