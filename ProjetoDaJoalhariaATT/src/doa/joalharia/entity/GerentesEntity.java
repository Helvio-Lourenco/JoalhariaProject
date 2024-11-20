package doa.joalharia.entity;

import doa.joalharia.entity.interfac.Entity;

import java.time.LocalDate;

public class GerentesEntity {


    private int vendasEquipe;
    private String nome;
    private int nif;
    private double salario;
    private LocalDate dataDeContratacao;

    public GerentesEntity ( String nome, int nif, LocalDate dataDeContratacao, double salario, int vendasEquipe){

        this.vendasEquipe=vendasEquipe;
        this.dataDeContratacao=dataDeContratacao;
        this.nif=nif;
        this.salario=salario;
        this.nome=nome;

    }



    public int getVendasEquipe() {
        return vendasEquipe;
    }

    public void setVendasEquipe(int vendasEquipe) {
        this.vendasEquipe = vendasEquipe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getDataDeContratacao() {
        return dataDeContratacao;
    }

    public void setDataDeContratacao(LocalDate dataDeContratacao) {
        this.dataDeContratacao = dataDeContratacao;
    }

    @Override
    public String toString() {
        // Retorna uma representação da classe GerenteEntity
        return "Nome=" + nome + ", NIF=" + nif + ", Salário=" + salario +
                ", Data de Contratação=" + dataDeContratacao + ", Vendas da Equipe=" + vendasEquipe + "]";
    }

    // Método para salvar os dados no formato CSV
    public String toCSV() {
        return nome + "," + nif + "," + salario + "," + dataDeContratacao + "," + salario+","+ vendasEquipe;
    }


}

