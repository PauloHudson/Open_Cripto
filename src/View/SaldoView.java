
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
    }

    private void exibirInformacoesUsuario() {
        jLabel1.setText("Nome: " + usuario.getNome());
        jLabel2.setText("CPF: " + usuario.getUsuario());
        jLabel4.setText("SALDO BRL: " + usuario.getSaldo());
    }

    private void exibirSaldos() {
        try {
            Map<String, Double> saldos = usuarioDAO.buscarTodosSaldos(usuario.getUsuario());
            StringBuilder saldoInfo = new StringBuilder();

            for (Map.Entry<String, Double> entry : saldos.entrySet()) {
                saldoInfo.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }

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
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(128, Short.MAX_VALUE))
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
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
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
