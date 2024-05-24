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


public boolean existePorMoeda(String sigla) throws SQLException {
        String sql = "SELECT * FROM moeda WHERE sigla = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sigla);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

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


public void criarColunaUsuario(String siglaMoeda) throws SQLException {
    String sql = "ALTER TABLE usuario ADD COLUMN saldo_" + siglaMoeda + " DOUBLE PRECISION";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.executeUpdate();
    }
}

public void excluirColunaUsuario(String siglaMoeda) throws SQLException {
    String sql = "ALTER TABLE usuario DROP COLUMN IF EXISTS saldo_" + siglaMoeda;
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.executeUpdate();
    }
}

  
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

    // Método para atualizar a cotação de todas as moedas
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