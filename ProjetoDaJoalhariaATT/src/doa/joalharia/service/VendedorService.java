package doa.joalharia.service;

import doa.joalharia.entity.VendedorEntity;
import doa.joalharia.repository.VendedorRepository;

import java.util.List;

public class VendedorService {

    private final VendedorRepository repository = new VendedorRepository();

    // Adicionar um vendedor
    public void adicionarVendedor(VendedorEntity vendedor) {
        repository.salvarVendedor(vendedor);
    }

    // Listar todos os vendedores
    public List<VendedorEntity> listarVendedores() {
        return repository.listarVendedores();
    }

    // Excluir um vendedor pelo NIF
    public void excluirVendedor(int nif) {
        repository.excluirVendedor(nif);
    }

    // Atualizar o salário de um vendedor
    public void atualizarSalarioVendedor(int nif, double novoSalario) {
        repository.atualizarSalarioVendedor(nif, novoSalario);
    }
}
