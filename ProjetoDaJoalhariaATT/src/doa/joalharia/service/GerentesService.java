package doa.joalharia.service;

import doa.joalharia.entity.GerentesEntity;
import doa.joalharia.repository.GerentesRepository;

import java.util.List;

public class GerentesService {
    private final GerentesRepository repository = new GerentesRepository();

    // Adicionar um gerente
    public void adicionarGerente(GerentesEntity gerente) {
        repository.salvarGerente(gerente);
    }

    // Listar todos os gerentes
    public List<GerentesEntity> listarGerentes() {
        return repository.listarGerentes();
    }

    // Excluir um gerente
    public void excluirGerente(int nif) {
        repository.excluirGerente(nif);
    }

    // Atualizar o salário de um gerente
    public void atualizarSalarioGerente(int nif, double novoSalario) {
        repository.atualizarSalarioGerente(nif, novoSalario);
    }
}