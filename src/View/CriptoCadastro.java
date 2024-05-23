/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;



import Controller.JavaCadastroCripto;
import javax.swing.JTextField;

/**
 *
 * @author paulo
 */
public class CriptoCadastro extends javax.swing.JFrame {
    
    private final JavaCadastroCripto controller;
    /**
     * Creates new form NewJFrame
     */
    public CriptoCadastro() {
        initComponents();
        controller = new JavaCadastroCripto(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CadastrarCripto = new javax.swing.JButton();
        Cripto_Nome = new javax.swing.JTextField();
        Cripto_Valor = new javax.swing.JTextField();
        Cripto_Sigla = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Valor:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 121, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel2.setText("Sigla:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 121, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel3.setText("Nome:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 121, -1));

        CadastrarCripto.setText("CADASTRAR CRIPTO");
        CadastrarCripto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarCriptoActionPerformed(evt);
            }
        });
        getContentPane().add(CadastrarCripto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 397, 240, 70));
        getContentPane().add(Cripto_Nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 306, 47));
        getContentPane().add(Cripto_Valor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 306, 47));
        getContentPane().add(Cripto_Sigla, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 306, 47));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CadastrarCriptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarCriptoActionPerformed
        controller.SalvarMoeda();
        this.dispose();
    }//GEN-LAST:event_CadastrarCriptoActionPerformed

 
    //Getters And Setter
    public JTextField getCripto_Sigla() {
        return Cripto_Sigla;
    }

    public void setCripto_Sigla(JTextField Cripto_Sigla) {
        this.Cripto_Sigla = Cripto_Sigla;
    }

    public JTextField getCripto_Valor() {
        return Cripto_Valor;
    }

    public void setCripto_Valor(JTextField Cripto_Valor) {
        this.Cripto_Valor = Cripto_Valor;
    }
    
    public JTextField getCripto_Nome() {
        return Cripto_Nome;
    }

    public void setCripto_Nome(JTextField Cripto_Nome) {
        this.Cripto_Nome = Cripto_Nome;
    }
    
    
    //Getters And Setter
    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CadastrarCripto;
    private javax.swing.JTextField Cripto_Nome;
    private javax.swing.JTextField Cripto_Sigla;
    private javax.swing.JTextField Cripto_Valor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
