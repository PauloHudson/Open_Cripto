
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

    /** A função insert tem como foco inserir as informações do usuário no nosso  banco de dados e para isso, verficamos primeiramente a existência 
     do cadastro do usuário para depois usar o comando intert do SQL para fazer a inserção */
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


/** A função "existePorUsuario" faz a procura do usuário na tabela do banco de dados.
 Para isso foi necessário o comando "SELECT" do SQL que selciona todas as colunas da tabela "usuario", especifíca que os dados serão selecionados da coluna "usuario"  
 (que contém o CPF) e faz um filtro que busca por um valor específico*/
public boolean existePorUsuario(String usuario) throws SQLException {
    String sql = "SELECT * FROM usuario WHERE usuario = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, usuario);
        try (ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next();
        }
    }
}
    
 
    
    
    
    /** Essa função faz o mesmo processo da anterior, mas ao invés de selecionar somente os dados da coluna "usuario",
     ela também seleciona os dados da coluna "senha", permitindo assim fazer o filtro que busque o usuários por seus CPFs e senhas.*/
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

/** A função "buscaPorUsuario" tem o mesmo comando da função "existePorUsuario" para intergir com o banco de dados mas, 
 * ela serve para a crição de um novo usuário ao retonar o objeto "Usuario" com valores "usuario"(cpf), "senha" e "nome"
 */
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
/**Para excluir um usuário, foi necessário usar o comando DELETE do SQL.
 * Mais uma vez, é feita seleção dos dados da tabela "usuario", buscando um valor específico na coluna "usuario" para efetuar a exclusão
 */
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
/** Para a atualização do saldo, criamos a função "atualizarSaldo que com oo comando "UPTADE", na tabela "usuario", atribui um valor específico na coluna "saldo
 onde "usuario" também possui um valor específico
 */
  public void atualizarSaldo(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET saldo = ? WHERE usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, usuario.getSaldo());
            statement.setString(2, usuario.getUsuario());
            statement.executeUpdate();
        }
    }


/** Essa função, "buscarPorUsuarioNOVO", tem a mesma funcionalidade da função "buscarPorUsuario", mas
 * ao criar o objeto "Usuario", foi acrescentado o saldo aos valores que são criados
 */
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

/** Essa função faz a seleção da tabela "usuario" e procura um valor específico na coluna "usuario"
 e retorna o mapeamento do nome de cada moeda para seu saldo correspondente*/

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
/** Todas as funções recebem o parâmetro String usuario, pois representa cpf do usuario específico para realizar as operções dele*/


}

