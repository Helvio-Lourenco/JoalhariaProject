package doa.joalharia.service;

import doa.joalharia.entity.ColaresEntity;
import doa.joalharia.repository.ColaresRepository;

import java.util.List;

public class ColaresService {

    private final ColaresRepository repository = new ColaresRepository();

    public void adicionarColar(ColaresEntity colar) {
        repository.salvarColar(colar);
    }

    public List<ColaresEntity> listarColares() {
        return repository.listarColares();
    }

    public void excluirColar(long id) {
        repository.excluirColar(id);
    }

    public void atualizarPrecoColares(long id, double novoPreco) {
        repository.atualizarPrecoColares(id, novoPreco);
    }
}
