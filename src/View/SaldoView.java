
package View;

import Model.Usuario;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JOptionPane;


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
    try {
        Usuario usuarioAtualizado = usuarioDAO.buscarPorUsuarioNOVO(usuario.getUsuario());
        if (usuarioAtualizado != null) {
  
            jLabel1.setText("Nome: " + usuarioAtualizado.getNome());
            jLabel2.setText("CPF: " + usuarioAtualizado.getUsuario());
            jLabel4.setText("SALDO BRL: " + usuarioAtualizado.getSaldo());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar usuário: " + e.getMessage());
        e.printStackTrace();
    }
}

  private void exibirSaldos() {
    try {
        Map<String, Double> saldos = usuarioDAO.buscarTodosSaldos(usuario.getUsuario());
        StringBuilder saldoInfo = new StringBuilder("<html>");

        for (Map.Entry<String, Double> entry : saldos.entrySet()) {
            saldoInfo.append(entry.getKey()).append(": ").append(entry.getValue()).append("<br>");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("NOME:");

        jLabel2.setText("CPF:");

        jLabel4.setText("SALDO BRL:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
