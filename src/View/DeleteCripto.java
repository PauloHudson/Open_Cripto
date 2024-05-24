/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.JavaDeleteController;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author paulo
 */
public class DeleteCripto extends javax.swing.JFrame {

    /**
     * Creates new form DeleteCripto
     */
    public DeleteCripto() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldCriptoCampo = new javax.swing.JTextField();
        CriptoDeletar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 3, 24)); // NOI18N
        jLabel1.setText("Nome Da Cripto (Sigla):");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 37, 350, 33));

        jTextFieldCriptoCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCriptoCampoActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldCriptoCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 420, 90));

        CriptoDeletar.setText("Deletar");
        CriptoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CriptoDeletarActionPerformed(evt);
            }
        });
        getContentPane().add(CriptoDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 190, 90));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCriptoCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCriptoCampoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCriptoCampoActionPerformed

    private void CriptoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CriptoDeletarActionPerformed
        String moeda = jTextFieldCriptoCampo.getText().trim();  // 
            if (moeda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o identificador do usuário a ser excluído.");
            return;
            }
            JavaDeleteController deleteController = new JavaDeleteController();
            deleteController.excluirMoedaPorSigla(moeda);
            this.dispose();
    }//GEN-LAST:event_CriptoDeletarActionPerformed

    

    //getters
    
    public JTextField getjTextFieldCriptoCampo() {
        return jTextFieldCriptoCampo;
    }

    public void setjTextFieldCriptoCampo(JTextField jTextFieldCriptoCampo) {
        this.jTextFieldCriptoCampo = jTextFieldCriptoCampo;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CriptoDeletar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldCriptoCampo;
    // End of variables declaration//GEN-END:variables
}
