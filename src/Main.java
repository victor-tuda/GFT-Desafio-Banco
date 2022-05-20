// Victor Gonçalves Tuda
// GITHUB: https://github.com/victor-tuda
// DESAFIO DESTINADO AO GFT START #5 JAVA - NA PLATAFORMA DA DIO.

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        System.out.println("[6] DEPOSITAR EM CONTA");
        System.out.println("[7] SACAR EM CONTA");
        System.out.println("[8] TRANSFERÊNCIA");
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

        // Recebendo as variáveis
        System.out.println("CADASTRO DE CLIENTE");
        System.out.print("NOME: ");
        nome = sc.nextLine();
        System.out.print("CPF: ");
        cpf = sc.nextLine();
        System.out.print("PROFISSÃO: ");
        profissao = sc.nextLine();
        System.out.print("IDADE: ");
        idade = sc.nextInt();

        Cliente cli = new Cliente(nome, cpf, idade, profissao); // Criando um cliente com as variavies coletadas
        banco.getClientes().add(cli); // Adicionando o cliente no registro do banco
    }
    public static void cadastrarContaCorrente(Banco banco){
        Cliente cli;

        System.out.println("CADASTRO DE CONTA CORRENTE");
        cli = buscarCliente(banco); // Busca e retornar o cliente desejado

        if (cli != null) {
            ContaCorrente cc = new ContaCorrente(cli);
            cli.getContas().add(cc); // Adiciona a conta no registro do cliente
            cc.setCliente(cli); // Adiciona o cliente no registro da conta (ficou redundante, talvez eu pudesse ter feito de outra forma)
            banco.getContas().add(cc); //Adiciona a conta no registro de contas do banco
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

    // Funções de Busca
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

    // Funções de Transação
    public static void sacar(Banco banco) {
        double valorSaque;
        List<Conta> contasDoCliente;
        int validadorClienteConta = 0;
        Scanner sc = new Scanner(System.in);

        // Instanciando o cliente e a conta fora do loop Do-While
        Cliente cli;
        Conta contaInput;

        do { // Loop para verificar se a conta chamada, existe nos registros do cliente.
            cli = buscarCliente(banco);
            contaInput = buscarConta(banco);

            if (cli.getContas() != null) { // Checa se o cliente possui contas
                contasDoCliente = cli.getContas(); // Armazena as contas do cliente em uma lista
                for (Conta conta : contasDoCliente) { // Itera entre as contas do cliente, buscando a conta inputada
                    if (conta == contaInput) { // Caso encontre, autoriza o validador
                        validadorClienteConta = 1;
                        break;
                    }
                }
                if (validadorClienteConta == 0)
                    System.out.println("Esse cliente não possui essa conta");
            } else
                System.out.println("Esse cliente não tem contas abertas");
        } while (validadorClienteConta == 0); // Sai do loop caso a conta esteja validada

        System.out.print("Digite o valor do saque: ");
        valorSaque = sc.nextDouble();

        if (contaInput.getSaldo() >= valorSaque) // Verifica se o valor do saque é menor ou igual a quantia existente da conta
            contaInput.sacar(valorSaque);
        else System.out.println("Valor de saque indisponivel");
    }
    public static void depositar(Banco banco){
        double valorDeposito;
        List<Conta> contasDoCliente;
        int validadorClienteConta = 0;
        Scanner sc = new Scanner(System.in);

        // Instanciando cliente e conta, fora do loop Do-While
        Cliente cli;
        Conta contaInput;
        do {
            cli = buscarCliente(banco);
            contaInput = buscarConta(banco);

            if (cli.getContas() != null) { // Checa se o cliente possui contas
                contasDoCliente = cli.getContas(); // Armazena as contas do cliente em uma lista
                for (Conta conta : contasDoCliente) { // Itera entre as contas do cliente, buscando a conta inputada
                    if (conta == contaInput) { // Caso encontre, autoriza o validador
                        validadorClienteConta = 1;
                        break;
                    }
                }
                if (validadorClienteConta == 0)
                    System.out.println("Esse cliente não possui essa conta");
            } else
                System.out.println("Esse cliente não tem contas abertas");
        } while (validadorClienteConta == 0); // Sai do loop caso a conta seja validada

        System.out.print("Digite o valor do depósito: ");
        valorDeposito = sc.nextDouble();
        contaInput.depositar(valorDeposito);
    }
    public static void transferencia(Banco banco){
        double valorTransferencia;
        List<Conta> contasDoCliente1;
        List<Conta> contasDoCliente2;
        int validadorClienteConta1 = 0;
        int validadorClienteConta2 = 0;
        Scanner sc = new Scanner(System.in);

        Cliente cli1;
        Conta contaInput1;
        Cliente cli2;
        Conta contaInput2;

        // Os loops Do-While abaixo ficaram extensos e repetitivos, incluse com as funções de transação anteriores
        // Poderia ter feito uma outra função para armazenar o loop e devolver um Map, contendo cliente e conta.

        System.out.println("====DIGITE AS INFORMAÇÕES DO CLIENTE PAGANTE====");
        // Validador do cliente 1
        do {
            cli1 = buscarCliente(banco);
            contaInput1 = buscarConta(banco);

            if (cli1.getContas() != null) { // Checa se o cliente possui contas
                contasDoCliente1 = cli1.getContas();
                for (Conta conta : contasDoCliente1) { // Itera entre as contas do cliente, buscando a conta inputada
                    if (conta == contaInput1) { // Caso encontre, autoriza o validador
                        validadorClienteConta1 = 1;
                        break;
                    }
                }
                if (validadorClienteConta1 == 0)
                    System.out.println("Esse cliente não possui essa conta");
            } else
                System.out.println("Esse cliente não tem contas abertas");
        } while (validadorClienteConta1 == 0);

        System.out.println("====DIGITE AS INFORMAÇÕES DO CLIENTE RECEPTOR====");
        // Validador do cliente 2
        do {
            cli2 = buscarCliente(banco);
            contaInput2 = buscarConta(banco);

            if (cli2.getContas() != null) { // Checa se o cliente possui contas
                contasDoCliente1 = cli2.getContas();
                for (Conta conta : contasDoCliente1) { // Itera entre as contas do cliente, buscando a conta inputada
                    if (conta == contaInput2) { // Caso encontre, autoriza o validador
                        validadorClienteConta2 = 1;
                        break;
                    }
                }
                if (validadorClienteConta2 == 0)
                    System.out.println("Esse cliente não possui essa conta");
            } else
                System.out.println("Esse cliente não tem contas abertas");
        } while (validadorClienteConta2 == 0);

        System.out.print("Digite o valor da transferência: ");
        valorTransferencia = sc.nextDouble();

        if (contaInput1.getSaldo() >= valorTransferencia) {
            contaInput1.sacar(valorTransferencia);
            contaInput2.depositar(valorTransferencia);
        }
        else System.out.println("Valor de transferência indisponivel");
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
                case 6 -> depositar(banco_gft); // REALIZAR SAQUE EM UMA CONTA
                case 7 -> sacar(banco_gft); // REALIZAR DEPÓSITO EM UMA CONTA
                case 8 -> transferencia(banco_gft); // REALIZAR TRANSFERENCIA DE UMA CONTA A OUTRA
            }
        }while(option != 0);


        System.out.println(banco_gft);

    }
}
