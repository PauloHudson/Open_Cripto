package Controller;
/**
 * This class handles user login operations.
 * It validates user credentials and manages user sessions.
 * 
 * @version 1.0
 * @since 2024-05-25
 */

import Model.Usuario;
import View.Admin;
import View.Login;
import View.MENU;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author paulo
 */

public class JavaLoginController {
    private final Login view;

    public JavaLoginController(Login view) {
        this.view = view;
    }

/** A função "autenticar()", começa obtendo o cpf e senha do usuário, através da interface gráfica e faz a verificação 
 se é o usuário administrador. Após a verificação, um objeto do tipo "Usuario" é criado com o cpf e senha que forma inseridos
 Usamos um objeto do tipo "UsuarioDAO" para fazer a conexão com o banco de dados e com o chamado da função "usuariodao.existePorUsuarioESenha(usuarioAutenticar)
 * ocorre a verificação se já existe um usuário com as informações dadas.
 * Caso o usuário exista, a interface gráfica da área correspondente se abre. */
    public void autenticar() throws SQLException {
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        System.out.println(usuario);
        System.out.println(senha);

        if ("admin".equals(usuario) && "admin".equals(senha)) {
            Admin adminView = new Admin(); 
            adminView.setVisible(true);
            view.dispose(); 
            return;
        }

        Usuario usuarioAutenticar = new Usuario(usuario, senha);

        java.sql.Connection conexao = new conexao().getConnection();
        UsuarioDAO usuariodao = new UsuarioDAO(conexao);

        boolean existe = usuariodao.existePorUsuarioESenha(usuarioAutenticar);
        System.out.println(existe);

        if (existe) {
            Usuario usuarioLogado = usuariodao.buscarPorUsuario(usuario);
            MENU TeladeMENU = new MENU(usuarioLogado);
            TeladeMENU.setVisible(true);
            view.dispose(); 
        } else {
            JOptionPane.showMessageDialog(view, "Usuario ou Senha Inválidos");
        }
    }
}
