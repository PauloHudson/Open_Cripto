
package dao;


import Model.Usuario;
import View.Cadastro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author paulo
 */




//Tudo que for relativo ao usuário na conexão com o banco de dados, vai conectar aqui.
public class UsuarioDAO {
    private final java.sql.Connection connection;
    //apenas crio o usuário dao se tiver a conexão.
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    //Vai inserir os dados, tem de ser to tipo usuario(MODEL):
    public void insert(Usuario usuario) throws SQLException{

        //comando de inserção do sql.
        String sql = "insert into usuario(usuario,senha) values('"+usuario.getUsuario()+"','"+usuario.getSenha()+"');";
        PreparedStatement statment = connection.prepareStatement(sql);
        statment.execute();  
     
             
    }
    
    
    
    
    
    
    //para fazer o select no banco e enviar se vai ser true or false.
public boolean existePorUsuarioESenha(Usuario usuario) throws SQLException {

    String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
try (Connection conn = new conexao().getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
    
    stmt.setString(1, usuario.getUsuario());
    stmt.setString(2, usuario.getSenha());

    try (ResultSet resultSet = stmt.executeQuery()) {
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
    throw new RuntimeException("Erro ao acessar o banco de dados", e);
}
    };   

   
    
}
