
package dao;


import Model.Moeda;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



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

    
public void insert(Usuario usuario) throws SQLException {
    // Verificar se o usuário já existe
    if (existePorUsuario(usuario.getUsuario())) {
        throw new SQLException("Usuário já cadastrado.");
    }

    // Comando de inserção no SQL
    String sql = "INSERT INTO usuario(usuario, senha, nome) VALUES (?, ?, ?)";
    
    
    
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getSenha());
        statement.setString(3, usuario.getNome());
        statement.execute();
    }
}



public boolean existePorUsuario(String usuario) throws SQLException {
    String sql = "SELECT * FROM usuario WHERE usuario = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, usuario);
        try (ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next();
        }
    }
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


public Usuario buscarPorUsuario(String usuario) throws SQLException {
    String sql = "SELECT * FROM usuario WHERE usuario = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, usuario);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return new Usuario(
                    resultSet.getString("usuario"),
                    resultSet.getString("senha"),
                    resultSet.getString("nome")
                    
                );
            }
        }
    }
    return null;
}

public boolean deleteByUsuario(String usuario) throws SQLException {
    String sql = "DELETE FROM usuario WHERE usuario = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, usuario);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Erro ao excluir o usuário", e);
    }
}

  public void atualizarSaldo(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET saldo = ? WHERE usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, usuario.getSaldo());
            statement.setString(2, usuario.getUsuario());
            statement.executeUpdate();
        }
    }


//ele está a dar uma busac no banco pelos usuarios, apartir dele conseguimos fazer a busca na funcao
public Usuario buscarPorUsuarioNOVO(String usuario) throws SQLException {
    String sql = "SELECT * FROM usuario WHERE usuario = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, usuario);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
         

                double saldo = resultSet.getDouble("saldo");
               

                return new Usuario(
                    resultSet.getString("usuario"),
                    resultSet.getString("senha"),
                    resultSet.getString("nome"),
                    saldo 
                );
            } else {
                System.out.println("Usuário não encontrado.");
            }
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar usuário: " + e.getMessage());
        throw e;
    }
    return null;
}



public Map<String, Double> buscarTodosSaldos(String usuario) throws SQLException {
    String sql = "SELECT * FROM usuario WHERE usuario = ?";
    Map<String, Double> saldos = new HashMap<>();

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, usuario);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);

                    if (columnName.startsWith("saldo_")) {
                        String moeda = columnName.substring(6); // Remove o prefixo "saldo_"
                        double saldo = resultSet.getDouble(columnName);
                        saldos.put(moeda, saldo);
                    
                    }
                }
            }
        }
    }

    return saldos;
}



}

