package doa.joalharia.entity;


import doa.joalharia.entity.interfac.Entity;
import doa.joalharia.entity.interfac.Joia;

public class BrincosEntity implements Joia {

    private String nome;
    private Long id;
    private String tipoFecho;  // Atributo exclusivo de Brinco
    private String material;
    private double peso;
    private double preco;

    public BrincosEntity() {
    }

    public BrincosEntity(long id, String nome, String tipoFecho, String material, double peso, double preco) {
        this.id = id;
        this.nome = nome;
        this.tipoFecho = tipoFecho;
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

    public String getTipoFecho() {
        return tipoFecho;
    }

    public void setTipoFecho(String tipoFecho) {
        this.tipoFecho = tipoFecho;
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
        return "Brinco{" +
                "ID=" + id +
                ", Nome='" + nome + '\'' +
                ", Tipo Fecho='" + tipoFecho + '\'' +
                ", Material='" + material + '\'' +
                ", Peso=" + peso + "g" +
                ", Preço=" + preco + " EUR" +
                '}';
    }

    public String toCSV() {
        return id + "," + nome + "," + tipoFecho + "," + material + "," + peso + "," + preco;
    }
}