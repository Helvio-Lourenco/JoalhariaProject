package doa.joalharia.service;

import doa.joalharia.entity.AneisEntity;
import doa.joalharia.repository.AneisRepository;

import java.util.List;

public class AneisService {

    private final AneisRepository repository = new AneisRepository();

    public void adicionarAnel(AneisEntity anel) {
        repository.salvarAnel(anel);
    }

    public List<AneisEntity> listarAneis() {
        return repository.listarAneis();
    }

    public void excluirAnel(long id) {
        repository.excluirAnel(id);
    }

    public void atualizarPrecoAneis(long id, double novoPreco) {
        repository.atualizarPrecoAneis(id, novoPreco);
    }
}







