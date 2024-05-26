package Controller;

import Model.Extrato;
import Model.Usuario;
import View.ComprarCripto;
import dao.ExtratoDAO;
import javax.swing.JOptionPane;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
/**Primeiramente, a função vai obter a senha do usuário e o valor de deposito desejado 
 * a partir da interface  gráfica. Em seguida, vai fazer a verificação das informações do usuário e com o objeto do 
 * tipo "UsuarioDAO" faz a interação com o nosso banco de dados, depois faz a busca do saldo do usuário no banco e
 * atualiza o novo valor.
 * Com isso, um objeto do tipo "Extrato" é criado para representar a transação e ao final há a inserção do objeto na 
 * tabela "extrato".
 */
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
            ///////////------------------------------------------------------
            ///////////------------------------------------------------------
            ///////////------------------------------------------------------
            ///////////------------------------------------------------------
            String condicao = "+";
            String tipoMoeda = "real"; 
            double cotacao = 0.0; 
            double taxa = 0.0; 
            double valorFinal = saldoAtual += valorDepositar;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String horario = sdf.format(new Date());
            double valor_acionado = valorDepositar;

            Extrato extrato = new Extrato(
                new Date(),
                horario,
                condicao,
                tipoMoeda,
                cotacao,
                taxa,
                valorFinal,
                usuario.getUsuario(),
                valor_acionado
            );

            ExtratoDAO extratoDao = new ExtratoDAO(conexao);
            extratoDao.insert(extrato);
            ///////////------------------------------------------------------
            ///////////------------------------------------------------------
            ///////////------------------------------------------------------
            ///////////------------------------------------------------------
            
            
            usuarioDAO.atualizarSaldo(usuario);
            
            JOptionPane.showMessageDialog(view, "Depósito realizado com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Valor de depósito inválido!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erro ao realizar o depósito!");
        }
    }
}
