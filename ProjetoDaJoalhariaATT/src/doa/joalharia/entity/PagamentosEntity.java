package doa.joalharia.entity;

import doa.joalharia.entity.interfac.Entity;

import java.util.Date;

public class PagamentosEntity implements Entity<Long> {

    public enum MetodoPagamento {
        CartaodeCredito, CartaoBancario, TranferenciaBancaria, DinheiroAPagar, Mbway
    }

    private Long id;
    private MetodoPagamento metodoDePagar;
    private Date data;
    private double valorAPagar;


    public PagamentosEntity (Long id, MetodoPagamento metodoDePagar, Date data, double valorAPagar) {
        this.metodoDePagar=metodoDePagar;
        this.data = data;
        this.valorAPagar=valorAPagar;
        this.id=id;
    }
    //getters and setters

    @Override
    public Long getId() {
        return id;
    }


    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public MetodoPagamento getMetodoDePagar() {
        return metodoDePagar;
    }

    public void setMetodoDePagar(MetodoPagamento metodoDePagar) {
        this.metodoDePagar = metodoDePagar;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    @Override
    public String toString() {
        return "Id do pagamento{" + id +
                ", valor=" + valorAPagar +
                ", metodoDePagamento=" + metodoDePagar.name() +
                ", data=" + data +
                '}';

    }


    // Método para salvar os dados no formato CSV
    public String toCSV() {
        return id + "," + valorAPagar + "," + metodoDePagar.name() + "," + data;
    }


}
