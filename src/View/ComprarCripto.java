/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;
import Controller.DepositoController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Usuario;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ComprarCripto extends javax.swing.JFrame {
    private Usuario usuario;
    private DepositoController depositoController;

    public ComprarCripto(Usuario usuario) {
        this.usuario = usuario;
        this.depositoController = new DepositoController(this, usuario);
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Creates new form ComprarCripto
     */
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Deposito_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ValorDepositar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        SenhaField = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Deposito_button.setText("Depositar");
        Deposito_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Deposito_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(Deposito_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 180, 64));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 250, 250));
        jLabel1.setText("Valor a depositar em BRL:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));
        getContentPane().add(ValorDepositar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 300, 50));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(250, 250, 250));
        jLabel2.setText("Senha:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));
        getContentPane().add(SenhaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 66, 300, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Deposito_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Deposito_buttonActionPerformed

        depositoController.realizarDeposito();
    }//GEN-LAST:event_Deposito_buttonActionPerformed

 
    public JTextField getValorDepositar() {
        return ValorDepositar;
    }

    public JPasswordField getSenhaField() {
        return (JPasswordField) SenhaField;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Deposito_button;
    private javax.swing.JPasswordField SenhaField;
    private javax.swing.JTextField ValorDepositar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
