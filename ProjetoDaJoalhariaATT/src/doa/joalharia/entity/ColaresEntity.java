package doa.joalharia.entity;

import doa.joalharia.entity.interfac.Entity;
import doa.joalharia.entity.interfac.Joia;

public class ColaresEntity implements Joia {

    private String nome;
    private Long id;
    private String comprimento;  // Atributo exclusivo de Colar
    private String material;
    private double peso;
    private double preco;

    public ColaresEntity() {
    }

    public ColaresEntity(long id, String nome, String comprimento, String material, double peso, double preco) {
        this.id = id;
        this.nome = nome;
        this.comprimento = comprimento;
        this.material = material;
        this.peso = peso;
        this.preco = preco;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComprimento() {
        return comprimento;
    }

    public void setComprimento(String comprimento) {
        this.comprimento = comprimento;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Colar{" +
                "ID=" + id +
                ", Nome='" + nome + '\'' +
                ", Comprimento='" + comprimento + '\'' +
                ", Material='" + material + '\'' +
                ", Peso=" + peso + "g" +
                ", Preço=" + preco + " EUR" +
                '}';
    }

    public String toCSV() {
        return id + "," + nome + "," + comprimento + "," + material + "," + peso + "," + preco;
    }
}

