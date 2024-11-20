package doa.joalharia.controller;

import doa.joalharia.entity.AneisEntity;
import doa.joalharia.service.AneisService;

import java.util.List;
import java.util.Scanner;

public class AneisController {

    private final AneisService aneisService;

    public AneisController() {
        this.aneisService = new AneisService(); // Instancia o serviço
    }

    public void adicionarAneis(Scanner scanner) {
        System.out.println("Digite os dados do novo anel:");

        // Gerar ID automaticamente (por exemplo, com base no tempo atual)
        long id = System.currentTimeMillis();  // ID gerado automaticamente
        System.out.println("ID gerado automaticamente: " + id);

        scanner.nextLine(); // Consumir o \n após nextInt()

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();

        System.out.print("Material: ");
        String material = scanner.nextLine();

        System.out.print("Peso: ");
        double peso = scanner.nextDouble();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        // Cria o novo anel com o ID gerado automaticamente
        AneisEntity novoAnel = new AneisEntity(id, nome, tamanho, material, peso, preco);
        aneisService.adicionarAnel(novoAnel);

        System.out.println("Anel adicionado com sucesso!");
    }

    public void listarAneis() {
        List<AneisEntity> aneis = aneisService.listarAneis();
        for (AneisEntity anel : aneis) {
            System.out.println(anel);
        }
    }

    public void excluirAnel(Scanner scanner) {
        System.out.print("Digite o ID do anel a ser excluído: ");
        long idParaExcluir = scanner.nextLong();
        aneisService.excluirAnel(idParaExcluir);
    }

    public void atualizarPrecoAneis(Scanner scanner) {
        System.out.print("Digite o ID do anel para atualizar o preço: ");
        long idParaAtualizar = scanner.nextLong();

        System.out.print("Digite o novo preço: ");
        double novoPreco = scanner.nextDouble();

        aneisService.atualizarPrecoAneis(idParaAtualizar, novoPreco);
    }
 }
