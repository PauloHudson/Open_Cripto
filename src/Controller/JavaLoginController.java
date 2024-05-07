
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
    //Connstrtuctor....
    public JavaLoginController(Login view) {
        this.view = view;
    }

    public void autenticar() throws SQLException {
        //Buscar um usuário da View.
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        System.out.println(usuario);
        System.out.println(senha);
        //verificar se existeo admin caso sim ele abre.
        if ("admin".equals(usuario) && "admin".equals(senha)) {
        Admin adminView = new Admin(); 
        adminView.setVisible(true);
        view.dispose(); 
        return;
    }
        Usuario usuarioAutenticar = new Usuario(usuario,senha);
        
        
        //Verificar se Existe no banco dedados
        //....
        //pegar a conexão:
        java.sql.Connection conexao = new conexao().getConnection();
        //Salvar o usuário
        UsuarioDAO usuariodao = new UsuarioDAO(conexao);
        
        boolean existe = usuariodao.existePorUsuarioESenha(usuarioAutenticar);
        System.out.println(existe);
        if(existe){
            MENU TeladeMENU = new MENU();
            TeladeMENU.setVisible(true);
            view.dispose(); 
        }else{
            JOptionPane.showMessageDialog(view, "Usuario ou Senha Inválidos");
        }
        
    }
    
    
}
