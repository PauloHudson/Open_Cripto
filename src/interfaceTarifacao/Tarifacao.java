/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceTarifacao;

/**
 *
 * @author AnaCa
 */
public interface Tarifacao {
    double calcTxCompra(double valor);
    double calcTxVenda(double valor);
    double taxaCompra(double valor);
    double taxaVenda(double valor);
    
    
}
