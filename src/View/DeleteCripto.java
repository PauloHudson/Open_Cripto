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
 * @author brunapaz
 */
public class DeleteCripto extends javax.swing.JFrame {

    /**
     * Creates new form DeleteCripto
     */
    public DeleteCripto() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldCriptoCampo = new javax.swing.JTextField();
        CriptoDeletar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome Da Cripto (Sigla):");

        jTextFieldCriptoCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCriptoCampoActionPerformed(evt);
            }
        });

        CriptoDeletar.setText("Deletar");
        CriptoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CriptoDeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCriptoCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(CriptoDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCriptoCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(CriptoDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

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
    private javax.swing.JTextField jTextFieldCriptoCampo;
    // End of variables declaration//GEN-END:variables
}
