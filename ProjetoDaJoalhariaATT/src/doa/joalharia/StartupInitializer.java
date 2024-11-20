package doa.joalharia;


import doa.joalharia.controller.FuncionariosController;
import doa.joalharia.controller.PagamentoController;
import doa.joalharia.controller.PedidoController;
import doa.joalharia.controller.ClientesController;

import java.util.Scanner;

public class StartupInitializer {


    private Scanner scanner = new Scanner(System.in);
    private PedidoController pedidoController = new PedidoController();
    private PagamentoController pagamentoController = new PagamentoController();
    private FuncionariosController funcionariosController = new FuncionariosController();
    private ClientesController clientesController = new ClientesController();

    public StartupInitializer() {
        while (true) {
            System.out.println("\n======== Menu Joalheria ========");
            System.out.println("1 - Gerenciar Pedidos");
            System.out.println("2 - Gerenciar Pagamentos");
            System.out.println("3 - Gerenciar Funcionários");
            System.out.println("4 - Gerenciar clientes");
            System.out.println("5 - Encerrar Programa");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    pedidoController.menuPedidos();
                    break;

                case 2:
                    pagamentoController.menuPagamentos();
                    break;

                case 3:
                    System.out.println("Acesso restrito apenas para Gerente. Digite o Código de gerente: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    if (codigo == 293) {
                        System.out.println("Acesso autorizado!");
                        funcionariosController.gerenciarFuncionarios();
                    } else {
                        System.out.println("Acesso Negado!");
                    }
                    break;

                case 4:
                     clientesController.menuClientes();
                    return;

                case 5:
                    System.out.println("Encerrando o sistema. Até logo!");
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}