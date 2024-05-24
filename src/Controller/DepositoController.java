package Controller;

import Model.Usuario;
import View.ComprarCripto;
import javax.swing.JOptionPane;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author paulo
 */
public class DepositoController {
    protected final ComprarCripto view;
    protected final Usuario usuario;

    public DepositoController(ComprarCripto view, Usuario usuario) {
        this.view = view;
        this.usuario = usuario;
    }

    public void realizarDeposito() {
        String valorDepositarTexto = view.getValorDepositar().getText();
        String senha = new String(view.getSenhaField().getPassword());

        try {
            double valorDepositar = Double.parseDouble(valorDepositarTexto);

            if (!usuario.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(view, "Senha incorreta!");
                return;
            }

            Connection conexao = new conexao().getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

            // Carregar o saldo atual do usuário do banco de dados
            Usuario usuarioAtualizado = usuarioDAO.buscarPorUsuarioNOVO(usuario.getUsuario());
            if (usuarioAtualizado == null) {
                JOptionPane.showMessageDialog(view, "Usuário não encontrado!");
                return;
            }

            double saldoAtual = usuarioAtualizado.getSaldo();
            usuario.setSaldo(saldoAtual + valorDepositar); //valorao saldo

            usuarioDAO.atualizarSaldo(usuario);

            JOptionPane.showMessageDialog(view, "Depósito realizado com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Valor de depósito inválido!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erro ao realizar o depósito!");
        }
    }
}
