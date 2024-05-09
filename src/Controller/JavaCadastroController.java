/**
 *
 * @author paulo
 */
package Controller;

import Model.Usuario;
import View.Cadastro;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class JavaCadastroController {
    private Cadastro view;
    //Estamos passando a view que o controllador vai iniciar.
    public JavaCadastroController(Cadastro view) {
        this.view = view;
    }
    
    
    
public void SalvaUsuario() {
    String usuario = view.getjTextFieldUsuario().getText();
    String senha = view.getjPasswordSenha().getText();
    String nome = view.getjTextFieldNome().getText();
    
    Usuario usuarioNovo = new Usuario(usuario, senha, nome);
      
    try {
        java.sql.Connection conexao = new conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        
        usuarioDao.insert(usuarioNovo);
        JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso.");
        view.dispose();
        
    } catch (SQLException ex) {
        if (ex.getMessage().equals("Usuário já cadastrado.")) {
            JOptionPane.showMessageDialog(null, "Usuário já cadastrado. Tente outro nome de usuário.");
        } else {
            Logger.getLogger(JavaCadastroController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar usuário: " + ex.getMessage());
        }
    }
}
}

