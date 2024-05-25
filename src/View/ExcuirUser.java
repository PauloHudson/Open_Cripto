
package View;

import Controller.JavaDeleteController;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author paulo
 */
public class ExcuirUser extends javax.swing.JFrame {

    public ExcuirUser() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Excluir = new javax.swing.JButton();
        CPF_Usuario_Excluir = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("EXCLUIR INVESTIDOR");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 290, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel2.setText("CPF");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 140, 30));

        Excluir.setText("EXCLUIR");
        Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(Excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 170, 48));

        CPF_Usuario_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CPF_Usuario_ExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(CPF_Usuario_Excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 320, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CPF_Usuario_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPF_Usuario_ExcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CPF_Usuario_ExcluirActionPerformed

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
           String usuario = CPF_Usuario_Excluir.getText().trim();  // 
            if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o identificador do usuário a ser excluído.");
            return;
            }
            JavaDeleteController deleteController = new JavaDeleteController();
            deleteController.excluirUsuarioPorUsuario(usuario);
            
    }//GEN-LAST:event_ExcluirActionPerformed

   

    public JTextField getCPF_Usuario_Excluir() {
        return CPF_Usuario_Excluir;
    }

    public void setCPF_Usuario_Excluir(JTextField CPF_Usuario_Excluir) {
        this.CPF_Usuario_Excluir = CPF_Usuario_Excluir;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CPF_Usuario_Excluir;
    private javax.swing.JButton Excluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
