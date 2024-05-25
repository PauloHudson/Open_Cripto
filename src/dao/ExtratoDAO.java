package dao;

import Model.Extrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ExtratoDAO {
    private Connection conexao;

    public ExtratoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void insert(Extrato extrato) {
        String sql = "INSERT INTO extrato (data, horario, condicao, tipo_moeda, cotacao, taxa, valor_final, usuario, valor_acionado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(extrato.getData().getTime()));
            stmt.setString(2, extrato.getHorario());
            stmt.setString(3, extrato.getCondicao());
            stmt.setString(4, extrato.getTipoMoeda());
            stmt.setDouble(5, extrato.getCotacao());
            stmt.setDouble(6, extrato.getTaxa());
            stmt.setDouble(7, extrato.getValorFinal());
            stmt.setString(8, extrato.getUsuario());
            stmt.setDouble(9, extrato.getValor_acionado());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Extrato> buscarPorUsuario(String usuario) throws SQLException {
        List<Extrato> extratos = new ArrayList<>();
        String sql = "SELECT * FROM extrato WHERE usuario = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, usuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Extrato extrato = new Extrato(
                        resultSet.getDate("data"),
                        resultSet.getString("horario"),
                        resultSet.getString("condicao"),
                        resultSet.getString("tipo_moeda"),
                        resultSet.getDouble("cotacao"),
                        resultSet.getDouble("taxa"),
                        resultSet.getDouble("valor_final"),
                        resultSet.getString("usuario"),
                        resultSet.getDouble("valor_acionado")
                    );
                    extratos.add(extrato);
                }
            }
        }
        return extratos;
    }
}
