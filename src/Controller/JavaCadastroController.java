/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
    
    
    
    public void SalvaUsuario(){

        
        //pegar a string da tela, pegando otexto dentro do campo
        //Aqui estamos acessando a classse view.Cadastro, e pegando o o texto que setá no campo J...
        String usuario = view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordSenha().getText();
        
        Usuario usuarioTeste = new Usuario(usuario, senha);
          
        try {
            //pegar a conexão:
            java.sql.Connection conexao = new conexao().getConnection();
            //Salvar o usuário
            UsuarioDAO usuariodao = new UsuarioDAO(conexao);
            //Adiciono o usuário teste.
            usuariodao.insert(usuarioTeste);
            
            JOptionPane.showMessageDialog(null, "Usuario Salvo com Sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Usuario Não Salvo");
        }
     }
    
    
}
