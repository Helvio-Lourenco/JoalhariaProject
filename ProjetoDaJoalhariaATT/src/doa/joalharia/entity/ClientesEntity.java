package doa.joalharia.entity;



import doa.joalharia.entity.interfac.Entity;

public class ClientesEntity  {
    // Atributos

    private String nome;
    private String nif;  // Alterado para String
    private String email;
    private String contacto;
    private String morada;

    // Construtor
    public ClientesEntity( String nif, String nome , String email, String contacto, String morada) {

        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.contacto = contacto;
        this.morada = morada;
    }

    // Getters e Setters


    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    // toString
    @Override
    public String toString() {
        return "Cliente [Nif=" + nif + ", Nome=" + nome + ", Email=" + email +
                ", Contacto=" + contacto + ", Morada=" + morada + "]";
    }

    // Método para salvar os dados no formato CSV
    public String toCSV() {
        return  nif + "," + nome + "," + email + "," + contacto + "," + morada;
    }
}

