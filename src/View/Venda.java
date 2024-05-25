
package View;
import dao.MoedaDAO;
import dao.UsuarioDAO;
import dao.conexao;
import Model.Moeda;
import Model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DecimalFormat;


/**
 *
 * @author AnaCa
 */
public class Venda extends javax.swing.JFrame {

    private Usuario usuarioLogado;

    public Venda(Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        atualizarSaldos();
    }




private void atualizarSaldos() {
    Connection connection = null;
    try {
        connection = new conexao().getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        MoedaDAO moedaDAO = new MoedaDAO(connection);


        DecimalFormat df = new DecimalFormat("#.##");

        // Atualiza saldo BRL
        usuarioLogado = usuarioDAO.buscarPorUsuarioNOVO(usuarioLogado.getUsuario());
        jLabel6.setText("SALDO BRL R$: " + df.format(usuarioLogado.getSaldo()));

        // Atualiza a cotação
        List<Moeda> moedas = moedaDAO.buscarTodasMoedas();
        StringBuilder cotacoes = new StringBuilder("<html>");
        for (Moeda moeda : moedas) {
            cotacoes.append(moeda.getSigla()).append(": ").append(df.format(moeda.getValor())).append("<br>");
        }
        cotacoes.append("</html>");
        jLabel2.setText(cotacoes.toString());

        // Para atualizar o saldo das criptos
        Map<String, Double> saldos = usuarioDAO.buscarTodosSaldos(usuarioLogado.getUsuario());
        StringBuilder saldoInfo = new StringBuilder("<html>");
        for (Map.Entry<String, Double> entry : saldos.entrySet()) {
            saldoInfo.append(entry.getKey()).append(": ").append(df.format(entry.getValue())).append("<br>");
        }
        saldoInfo.append("</html>");
        SALDOCRIPTO.setText(saldoInfo.toString());

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao atualizar saldos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } finally {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


    private void RealizarVenda() throws SQLException {
    // Seleciona a sigla da cripto
    String siglaCripto = selectCripto.getText();
    // Seleciona o valor da cripto a ser vendida
    double valorVenda = Double.parseDouble(valorTxt.getText());
    // Seleciona o campo senha
    String senha = new String(SenhaField.getPassword());

    try (Connection connection = new conexao().getConnection()) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        MoedaDAO moedaDAO = new MoedaDAO(connection);

        // Verifica a senha do usuário
        if (!usuarioDAO.existePorUsuarioESenha(new Usuario(usuarioLogado.getUsuario(), senha, null))) {
            JOptionPane.showMessageDialog(null, "Senha incorreta.");
            return;
        }

        // Busca a moeda desejada
        List<Moeda> moedas = moedaDAO.buscarTodasMoedas();
        Moeda moedaDesejada = null;
        for (Moeda moeda : moedas) {
            // Faz um get para obter a sigla e ver se ela é igual a do inputText, ignorando se é maiúscula
            if (moeda.getSigla().equalsIgnoreCase(siglaCripto)) {
                moedaDesejada = moeda;
                break;
            }
        }

        if (moedaDesejada == null) {
            JOptionPane.showMessageDialog(null, "Criptomoeda não encontrada.");
            return;
        }

        // Busca os saldos das criptomoedas do usuário
        Map<String, Double> saldos = usuarioDAO.buscarTodosSaldos(usuarioLogado.getUsuario());
        double saldoCripto = saldos.getOrDefault(siglaCripto, 0.0);

        // Verifica se o usuário tem saldo suficiente da criptomoeda
        if (valorVenda > saldoCripto) {
            JOptionPane.showMessageDialog(null, "Saldo de criptomoeda insuficiente.");
            return;
        }

        // Calcula o valor total da venda em BRL
        double valorTotalVenda = valorVenda * moedaDesejada.getValor();

        // Atualiza o saldo em BRL
        usuarioLogado.setSaldo(usuarioLogado.getSaldo() + valorTotalVenda);
        usuarioDAO.atualizarSaldo(usuarioLogado);

        // AAtualiza o saldo da criptomoeda
        saldoCripto -= valorVenda;

        String sql = "UPDATE usuario SET saldo_" + siglaCripto + " = ? WHERE usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, saldoCripto);
            statement.setString(2, usuarioLogado.getUsuario());
            statement.executeUpdate();
        }

        JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
        atualizarSaldos();

    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Erro ao realizar a venda", e);
    }
}


    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        selectCripto = new javax.swing.JTextField();
        valorTxt = new javax.swing.JTextField();
        btFinalizarCompra = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        SenhaField = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        SALDOCRIPTO = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Venda");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 152, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(250, 250, 250));
        jLabel4.setText("SALDO CRIPTO");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, -1, 27));
        getContentPane().add(selectCripto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 170, 32));
        getContentPane().add(valorTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 170, 30));

        btFinalizarCompra.setText("Venda");
        btFinalizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarCompraActionPerformed(evt);
            }
        });
        getContentPane().add(btFinalizarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 270, 46));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(250, 250, 250));
        jLabel5.setText("Sigla Cripto");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel2.setBackground(new java.awt.Color(250, 250, 250));
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 270, 170));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(250, 250, 250));
        jLabel3.setText("Confirme Sua Senha:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, 50));
        getContentPane().add(SenhaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 120, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(250, 250, 250));
        jLabel6.setText("SALDO BRL R$:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 20));

        SALDOCRIPTO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(SALDOCRIPTO, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 160, 320));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(250, 250, 250));
        jLabel9.setText("Valor Vender");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        jLabel8.setForeground(new java.awt.Color(250, 250, 250));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btFinalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarCompraActionPerformed
        try {
            RealizarVenda();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar a Venda: " + ex.getMessage());
        }
    }//GEN-LAST:event_btFinalizarCompraActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SALDOCRIPTO;
    private javax.swing.JPasswordField SenhaField;
    private javax.swing.JButton btFinalizarCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField selectCripto;
    private javax.swing.JTextField valorTxt;
    // End of variables declaration//GEN-END:variables
}
