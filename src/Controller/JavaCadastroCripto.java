package Controller;

import Model.Moeda;
import View.CriptoCadastro;
import dao.MoedaDAO;
import dao.conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author paulo
 */

public class JavaCadastroCripto {
    private CriptoCadastro view;

    public JavaCadastroCripto(CriptoCadastro view) {
        this.view = view;
    }

    public void SalvarMoeda() {
        String sigla = view.getCripto_Sigla().getText();
        String nome = view.getCripto_Nome().getText();
        double valor = Double.parseDouble(view.getCripto_Valor().getText());  // converter para double...

        Moeda novaMoeda = new Moeda(sigla, nome, valor);

        try (Connection conexao = new conexao().getConnection()) {
            MoedaDAO moedaDAO = new MoedaDAO(conexao);
            moedaDAO.insertMoeda(novaMoeda); 
            JOptionPane.showMessageDialog(null, "Moeda salva com sucesso.");
            view.dispose();
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Moeda Já cadastrada")) {
                JOptionPane.showMessageDialog(null, "Moeda já cadastrada. Tente outra sigla.");
            } else {
                Logger.getLogger(JavaCadastroCripto.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao salvar moeda: " + ex.getMessage());
            }
        }
    }
}
