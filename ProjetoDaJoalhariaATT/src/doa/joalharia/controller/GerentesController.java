package doa.joalharia.controller;

import doa.joalharia.entity.GerentesEntity;
import doa.joalharia.service.GerentesService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GerentesController {

    private final GerentesService gerenteService;

    public GerentesController() {
        this.gerenteService = new GerentesService(); // Instancia o serviço de gerentes
    }

    // Adicionar um novo gerente
    public void adicionarGerente(Scanner scanner) {
        System.out.println("Digite os dados do novo gerente:");

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

        System.out.print("Vendas da Equipe: ");
        int vendasEquipe = scanner.nextInt();  // Novo atributo

        // Criando o novo gerente com todos os dados
        GerentesEntity novoGerente = new GerentesEntity(nome, nif, dataDeContratacao, salario, vendasEquipe);

        // Adicionando o gerente através do serviço
        gerenteService.adicionarGerente(novoGerente);

        System.out.println("Gerente adicionado com sucesso!");
    }

    // Listar todos os gerentes
    public void listarGerentes() {
        List<GerentesEntity> gerentes = gerenteService.listarGerentes();
        for (GerentesEntity gerente : gerentes) {
            System.out.println(gerente);
        }
    }

    // Excluir um gerente
    public void excluirGerente(Scanner scanner) {
        System.out.print("Digite o NIF do gerente a ser excluído: ");
        int nifParaExcluir = scanner.nextInt();  // Alterado para 'nif' em vez de 'id'
        gerenteService.excluirGerente(nifParaExcluir);
        System.out.println("Gerente excluído com sucesso!");
    }

    // Atualizar o salário de um gerente
    public void atualizarSalarioGerente(Scanner scanner) {
        System.out.print("Digite o NIF do gerente para atualizar o salário: ");
        int nifParaAtualizar = scanner.nextInt();  // Alterado para 'nif' em vez de 'id'

        System.out.print("Digite o novo salário: ");
        double novoSalario = scanner.nextDouble();

        gerenteService.atualizarSalarioGerente(nifParaAtualizar, novoSalario);
        System.out.println("Salário atualizado com sucesso!");
    }
}
