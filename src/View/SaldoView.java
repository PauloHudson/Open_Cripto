
package View;

import Model.Usuario;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

/**
 *
 * @author paulo
 */


public class SaldoView extends javax.swing.JFrame {
    private final Usuario usuario;
    private final UsuarioDAO usuarioDAO;

    public SaldoView(Usuario usuario) throws SQLException {
        initComponents();
        this.usuario = usuario;


        conexao conexao = new conexao();
        Connection connection = conexao.getConnection();

        this.usuarioDAO = new UsuarioDAO(connection);
        
        exibirInformacoesUsuario();
        exibirSaldos();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
    }

   private void exibirInformacoesUsuario() {
    DecimalFormat df = new DecimalFormat("#.##");
    try {
        Usuario usuarioAtualizado = usuarioDAO.buscarPorUsuarioNOVO(usuario.getUsuario());
        if (usuarioAtualizado != null) {
  
            jLabel1.setText("Nome: " + usuarioAtualizado.getNome());
            jLabel2.setText("CPF: " + usuarioAtualizado.getUsuario());
            jLabel4.setText("SALDO BRL: " + df.format(usuarioAtualizado.getSaldo()));
        } else {
            System.out.println("Usuário não encontrado.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar usuário: " + e.getMessage());
        e.printStackTrace();
    }
    }

  private void exibirSaldos() {
    DecimalFormat df = new DecimalFormat("#.##");
    try {
        Map<String, Double> saldos = usuarioDAO.buscarTodosSaldos(usuario.getUsuario());
        StringBuilder saldoInfo = new StringBuilder("<html>");

        for (Map.Entry<String, Double> entry : saldos.entrySet()) {
            saldoInfo.append(entry.getKey()).append(": ").append(df.format(entry.getValue())).append("<br>");
        }

        saldoInfo.append("</html>");
        jLabel3.setText(saldoInfo.toString());
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao exibir saldos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 250, 250));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 230, 20));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(250, 250, 250));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 240, 20));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(250, 250, 250));
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 230, 200));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(250, 250, 250));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 240, 20));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(250, 250, 250));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

  


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
