package Controller;

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
