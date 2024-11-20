package doa.joalharia.service;

import doa.joalharia.entity.ClientesEntity;
import doa.joalharia.repository.ClientesRepository;

import java.util.List;

public class ClientesService {
    private final ClientesRepository clientesRepository;

    public ClientesService() {
        this.clientesRepository = new ClientesRepository();
    }

    public void adicionarCliente(ClientesEntity cliente) {
        clientesRepository.salvarCliente(cliente);
    }

    public List<ClientesEntity> listarClientes() {
        return clientesRepository.listarClientes();
    }

    public void excluirCliente(String nif) {
        clientesRepository.excluirCliente(nif);
    }
}
