package doa.joalharia.service;

import doa.joalharia.entity.PedidosEntity;
import doa.joalharia.repository.PedidoRepository;

import java.util.List;

public class PedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoService() {
        this.pedidoRepository = new PedidoRepository();
    }

    public void adicionarPedido(PedidosEntity pedido) {
        pedidoRepository.salvarPedido(pedido);
    }

    public List<PedidosEntity> listarPedidos() {
        return pedidoRepository.listarPedidos();
    }

    public void atualizarStatusPedido(Long id, PedidosEntity.Status novoStatus) {
        List<PedidosEntity> pedidos = pedidoRepository.listarPedidos();
        for (PedidosEntity pedido : pedidos) {
            if (pedido.getId().equals(id)) {
                pedido.setEstado(novoStatus);
                pedidoRepository.salvarPedido(pedido);
                break;
            }
        }
    }
}
