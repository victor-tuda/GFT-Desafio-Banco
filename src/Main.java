import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Prints e Menus
    public static void printMenuPrincipal(){
        System.out.println("====BEM VINDO AO BANCO - GFT START #5====");
        System.out.println("[1] CADASTRAR CLIENTE");
        System.out.println("[2] CADASTRAR CONTA CORRENTE");
        System.out.println("[3] CADASTRAR CONTA POUPANÇA");
        System.out.println("[4] BUSCAR CLIENTE");
        System.out.println("[5] BUSCAR CONTA");
        System.out.println("[0] VOLTAR");
        System.out.print("SELECIONE: ");
    }

    // Funções de Cadastro
    public static void cadastrarCliente(Banco banco){
        String nome;
        String cpf;
        int idade;
        String profissao;

        Scanner sc = new Scanner(System.in);

        System.out.println("CADASTRO DE CLIENTE");
        System.out.print("NOME: ");
        nome = sc.nextLine();
        System.out.print("CPF: ");
        cpf = sc.nextLine();
        System.out.print("PROFISSÃO: ");
        profissao = sc.nextLine();
        System.out.print("IDADE: ");
        idade = sc.nextInt();

        Cliente cli = new Cliente(nome, cpf, idade, profissao);
        banco.getClientes().add(cli);
    }
    public static void cadastrarContaCorrente(Banco banco){
        Cliente cli;

        System.out.println("CADASTRO DE CONTA CORRENTE");
        cli = buscarCliente(banco);

        if (cli != null) {
            ContaCorrente cc = new ContaCorrente(cli);
            cli.getContas().add(cc);
            cc.setCliente(cli);
            banco.getContas().add(cc);
        }

    }
    public static void cadastrarContaPoupanca(Banco banco){
        Cliente cli;

        System.out.println("CADASTRO DE CONTA POUPANÇA");
        cli = buscarCliente(banco);

        if (cli != null) {
            ContaPoupanca cp = new ContaPoupanca(cli);
            cli.getContas().add(new ContaPoupanca(cli));
            cp.setCliente(cli);
            banco.getContas().add(cp);
        }
    }

    // Funções de Buscar
    public static Cliente buscarCliente(Banco banco){
        String cpf;
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o cpf do cliente: ");
        cpf = sc.nextLine();

        for (Cliente cliente: banco.getClientes()) {
            if(cliente.getCpf().equals(cpf)){
                cliente.imprimirInfos();
                return cliente;
            }
        }
        return null;
    }
    public static Conta buscarConta(Banco banco){
        int numero;
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o número da conta: ");
        numero = sc.nextInt();

        for (Conta conta: banco.getContas()){
            if(conta.getNumero() == numero){
                conta.imprimirInfos();
                return conta;
            }
        }
        return null;
    }

    // MAIN
    public static void main(String[] args) {
        int option;
        Scanner sc = new Scanner(System.in);
        Banco banco_gft = new Banco("Banco GFT START #5"); // Declarando banco principal

        // Loop do MENU
        do {
            printMenuPrincipal();
            option = sc.nextInt();

            switch (option) {
                case 1 -> cadastrarCliente(banco_gft);// CADASTRO DE CLIENTE
                case 2 -> cadastrarContaCorrente(banco_gft); // CRIAR UMA CONTA CORRENTE
                case 3 -> cadastrarContaPoupanca(banco_gft); // CRIAR UMA CONTA POUPANÇA
                case 4 -> buscarCliente(banco_gft); // BUSCAR UM CLIENTE
                case 5 -> buscarConta(banco_gft); // BUSCAR UMA CONTA
            }
        }while(option != 0);


        System.out.println(banco_gft);

    }
}