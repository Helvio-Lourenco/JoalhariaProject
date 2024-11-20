package doa.joalharia.controller;

import doa.joalharia.entity.VendedorEntity;
import doa.joalharia.service.VendedorService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class VendedoresController {

    private final VendedorService vendedorService;

    public VendedoresController() {
        this.vendedorService = new VendedorService(); // Instancia o serviço de vendedores
    }

    // Adicionar um novo vendedor
    public void adicionarVendedor(Scanner scanner) {
        System.out.println("Digite os dados do novo vendedor:");

        scanner.nextLine(); // Consumir o \n após nextInt()

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("NIF: ");
        int nif = scanner.nextInt();  // Usando 'int' para NIF

        scanner.nextLine(); // Consumir o \n após nextInt()

        System.out.print("Data de Contratação (YYYY-MM-DD): ");
        LocalDate dataDeContratacao = LocalDate.parse(scanner.nextLine());

        System.out.print("Salário: ");
        double salario = scanner.nextDouble();

        System.out.print("Vendas Feitas: ");
        int vendasFeitas = scanner.nextInt();

        VendedorEntity novoVendedor = new VendedorEntity(nome, nif, dataDeContratacao, salario, vendasFeitas);
        vendedorService.adicionarVendedor(novoVendedor);

        System.out.println("Vendedor adicionado com sucesso!");
    }

    // Listar todos os vendedores
    public void listarVendedores() {
        List<VendedorEntity> vendedores = vendedorService.listarVendedores();
        for (VendedorEntity vendedor : vendedores) {
            System.out.println(vendedor);
        }
    }

    // Excluir um vendedor
    public void excluirVendedor(Scanner scanner) {
        System.out.print("Digite o NIF do vendedor a ser excluído: ");
        int nifParaExcluir = scanner.nextInt();  // Alterado para 'nif' em vez de 'id'
        vendedorService.excluirVendedor(nifParaExcluir);
        System.out.println("Vendedor excluído com sucesso!");
    }

    // Atualizar o salário de um vendedor
    public void atualizarSalarioVendedor(Scanner scanner) {
        System.out.print("Digite o NIF do vendedor para atualizar o salário: ");
        int nifParaAtualizar = scanner.nextInt();  // Alterado para 'nif' em vez de 'id'

        System.out.print("Digite o novo salário: ");
        double novoSalario = scanner.nextDouble();

        vendedorService.atualizarSalarioVendedor(nifParaAtualizar, novoSalario);
        System.out.println("Salário atualizado com sucesso!");
    }
}

