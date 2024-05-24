/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.AtualizarCotacao;
import Controller.AtualizarCotacao;
import Model.Usuario;
import dao.MoedaDAO;
import dao.UsuarioDAO;
import dao.conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author paulo
 */
public class Admin extends javax.swing.JFrame {
    
    private MoedaDAO moedaDAO;

    private Connection connection;
    
    public Admin() throws SQLException{

        initComponents();
        
    conexao conexao = new conexao();
    Connection connection = conexao.getConnection();
        
     this.moedaDAO = new MoedaDAO(connection);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Atualizar = new javax.swing.JButton();
        Excluir_botao = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        CadastrarCripto = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 250, 250));
        jLabel1.setText(" ADMIN");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 250, 90));

        Atualizar.setText("Atualizar Cotação Cripto");
        Atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtualizarActionPerformed(evt);
            }
        });
        getContentPane().add(Atualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 490, 65));

        Excluir_botao.setText("Excluir User");
        Excluir_botao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Excluir_botaoActionPerformed(evt);
            }
        });
        getContentPane().add(Excluir_botao, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 150, 65));

        jButton9.setText("Excluir Cripto");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 150, 65));

        jButton10.setText("Saldo User");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 150, 65));

        jButton11.setText("Extrato User");
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 150, 65));

        CadastrarCripto.setText("Cadastrar Cripto");
        CadastrarCripto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarCriptoActionPerformed(evt);
            }
        });
        getContentPane().add(CadastrarCripto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 150, 65));

        jButton13.setText("Cadastrar User");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 150, 65));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Cadastro TeladeCadastro = new Cadastro();
        TeladeCadastro.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void Excluir_botaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Excluir_botaoActionPerformed
        ExcuirUser excluir = new ExcuirUser();
        excluir.setVisible(true);
    }//GEN-LAST:event_Excluir_botaoActionPerformed

    private void CadastrarCriptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarCriptoActionPerformed
        CriptoCadastro cripto = new CriptoCadastro();
        cripto.setVisible(true);
    }//GEN-LAST:event_CadastrarCriptoActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        DeleteCripto p = new DeleteCripto();
        p.setVisible(true);
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        SaldoAdmin p = new SaldoAdmin();
        p.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtualizarActionPerformed
        AtualizarCotacao.atualizarCotacao(moedaDAO);

    }//GEN-LAST:event_AtualizarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Atualizar;
    private javax.swing.JButton CadastrarCripto;
    private javax.swing.JButton Excluir_botao;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
