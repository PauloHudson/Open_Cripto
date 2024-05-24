
package View;


import Model.Usuario;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author paulo
 */

public class SaldoAdmin extends javax.swing.JFrame {
    private UsuarioDAO usuarioDAO;
 
    public SaldoAdmin() {
        initComponents();
        
        // configuracao da conexão e DAO....
        try {
            conexao conexao = new conexao();
            Connection connection = conexao.getConnection();
            usuarioDAO = new UsuarioDAO(connection);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1CPF = new javax.swing.JLabel();
        jLabel2SALDO = new javax.swing.JLabel();
        InputCPF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buscarSaldo = new javax.swing.JButton();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1CPF.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1CPF.setForeground(new java.awt.Color(250, 250, 250));
        jLabel1CPF.setText("USUARIO:");
        getContentPane().add(jLabel1CPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel2SALDO.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2SALDO.setForeground(new java.awt.Color(250, 250, 250));
        jLabel2SALDO.setText("SALDO:");
        getContentPane().add(jLabel2SALDO, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));
        getContentPane().add(InputCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 230, 50));

        jLabel1.setForeground(new java.awt.Color(250, 250, 250));
        jLabel1.setText("Digite o CPF do Usuario:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(250, 250, 250));
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 230, 153));

        buscarSaldo.setText("BUSCAR");
        buscarSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarSaldoActionPerformed(evt);
            }
        });
        getContentPane().add(buscarSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(Fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
   private void exibirInformacoesUsuario() {
    String usuario = InputCPF.getText();
    Usuario usuarioAtualizado;
    try {
        usuarioAtualizado = usuarioDAO.buscarPorUsuarioNOVO(usuario);
        if (usuarioAtualizado != null) {
            jLabel1CPF.setText("USUÁRIO: " + usuarioAtualizado.getUsuario());
            jLabel2SALDO.setText("SALDO BRL: " + usuarioAtualizado.getSaldo());
        } else {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao buscar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void buscarSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarSaldoActionPerformed
        String usuario = InputCPF.getText();
        try {
            Map<String, Double> saldos = usuarioDAO.buscarTodosSaldos(usuario);
            StringBuilder saldoInfo = new StringBuilder();

            for (Map.Entry<String, Double> entry : saldos.entrySet()) {
                saldoInfo.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }

            jLabel2.setText("<html>" + saldoInfo.toString().replace("\n", "<br>") + "</html>");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar saldos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        exibirInformacoesUsuario();
    
    }//GEN-LAST:event_buscarSaldoActionPerformed

   
    public static void main(String args[]) {
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaldoAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fundo;
    private javax.swing.JTextField InputCPF;
    private javax.swing.JButton buscarSaldo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1CPF;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel2SALDO;
    // End of variables declaration//GEN-END:variables
}
