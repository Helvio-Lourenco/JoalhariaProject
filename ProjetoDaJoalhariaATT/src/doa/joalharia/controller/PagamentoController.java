package doa.joalharia.controller;

import doa.joalharia.entity.PagamentosEntity;
import doa.joalharia.service.PagamentoService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PagamentoController {
    private PagamentoService pagamentoService;

    public PagamentoController() {
        this.pagamentoService = new PagamentoService();
    }

    public void menuPagamentos() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu de Pagamentos:");
            System.out.println("1. Registrar Pagamento");
            System.out.println("2. Listar Pagamentos");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    registrarPagamento(scanner, 0);
                    break;
                case 2:
                    listarPagamentos();
                    break;
                case 3:
                    return; // Voltar para o menu anterior
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
//parou aqui

    public String registrarPagamento(Scanner scanner, double valorTotal) {
        // Exibir o valor total
        System.out.println("O valor total a pagar é: " + valorTotal);

        // Solicitar o método de pagamento
        System.out.print("Escolha o Método de pagamento (CartaodeCredito, CartaoBancario, TranferenciaBancaria, DinheiroAPagar, Mbway): ");
        String metodoStr = scanner.nextLine();

        try {
            PagamentosEntity.MetodoPagamento metodo = PagamentosEntity.MetodoPagamento.valueOf(metodoStr);

            // Geração de ID aleatório
            Long id = pagamentoService.gerarIdAleatorio();

            // Define a data atual para o pagamento
            Date dataAtual = new Date();

            // Criação do objeto de pagamento
            PagamentosEntity pagamento = new PagamentosEntity(id, metodo, dataAtual, valorTotal);

            // Registrar o pagamento
            pagamentoService.registrarPagamento(pagamento);

            // Confirmação ao usuário
            System.out.println("Pagamento efetuado com sucesso!");

            // Retornar o método de pagamento escolhido
            return metodoStr;
        } catch (IllegalArgumentException e) {
            System.out.println("Método de pagamento inválido. Tente novamente.");
            return null; // Retornar null caso o método seja inválido
        }
    }

    private void listarPagamentos() {
        List<PagamentosEntity> pagamentos = pagamentoService.listarPagamentos();
        for (PagamentosEntity pagamento : pagamentos) {
            System.out.println(pagamento);
        }
    }
}
