package Model;

import java.util.Date;

public class Extrato {
    private Date data;
    private String horario;
    private String condicao;
    private String tipoMoeda;
    private double cotacao;
    private double taxa;
    private double valorFinal;
    private String usuario;
    private double valor_acionado;

    public Extrato(Date data, String horario, String condicao, String tipoMoeda, double cotacao, double taxa, double valorFinal, String usuario, double valor_acionado) {
        this.data = data;
        this.horario = horario;
        this.condicao = condicao;
        this.tipoMoeda = tipoMoeda;
        this.cotacao = cotacao;
        this.taxa = taxa;
        this.valorFinal = valorFinal;
        this.usuario = usuario;
        this.valor_acionado = valor_acionado;
    }

    public double getValor_acionado() {
        return valor_acionado;
    }

    public void setValor_acionado(double valor_acionado) {
        this.valor_acionado = valor_acionado;
    }

   
   

    // Getters e setters
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getTipoMoeda() {
        return tipoMoeda;
    }

    public void setTipoMoeda(String tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }

    public double getCotacao() {
        return cotacao;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
