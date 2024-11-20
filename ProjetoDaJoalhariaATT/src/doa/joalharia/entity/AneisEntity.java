package doa.joalharia.entity;


import doa.joalharia.entity.interfac.Entity;
import doa.joalharia.entity.interfac.Joia;

public class AneisEntity implements Joia {

    private String nome;
    private Long id;
    private String tamanho;
    private String material;
    private double peso;
    private double preco;

    public AneisEntity() {
    }


    public AneisEntity( long id, String nome, String tamanho, String material, double peso, double preco) {
        this.nome = nome;
        this.tamanho=tamanho;
        this.material=material;
        this.preco=preco;
        this.peso=peso;
        this.id = id;

    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the ID of the author.
     *
     * @return The ID of the author.
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the author.
     *
     * @return The name of the author.
     */
    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
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


    // Sobrescrevendo o método toString()
    @Override
    public String toString() {
        return "Anel{" +
                "ID=" + id +
                ", Nome='" + nome + '\'' +
                ", Tamanho='" + tamanho + '\'' +
                ", Material='" + material + '\'' +
                ", Peso=" + peso +
                "g, Preço=" + preco+ " EUR" +
                '}';
    }

    // Método para salvar os dados no formato CSV
    public String toCSV() {
        return id + "," + nome + "," + tamanho + "," + material + "," + peso + "," + preco;
    }




}


