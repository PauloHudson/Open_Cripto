
package Controller;

import dao.MoedaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AtualizarCotacao {
    
    public static void atualizarCotacao(MoedaDAO moedaDAO) {
        try {
            moedaDAO.atualizarCotacoes();
            JOptionPane.showMessageDialog(null, "Cotações atualizadas com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cotações: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
