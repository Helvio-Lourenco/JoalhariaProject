package doa.joalharia.controller;

import java.util.Scanner;

public class JoiasController {

    private final AneisController aneisController;
    private final BrincosController brincoController;
    private final ColaresController colaresController;

    public JoiasController() {
        this.aneisController = new AneisController();
        this.brincoController = new BrincosController();
        this.colaresController = new ColaresController();
    }

    // Método para gerenciar o menu
    public void gerenciarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Aneis");
            System.out.println("2. Brincos");
            System.out.println("3. Colares");
            System.out.println("4. Sair");
            System.out.print("Escolha a joia que pretende gerenciar: ");

            int opcao = scanner.nextInt();

            if (opcao == 4) {
                System.out.println("Saindo do sistema...");
                break;  // Sai do loop
            }

            // Chama o metodo para gerenciar as joias com base na opção
            gerenciarJoias(opcao, scanner);
        }
    }

    // Método para gerenciar as opções de joias
    private void gerenciarJoias(int tipoJoia, Scanner scanner) {
        switch (tipoJoia) {
            case 1 -> {
                // Menu para Aneis
                System.out.println("\nMenu Aneis:");
                System.out.println("1. Adicionar Anel");
                System.out.println("2. Listar Aneis");
                System.out.println("3. Excluir Anel");
                System.out.println("4. Atualizar Preço de Anel");
                System.out.println("5. Voltar");
                System.out.print("Escolha uma opção: ");
                int subOpcao = scanner.nextInt();
                switch (subOpcao) {
                    case 1 -> aneisController.adicionarAneis(scanner);
                    case 2 -> aneisController.listarAneis();
                    case 3 -> aneisController.excluirAnel(scanner);
                    case 4 -> aneisController.atualizarPrecoAneis(scanner);
                    case 5 -> System.out.println("Voltando ao Menu Principal...");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }
            case 2 -> {
                // Menu para Brincos
                System.out.println("\nMenu Brincos:");
                System.out.println("1. Adicionar Brinco");
                System.out.println("2. Listar Brincos");
                System.out.println("3. Excluir Brinco");
                System.out.println("4. Atualizar Preço de Brinco");
                System.out.println("5. Voltar");
                System.out.print("Escolha uma opção: ");
                int subOpcao = scanner.nextInt();
                switch (subOpcao) {
                    case 1 -> brincoController.adicionarBrinco(scanner);
                    case 2 -> brincoController.listarBrincos();
                    case 3 -> brincoController.excluirBrinco(scanner);
                    case 4 -> brincoController.atualizarPrecoBrinco(scanner);
                    case 5 -> System.out.println("Voltando ao Menu Principal...");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }
            case 3 -> {
                // Menu para Colares
                System.out.println("\nMenu Colares:");
                System.out.println("1. Adicionar Colar");
                System.out.println("2. Listar Colares");
                System.out.println("3. Excluir Colar");
                System.out.println("4. Atualizar Preço de Colar");
                System.out.println("5. Voltar");
                System.out.print("Escolha uma opção: ");
                int subOpcao = scanner.nextInt();
                switch (subOpcao) {
                    case 1 -> colaresController.adicionarColar(scanner);
                    case 2 -> colaresController.listarColares();
                    case 3 -> colaresController.excluirColar(scanner);
                    case 4 -> colaresController.atualizarPrecoColar(scanner);
                    case 5 -> System.out.println("Voltando ao Menu Principal...");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }
}
