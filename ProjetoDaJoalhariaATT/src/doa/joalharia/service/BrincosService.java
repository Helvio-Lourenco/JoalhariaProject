package doa.joalharia.service;

import doa.joalharia.entity.BrincosEntity;
import doa.joalharia.repository.BrincosRepository;

import java.util.List;

public class BrincosService {

    private final BrincosRepository repository = new BrincosRepository();

    public void adicionarBrinco(BrincosEntity brinco) {
        repository.salvarBrinco(brinco);
    }

    public List<BrincosEntity> listarBrincos() {
        return repository.listarBrincos();
    }

    public void excluirBrinco(long id) {
        repository.excluirBrinco(id);
    }

    public void atualizarPrecoBrincos(long id, double novoPreco) {
        repository.atualizarPrecoBrincos(id, novoPreco);
    }
}
