
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

public class SaqueView extends javax.swing.JFrame {
    private Usuario usuario;
    private SaqueController controller;

    public SaqueView(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
        this.controller = new SaqueController(this, usuario); 
    }


    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sacar_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ValorSacar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        SenhaField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Sacar_button.setText("Depositar");
        Sacar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sacar_buttonActionPerformed(evt);
            }
        });

        jLabel1.setText("Valor a Sacar em BRL:");

        jLabel2.setText("Senha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(Sacar_button, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(ValorSacar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SenhaField))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addComponent(SenhaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ValorSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(Sacar_button, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

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
    // End of variables declaration//GEN-END:variables
}
