package Controller;

import Model.Usuario;
import View.ComprarCripto;
import javax.swing.JOptionPane;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.Connection;
import java.sql.SQLException;

public class DepositoController {
    private final ComprarCripto view;
    private final Usuario usuario;

    public DepositoController(ComprarCripto view, Usuario usuario) {
        this.view = view;
        this.usuario = usuario;
    }

    public void realizarDeposito() {
         if (usuario.getSaldo() == null) {
            usuario.setSaldo(0.0);
        }
        Double valorDeposito = Double.parseDouble(view.getValorDepositar().getText());

        // Realizar o depósito
        //usuario.depositar(valorDeposito);
        
        String valorDepositarTexto = view.getValorDepositar().getText();
        String senha = new String(view.getSenhaField().getPassword());

        try {
            double valorDepositar = Double.parseDouble(valorDepositarTexto);

            if (!usuario.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(view, "Senha incorreta!");
                return;
            }

            usuario.setSaldo(usuario.getSaldo() + valorDepositar);

            Connection conexao = new conexao().getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            usuarioDAO.atualizarSaldo(usuario);

            JOptionPane.showMessageDialog(view, "Depósito realizado com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Valor de depósito inválido!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erro ao realizar o depósito!");
        }
    }
}
