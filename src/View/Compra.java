
package View;
import dao.MoedaDAO;
import dao.UsuarioDAO;
import dao.conexao;
import Model.Moeda;
import Model.Usuario; 
import Model.Taxa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;


/**
 *
 * @author AnaCa
 */
public class Compra extends javax.swing.JFrame {

    private Usuario usuarioLogado;

    public Compra(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        atualizarSaldos();
    }

    private void atualizarSaldos() {
        DecimalFormat df = new DecimalFormat("#.##");
        
        
        
        try (Connection connection = new conexao().getConnection()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            MoedaDAO moedaDAO = new MoedaDAO(connection);

            // Atualiza o saldo BRL (real)
            usuarioLogado = usuarioDAO.buscarPorUsuarioNOVO(usuarioLogado.getUsuario());
            jLabel6.setText("SALDO BRL R$ : " + df.format(usuarioLogado.getSaldo()));

            // Atualiza as cotações das criptomoedas na interface
            List<Moeda> moedas = moedaDAO.buscarTodasMoedas();
            StringBuilder cotacoes = new StringBuilder("<html>");
            for (Moeda moeda : moedas) {
                cotacoes.append(moeda.getSigla()).append(": ").append(df.format(moeda.getValor())).append("<br>");
            }
            cotacoes.append("</html>");
            jLabel2.setText(cotacoes.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void realizarCompra() throws SQLException {
    // Seleciona a sigla da cripto
    String siglaCripto = selectCripto.getText();
    // Seleciona o valor da cripto
    double valorCompra = Double.parseDouble(valorTxt.getText());
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
            // Faz um get para obter a sigla e ver se ela é igual à do inputText; ignorando maiúsculas e minúsculas
            if (moeda.getSigla().equalsIgnoreCase(siglaCripto)) {
                moedaDesejada = moeda;
                break;
            }
        }

        if (moedaDesejada == null) {
            JOptionPane.showMessageDialog(null, "Criptomoeda não encontrada.");
            return;
        }

        // Calcula a taxa de transação
        Taxa taxa = new Taxa();
        double taxaCompra = taxa.calculoTx(siglaCripto, valorCompra);
        double valorFinalCompra = valorCompra + taxaCompra;

        // Verifica se o saldo é suficiente para cobrir o valor da compra e a taxa
        if (valorFinalCompra > usuarioLogado.getSaldo()) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
            return;
        }

        // Atualiza o saldo em BRL
        usuarioLogado.setSaldo(usuarioLogado.getSaldo() - valorFinalCompra);
        usuarioDAO.atualizarSaldo(usuarioLogado);

        // Atualiza o saldo da criptomoeda
        Map<String, Double> saldos = usuarioDAO.buscarTodosSaldos(usuarioLogado.getUsuario());
        double saldoCripto = saldos.getOrDefault(siglaCripto, 0.0);
        
        // Valor que for a ser colocado, vai ser então pelo valor convertido do preço da moeda
        saldoCripto += valorCompra / moedaDesejada.getValor();

        String sql = "UPDATE usuario SET saldo_" + siglaCripto + " = ? WHERE usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, saldoCripto);
            statement.setString(2, usuarioLogado.getUsuario());
            statement.executeUpdate();
        }

        JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!");
        atualizarSaldos();

    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Erro ao realizar a compra", e);
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
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Compra");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 152, -1));

        jLabel4.setFont(new java.awt.Font(".AppleSystemUIFont", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(250, 250, 250));
        jLabel4.setText("Valor Comprar: ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, 27));
        getContentPane().add(selectCripto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 261, 32));
        getContentPane().add(valorTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 261, 30));

        btFinalizarCompra.setText("Finalizar Compra");
        btFinalizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarCompraActionPerformed(evt);
            }
        });
        getContentPane().add(btFinalizarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 240, 46));

        jLabel5.setFont(new java.awt.Font(".AppleSystemUIFont", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(250, 250, 250));
        jLabel5.setText("Sigla Cripto:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 380, 200));

        jLabel3.setFont(new java.awt.Font(".AppleSystemUIFont", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(250, 250, 250));
        jLabel3.setText("Confirme Sua Senha:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, -1, 50));
        getContentPane().add(SenhaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 220, 30));

        jLabel6.setFont(new java.awt.Font(".AppleSystemUIFont", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(250, 250, 250));
        jLabel6.setText("SALDO BRL R$:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btFinalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarCompraActionPerformed
        try {
            realizarCompra();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar a compra: " + ex.getMessage());
        }
    }//GEN-LAST:event_btFinalizarCompraActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField SenhaField;
    private javax.swing.JButton btFinalizarCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField selectCripto;
    private javax.swing.JTextField valorTxt;
    // End of variables declaration//GEN-END:variables

 
}
