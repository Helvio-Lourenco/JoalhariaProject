package doa.joalharia.entity;

import doa.joalharia.entity.interfac.Entity;
import doa.joalharia.entity.interfac.Joia;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PedidosEntity implements Entity<Long> {


    public enum Status {
        Entregue, Pendente, Aceito, Cancelado
    }

    private Long id;
    private Date data;
    ;
    private ClientesEntity cliente;
    private List<Joia> itens;
    private double valorTotal;
    private Status estado;
    private List<Joia> pagamentos;
    private String metodoDePagar;


    public PedidosEntity(Long id, Date data, ClientesEntity cliente, List<Joia> itens, Status estado, List<Joia> pagamentos, double valorTotal, String metodoDePagar) {
        this.data = data;
        this.cliente = cliente;
        this.estado = estado;
        this.valorTotal = valorTotal;
        this.pagamentos = new ArrayList<>();
        this.id = id;
        this.itens = itens;
        this.metodoDePagar = metodoDePagar;

    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ClientesEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClientesEntity cliente) {
        this.cliente = cliente;
    }

    public List<Joia> getItens() {
        return itens;
    }

    public void setItens(List<Joia> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getEstado() {
        return estado;
    }

    public void setEstado(Status estado) {
        this.estado = estado;
    }

    public List<Joia> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Joia> pagamentos) {
        this.pagamentos = pagamentos;
    }

    private Long gerarId() {
        return (long) (Math.random() * 1000);  // Gera um ID aleatório entre 0 e 100000
    }

    public String getMetodoDePagar() {
        return metodoDePagar;
    }

    public void setMetodoDePagar(String metodoDePagar) {
        this.metodoDePagar = metodoDePagar;
    }


    @Override
    public String toString() {
        // Exibir os itens do pedido como uma lista de IDs de joias
        StringBuilder itensString = new StringBuilder();
        for (Joia joia : itens) {
            itensString.append(joia.getId()).append(";");  // Usar ponto-e-vírgula
        }

        // Remover a última vírgula e espaço extra
        if (itensString.length() > 0) {
            itensString.setLength(itensString.length() - 2); // Remover vírgula final
        }

        // Exibir as informações do pedido
        return "Pedido [id: " + id + ", Data: " + data + ", Ciente Nif:" + cliente.getNif() + ", Itens: [" + itensString + "], Valor Total: " + valorTotal + ", Metodo de pagamento: " + metodoDePagar + "]";
    }


    public String toCSV() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        String dataFormatada = dateFormat.format(data);

        // Criar a string representando os itens
        StringBuilder itensString = new StringBuilder();
        for (Joia joia : itens) {
            itensString.append(joia.getId()).append(";"); // Sem aspas
        }

        // Remover o último ponto e vírgula, se existir
        if (itensString.length() > 0) {
            itensString.setLength(itensString.length() - 1);
        }

        // Retornar a string no formato CSV
        return id + "," +
                dataFormatada + "," +
                cliente.getNif() + "," +
                itensString + "," + // Sem aspas ao redor dos itens
                estado.name() + "," +
                valorTotal + "," +
                metodoDePagar;
    }
}


