package doa.joalharia.controller;

import doa.joalharia.entity.BrincosEntity;
import doa.joalharia.service.BrincosService;

import java.util.List;
import java.util.Scanner;

public class BrincosController {

    private final BrincosService brincoService = new BrincosService();

    // Método para adicionar um brinco
    public void adicionarBrinco(Scanner scanner) {
        System.out.println("Digite os dados do novo brinco:");

        // Gerar ID automaticamente (por exemplo, com base no tempo atual)
        long id = System.currentTimeMillis(); // ID gerado automaticamente
        System.out.println("ID gerado automaticamente: " + id);

        scanner.nextLine(); // Consumir o \n após nextInt()

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Tipo de Fecho: ");
        String tipoFecho = scanner.nextLine();

        System.out.print("Material: ");
        String material = scanner.nextLine();

        System.out.print("Peso: ");
        double peso = scanner.nextDouble();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        // Cria o novo brinco com o ID gerado automaticamente
        BrincosEntity novoBrinco = new BrincosEntity(id, nome, tipoFecho, material, peso, preco);
        brincoService.adicionarBrinco(novoBrinco);

        System.out.println("Brinco adicionado com sucesso!");
    }

    // Método para listar todos os brincos
    public void listarBrincos() {
        System.out.println("Listar Brincos:");
        List<BrincosEntity> brincos = brincoService.listarBrincos();
        for (BrincosEntity brinco : brincos) {
            System.out.println(brinco);
        }
    }

    // Método para excluir um brinco
    public void excluirBrinco(Scanner scanner) {
        System.out.print("Digite o ID do brinco a ser excluído: ");
        long idParaExcluir = scanner.nextLong();
        brincoService.excluirBrinco(idParaExcluir);
    }

    // Método para atualizar o preço de um brinco
    public void atualizarPrecoBrinco(Scanner scanner) {
        System.out.print("Digite o ID do brinco para atualizar o preço: ");
        long idParaAtualizar = scanner.nextLong();

        System.out.print("Digite o novo preço: ");
        double novoPreco = scanner.nextDouble();

        brincoService.atualizarPrecoBrincos(idParaAtualizar, novoPreco);
    }
}
