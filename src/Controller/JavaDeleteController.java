package Controller;

import Model.Usuario;
import dao.MoedaDAO;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class JavaDeleteController {

public void excluirUsuarioPorUsuario(String usuario) {
        try (java.sql.Connection conexao = new conexao().getConnection()) {
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            Usuario usuarioEncontrado = usuarioDao.buscarPorUsuario(usuario);
            if (usuarioEncontrado == null) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o usuário?\n" +"Nome: " + usuarioEncontrado.getNome(),
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (usuarioDao.deleteByUsuario(usuario)) {
                    JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao excluir o usuário.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
public void excluirMoedaPorSigla(String nome) {
    try (java.sql.Connection conexao = new conexao().getConnection()) { 
        MoedaDAO moedaDao = new MoedaDAO(conexao);
        if (moedaDao.existePorMoeda(nome)) {
            int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a moeda?\n" + "Nome: " + nome,
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (moedaDao.deleteBySigla(nome)) {
                    JOptionPane.showMessageDialog(null, "Moeda excluída com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao excluir a moeda.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Moeda não encontrada.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + e.getMessage());
    }
}
}
