
package Controller;

import dao.MoedaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AtualizarCotacao {
    /** A função "atualizarCotacao" recebe como parâmetro o objeto do tipo "MoedaDAO" e  reliza as atualizações das cotações através do chamado
    "moedaDAO.atualizarCotacoes()"
    Ela também exibe mensagens que retornam se houve sucesso na operção ou se houve algum erro*/
    public static void atualizarCotacao(MoedaDAO moedaDAO) {
        try {
            moedaDAO.atualizarCotacoes();
            JOptionPane.showMessageDialog(null, "Cotações atualizadas com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cotações: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
