package doa.joalharia.controller;

import java.util.Scanner;

public class FuncionariosController {

    
    private final GerentesController gerentesController;
    private final VendedoresController vendedoresController;

    public FuncionariosController() {
        this.gerentesController = new GerentesController();
        this.vendedoresController = new VendedoresController();
    }

    public void gerenciarFuncionarios() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Funcionários:");
            System.out.println("1. Gerentes");
            System.out.println("2. Vendedores");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            if (opcao == 3) {
                System.out.println("Saindo do menu de funcionários...");
                break;
            }

            gerenciarTipoFuncionario(opcao, scanner);
        }
    }

    private void gerenciarTipoFuncionario(int tipo, Scanner scanner) {
        switch (tipo) {
            case 1 -> gerenciarMenuGerentes(scanner);
            case 2 -> gerenciarMenuVendedores(scanner);
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private void gerenciarMenuGerentes(Scanner scanner) {
        System.out.println("\nMenu Gerentes:");
        System.out.println("1. Adicionar Gerente");
        System.out.println("2. Listar Gerentes");
        System.out.println("3. Excluir Gerente");
        System.out.println("4. Atualizar Salário de Gerente");
        System.out.println("5. Voltar");
        System.out.print("Escolha uma opção: ");

        int subOpcao = scanner.nextInt();
        switch (subOpcao) {
            case 1 -> gerentesController.adicionarGerente(scanner);
            case 2 -> gerentesController.listarGerentes();
            case 3 -> gerentesController.excluirGerente(scanner);
            case 4 -> gerentesController.atualizarSalarioGerente(scanner);
            case 5 -> System.out.println("Voltando ao menu de funcionários...");
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private void gerenciarMenuVendedores(Scanner scanner) {
        System.out.println("\nMenu Vendedores:");
        System.out.println("1. Adicionar Vendedor");
        System.out.println("2. Listar Vendedores");
        System.out.println("3. Excluir Vendedor");
        System.out.println("4. Atualizar Salário de Vendedor");
        System.out.println("5. Voltar");
        System.out.print("Escolha uma opção: ");

        int subOpcao = scanner.nextInt();
        switch (subOpcao) {
            case 1 -> vendedoresController.adicionarVendedor(scanner);
            case 2 -> vendedoresController.listarVendedores();
            case 3 -> vendedoresController.excluirVendedor(scanner);
            case 4 -> vendedoresController.atualizarSalarioVendedor(scanner);
            case 5 -> System.out.println("Voltando ao menu de funcionários...");
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }
}
