/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AnaCa
 */
public class CompraModel {
    private String siglaCriptomoeda;
    private double valorCompra;
    private String cpfUsuario;

    public String getSiglaCriptomoeda() {
        return siglaCriptomoeda;
    }

    public void setSiglaCriptomoeda(String siglaCriptomoeda) {
        this.siglaCriptomoeda = siglaCriptomoeda;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public CompraModel(String siglaCriptomoeda, double valorCompra, String cpfUsuario) {
        this.siglaCriptomoeda = siglaCriptomoeda;
        this.valorCompra = valorCompra;
        this.cpfUsuario = cpfUsuario;
    }

    public CompraModel() {
    }
    
    
 
    
    
}
