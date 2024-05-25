package dao;

import Model.Moeda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author paulo
 */

public class MoedaDAO {
    private final Connection connection;

    public MoedaDAO(Connection connection) {
        this.connection = connection;
    }

    /** A função insert tem como foco inserir as informações das criptos no nosso  banco de dados e para isso, verficamos primeiramente a existência 
     do cadastro da criptomoeda para depois usar o comando intert do SQL para fazer a inserção */
public void insertMoeda(Moeda moeda) throws SQLException {
    if (existePorMoeda(moeda.getSigla())) {
        throw new SQLException("Moeda já cadastrada");
    }

    String sql = "INSERT INTO moeda(sigla, nome, valor) VALUES (?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, moeda.getSigla());
        statement.setString(2, moeda.getNome());
        statement.setDouble(3, moeda.getValor());
        statement.execute();
    }

    //cria a coluna na usuario quando for criada a cripto nova
    criarColunaUsuario(moeda.getSigla());
}

/** A função "existePorMoeda" faz a procura do da cripto escolhida na tabela do banco de dados.
 Para isso foi necessário o comando "SELECT" do SQL que selciona todas as colunas da tabela "moeda", especifíca que os dados serão selecionados da coluna "sigla"  
  e faz um filtro que busca por um valor específico*/
public boolean existePorMoeda(String sigla) throws SQLException {
        String sql = "SELECT * FROM moeda WHERE sigla = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sigla);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

/**Para excluir uma criptomoeda, foi necessário usar o comando DELETE do SQL.
 * Mais uma vez, é feita seleção dos dados da tabela "moeda", buscando um valor específico na coluna "sigla" para efetuar a exclusão
 */
public boolean deleteBySigla(String sigla) throws SQLException {
    excluirColunaUsuario(sigla); // exluci a coluna de usuario
    String sql = "DELETE FROM moeda WHERE sigla = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, sigla);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Erro ao excluir a moeda", e);
    }
}

/** Essa função realiza a adição de uma coluna na tabela "usuario" a partir da criação de uma criptomoeda.
 *Isso é possível com o coamando ADD COLUMN e na nossa função o nome da coluna recebe o prefixo da sigla criada para a criptomoeda
 */
public void criarColunaUsuario(String siglaMoeda) throws SQLException {
    String sql = "ALTER TABLE usuario ADD COLUMN saldo_" + siglaMoeda + " DOUBLE PRECISION";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.executeUpdate();
    }
}

/** A função "excluirColunaUsuario" faz o oposto da anterior. Ao chegar a existência de uma coluna de saldo de uma criptomoeda específica
 * ela faz a exclusão dessa coluna. Esse caso ocorre quando há a exclusão de uma criptomoeda
 */
public void excluirColunaUsuario(String siglaMoeda) throws SQLException {
    String sql = "ALTER TABLE usuario DROP COLUMN IF EXISTS saldo_" + siglaMoeda;
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.executeUpdate();
    }
}

  /** Essa função faz a seleção da tabela "moeda" e retorna a lista com todos os objetos do tipo"Moeda" representados, como 
   sua sigla, nome e valor.*/
    public List<Moeda> buscarTodasMoedas() throws SQLException {
        String sql = "SELECT * FROM moeda";
        List<Moeda> moedas = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Moeda moeda = new Moeda(
                    resultSet.getString("sigla"),
                    resultSet.getString("nome"),
                    resultSet.getDouble("valor")
                );
                moedas.add(moeda);
            }
        }
        return moedas;
    }

/** A função a seguir tem o propósito de atualizar as contações das criptomoedas. Para isso
 * um objeto do tipo "Random" gera números aleatórios e para cada objeto do tipo "Moeda", é obtido seu valor atual
 * para o cálculo da variação que vai de +5% a -5%. 
 * Depois disso, o valor final da moeda é atualizado com o comando "UPDATE" do SQL que atribui a atualização na tabela "moeda". 
 */
    public void atualizarCotacoes() throws SQLException {
        List<Moeda> moedas = buscarTodasMoedas();
        Random random = new Random();

        for (Moeda moeda : moedas) {
            double valorAtual = moeda.getValor();
            double variacao = (random.nextDouble() * 10 - 5) / 100; // Variação entre -5% e +5%
            double novoValor = valorAtual * (1 + variacao);

            String sql = "UPDATE moeda SET valor = ? WHERE sigla = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDouble(1, novoValor);
                statement.setString(2, moeda.getSigla());
                statement.executeUpdate();
            }
        }
    }



}