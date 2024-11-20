package doa.joalharia.controller;

import doa.joalharia.entity.ClientesEntity;
import doa.joalharia.entity.PedidosEntity;
import doa.joalharia.entity.interfac.Joia;
import doa.joalharia.repository.AneisRepository;
import doa.joalharia.repository.BrincosRepository;
import doa.joalharia.repository.ClientesRepository;
import doa.joalharia.repository.ColaresRepository;
import doa.joalharia.service.PedidoService;


import java.util.*;

public class PedidoController {private PedidoService pedidoService;
    private ClientesRepository clientesRepository;
    public PedidoController() {
        this.pedidoService = new PedidoService();
        this.clientesRepository = new ClientesRepository();
    }


    public void menuPedidos() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu de Pedidos:");
            System.out.println("1. Adicionar Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("3. Atualizar Status do Pedido");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    adicionarPedido(scanner);
                    break;
                case 2:
                    listarPedidos();
                    break;
                case 3:
                    atualizarStatusPedido(scanner);
                    break;
                case 4:
                    return; // Voltar para o menu anterior
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void adicionarPedido(Scanner scanner) {
        System.out.println("Digite o NIF do cliente:");


        String nifCliente = scanner.nextLine();
        ClientesEntity cliente = clientesRepository.buscarClientePorNif(nifCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return; // Interrompe o fluxo se o cliente não existir

        }

        // Criar a lista de itens do pedido
        List<Joia> itens = new ArrayList<>();
        boolean adicionandoItens = true;
        while (adicionandoItens) {
            System.out.print("Digite o tipo de joia (Anel, Colar, Brinco) ou 'avancar' para finalizar: ");
            String tipoJoia = scanner.nextLine();
            if (tipoJoia.equalsIgnoreCase("avancar")) {
                adicionandoItens = false;  // Interrompe o loop se 'avancar' for digitado
            } else {
                System.out.print("Digite o ID da joia: ");
                Long idJoia = scanner.nextLong();
                scanner.nextLine(); // Consumir quebra de linha

                Joia joia = buscarJoiaPorTipoEId(tipoJoia, idJoia);
                if (joia != null) {
                    itens.add(joia);
                    System.out.println("Joia adicionada: " + joia.getNome());
                } else {
                    System.out.println("Joia não encontrada.");
                }
            }
        }

        // Verificar se há itens antes de avançar para o pagamento
        if (itens.isEmpty()) {
            System.out.println("Não há itens no pedido. Cancelando.");
            return; // Se não houver itens, não é possível continuar
        }


        double valorTotal = calcularValorTotal(itens);
        System.out.println("Valor total calculado: " + valorTotal);



        // Criar uma instância do PagamentoController
        PagamentoController pagamentoController = new PagamentoController();
        String metodoDePagar = pagamentoController.registrarPagamento(scanner, valorTotal);





      // A criação do pedido com todos os parâmetros necessários (id, data, status, etc.)
        Long idPedido = (long) (Math.random() * 1000);
        Date dataPedido = new Date();  // Definir a data atual do pedido
        PedidosEntity.Status statusPedido = PedidosEntity.Status.Pendente;  // Definir o status inicial






        // Criar o pedido com os parâmetros necessários
        PedidosEntity pedido = new PedidosEntity(idPedido, dataPedido, cliente, itens, statusPedido, new ArrayList<>(), valorTotal, metodoDePagar);

        pedidoService.adicionarPedido(pedido);

        System.out.println("A processar...");

        System.out.println("Pedido adicionado com sucesso!");
    }



    private double calcularValorTotal(List<Joia> itens) {
        return itens.stream().mapToDouble(Joia::getPreco).sum();
    }

    private ClientesEntity buscarClientePorNif(String nif) {
        // Método fictício que busca o cliente pelo NIF no banco de dados ou lista
        // Retorne o cliente correspondente ou null se não encontrar
        return clientesRepository.buscarClientePorNif(nif);
    }

    private void listarPedidos() {
        List<PedidosEntity> pedidos = pedidoService.listarPedidos();
        for (PedidosEntity pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    private void atualizarStatusPedido(Scanner scanner) {
        System.out.print("Digite o ID do pedido a ser atualizado: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("Digite o novo status (Pendente, Aceito, Entregue, Cancelado): ");
        String statusStr = scanner.nextLine();
        PedidosEntity.Status novoStatus = PedidosEntity.Status.valueOf(statusStr);

        pedidoService.atualizarStatusPedido(id, novoStatus);
        System.out.println("Status do pedido atualizado com sucesso!");
    }


    private Joia buscarJoiaPorTipoEId(String tipoJoia, Long idJoia) {
        switch (tipoJoia.toLowerCase()) {
            case "anel":
                return new AneisRepository().buscarPorId(idJoia);
            case "colar":
                return new ColaresRepository().buscarPorId(idJoia);
            case "brinco":
                return new BrincosRepository().buscarPorId(idJoia);
            default:
                System.out.println("Tipo de joia inválido.");
                return null;
        }

    }
}
