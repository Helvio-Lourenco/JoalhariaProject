package doa.joalharia.entity;

import doa.joalharia.entity.interfac.Entity;

import java.time.LocalDate;

public class VendedorEntity {


    private int vendasFeitas;
    private String nome;
    private int nif;
    private LocalDate dataDeContratacao;
    private double salario;


    public VendedorEntity( String nome, int nif, LocalDate dataDeContratacao, double salario, int vendasFeitas) {

        this.vendasFeitas=vendasFeitas;
        this.nome=nome;
        this.nif=nif;
        this.dataDeContratacao=dataDeContratacao;
        this.salario=salario;
    }



    public int getVendasFeitas() {
        return vendasFeitas;
    }

    public void setVendasFeitas(int vendasFeitas) {
        this.vendasFeitas = vendasFeitas;
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

    public LocalDate getDataDeContratacao() {
        return dataDeContratacao;
    }

    public void setDataDeContratacao(LocalDate dataDeContratacao) {
        this.dataDeContratacao = dataDeContratacao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    @Override
    public String toString(){
        return "Vendendor Nome: "+nome+" ; Nif: "+nif+" ; Data de Contratação: "+ dataDeContratacao+ "; Salario: "+salario+ "; Vendas feitas: "+ vendasFeitas ;
    }
    // Método para salvar os dados no formato CSV
    public String toCSV() {
        return  nome + "," + nif + "," + dataDeContratacao + "," + salario+","+ vendasFeitas;
    }

}
