
package View;
import Controller.DepositoController;
import Controller.SaqueController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Usuario;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Controller.SaqueController;
import Model.Usuario;
import javax.swing.*;

/**
 *
 * @author paulo
 */

public class SaqueView extends javax.swing.JFrame {
    private Usuario usuario;
    private SaqueController controller;

    public SaqueView(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
        this.controller = new SaqueController(this, usuario);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }


    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sacar_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ValorSacar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        SenhaField = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Sacar_button.setText("Depositar");
        Sacar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sacar_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(Sacar_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 180, 60));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 250, 250));
        jLabel1.setText("Valor a Sacar em BRL:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));
        getContentPane().add(ValorSacar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 180, 40));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(250, 250, 250));
        jLabel2.setText("Senha");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));
        getContentPane().add(SenhaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 56, 180, 40));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(250, 250, 250));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Sacar_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sacar_buttonActionPerformed

        controller.realizarDeposito();
    }//GEN-LAST:event_Sacar_buttonActionPerformed

 
    public JTextField getValorSacar() {
        return ValorSacar;
    }

    public JPasswordField getSenhaField() {
        return (JPasswordField) SenhaField;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Sacar_button;
    private javax.swing.JPasswordField SenhaField;
    private javax.swing.JTextField ValorSacar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
