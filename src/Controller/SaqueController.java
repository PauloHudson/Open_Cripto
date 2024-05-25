package Controller;

import Model.Usuario;
import View.SaqueView;
import javax.swing.JOptionPane;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author paulo
 */

import javax.swing.JOptionPane;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.Connection;
import java.sql.SQLException;

public class SaqueController extends DepositoController {
    private final SaqueView view;

    public SaqueController(SaqueView view, Usuario usuario) {
        super(null, usuario); 
        this.view = view;
    }

    @Override
    public void realizarDeposito() {
        Double valorSaque = Double.parseDouble(view.getValorSacar().getText());
        String senha = new String(view.getSenhaField().getPassword());

        try {
            if (!usuario.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(view, "Senha incorreta!");
                return;
            }


            Connection conexao = new conexao().getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            Usuario usuarioAtualizado = usuarioDAO.buscarPorUsuarioNOVO(usuario.getUsuario());

            if (usuarioAtualizado == null) {
                JOptionPane.showMessageDialog(view, "Usuário não encontrado!");
                return;
            }

            double saldoAtual = usuarioAtualizado.getSaldo();

            if (valorSaque > saldoAtual) {
                JOptionPane.showMessageDialog(view, "Saldo insuficiente!");
                return;
            }

            usuario.setSaldo(saldoAtual - valorSaque);
            usuarioDAO.atualizarSaldo(usuario);

            JOptionPane.showMessageDialog(view, "Saque realizado com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Valor de saque inválido!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erro ao realizar o saque!");
        }
    }
}
