package doa.joalharia.service;

import doa.joalharia.entity.PagamentosEntity;
import doa.joalharia.repository.PagamentoRepository;

import java.util.List;
import java.util.Random;

public class PagamentoService {

    private Random random = new Random();

    // Método para gerar um ID aleatório do tipo Long
    public Long gerarIdAleatorio() {
        return Math.abs(random.nextLong()); // Gera um número aleatório positivo do tipo Long
    }
    private PagamentoRepository pagamentoRepository;

    public PagamentoService() {
        this.pagamentoRepository = new PagamentoRepository();
    }

    public void registrarPagamento(PagamentosEntity pagamento) {
        pagamentoRepository.salvarPagamento(pagamento);
    }

    public List<PagamentosEntity> listarPagamentos() {
        return pagamentoRepository.listarPagamentos();
    }

    public List<PagamentosEntity> listarPagamentosPorPedido(Long pedidoId) {
        return pagamentoRepository.listarPagamentosPorPedido(pedidoId);
    }
}
