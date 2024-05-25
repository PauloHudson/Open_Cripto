/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AnaCa
 */
public class Taxa {
 
    public double calculoTx(String siglaCripto, double valorCompra) {
        return calcularTaxaCmp(siglaCripto, valorCompra);
    }

    private double calcularTaxaCmp(String siglaCripto, double valorCompra) {
        double taxaCompra;
        switch (siglaCripto.toUpperCase()) {
            case "BTC":
                taxaCompra = 0.02;
                break;
            case "RPL":
                taxaCompra = 0.01;
                break;
            case "ETH":
                taxaCompra = 0.01;
                break;
            default:
                taxaCompra = 0.00;
                break;
        }
        return valorCompra * taxaCompra;
    }
}
