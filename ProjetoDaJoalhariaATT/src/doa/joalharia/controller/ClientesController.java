package doa.joalharia.controller;

import doa.joalharia.entity.ClientesEntity;
import doa.joalharia.repository.ClientesRepository;
import doa.joalharia.service.ClientesService;

import java.util.List;
import java.util.Scanner;

public class ClientesController {
    private ClientesRepository clientesRepository;

    public ClientesController() {
        clientesRepository = new ClientesRepository();
    }

    // Método para gerenciar o menu de clientes
    public void menuClientes() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu de Clientes:");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Excluir Cliente");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o \n após o nextInt()

            switch (opcao) {
                case 1:
                    // Adicionar Cliente
                    System.out.println("Digite os dados do novo cliente:");

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("NIF: ");
                    String nif = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Contacto: ");
                    String contacto = scanner.nextLine();

                    System.out.print("Morada: ");
                    String morada = scanner.nextLine();

                    ClientesEntity cliente = new ClientesEntity(nif,nome, email, contacto, morada);
                    clientesRepository.salvarCliente(cliente);
                    System.out.println("Cliente adicionado com sucesso!");
                    break;

                case 2:
                    // Listar Clientes
                    System.out.println("Lista de Clientes:");
                    List<ClientesEntity> clientes = clientesRepository.listarClientes();
                    for (ClientesEntity c : clientes) {
                        System.out.println(c);
                    }
                    break;

                case 3:
                    // Excluir Cliente
                    System.out.print("Digite o NIF do cliente a ser excluído: ");
                    String nifParaExcluir = scanner.nextLine();
                    clientesRepository.excluirCliente(nifParaExcluir);
                    System.out.println("Cliente excluído com sucesso!");
                    break;

                case 4:
                    // Sair
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }
}
