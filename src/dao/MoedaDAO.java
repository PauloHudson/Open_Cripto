package dao;

import Model.Moeda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoedaDAO {
    private final Connection connection;

    public MoedaDAO(Connection connection) {
        this.connection = connection;
    }

public void insertMoeda(Moeda moeda) throws SQLException {
        if (existePorMoeda(moeda.getSigla())) {
            throw new SQLException("Moeda Já cadastrada");
        }

        String sql = "INSERT INTO moeda(sigla, nome, valor) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, moeda.getSigla());
            statement.setString(2, moeda.getNome());
            statement.setDouble(3, moeda.getValor());
            statement.execute();
        }
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
public boolean deleteBySigla(String nome) throws SQLException {
    String sql = "DELETE FROM moeda WHERE nome = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, nome);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Erro ao excluir a moeda", e);
    }
}

}
