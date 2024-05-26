/**
 *
 * @author paulo
 */
package Model;

import interfaceTarifacao.Tarifacao;

public  class ETH  implements Tarifacao{
    private static final double taxaComp = 0.01;
    private static final double taxaVnd = 0.02;
   

   
    public double calcTxCompra(double valor) {
        return valor * taxaComp;
    }


    public double calcTxVenda(double valor) {
        return valor * taxaVnd;
    }

   
    public double taxaCompra(double valor) {
        return taxaComp;
    }

    
    public double taxaVenda(double valor) {
        return taxaVnd;
    }
}