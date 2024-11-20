package doa.joalharia.controller;

import doa.joalharia.entity.ColaresEntity;
import doa.joalharia.service.ColaresService;

import java.util.List;
import java.util.Scanner;

public class ColaresController {

    private final ColaresService colaresService = new ColaresService();

    // Método para adicionar um colar
    public void adicionarColar(Scanner scanner) {
        System.out.println("Digite os dados do novo colar:");

        // Gerar ID automaticamente (por exemplo, com base no tempo atual)
        long id = System.currentTimeMillis(); // ID gerado automaticamente
        System.out.println("ID gerado automaticamente: " + id);

        scanner.nextLine(); // Consumir o \n após nextInt()

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Comprimento: ");
        String comprimento = scanner.nextLine();

        System.out.print("Material: ");
        String material = scanner.nextLine();

        System.out.print("Peso: ");
        double peso = scanner.nextDouble();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        // Cria o novo colar com o ID gerado automaticamente
        ColaresEntity novoColar = new ColaresEntity(id, nome, comprimento, material, peso, preco);
        colaresService.adicionarColar(novoColar);

        System.out.println("Colar adicionado com sucesso!");
    }

    // Método para listar todos os colares
    public void listarColares() {
        System.out.println("Listar Colares:");
        List<ColaresEntity> colares = colaresService.listarColares();
        for (ColaresEntity colar : colares) {
            System.out.println(colar);
        }
    }

    // Método para excluir um colar
    public void excluirColar(Scanner scanner) {
        System.out.print("Digite o ID do colar a ser excluído: ");
        long idParaExcluir = scanner.nextLong();
        colaresService.excluirColar(idParaExcluir);
    }

    // Método para atualizar o preço de um colar
    public void atualizarPrecoColar(Scanner scanner) {
        System.out.print("Digite o ID do colar para atualizar o preço: ");
        long idParaAtualizar = scanner.nextLong();

        System.out.print("Digite o novo preço: ");
        double novoPreco = scanner.nextDouble();

        colaresService.atualizarPrecoColares(idParaAtualizar, novoPreco);
    }
}
