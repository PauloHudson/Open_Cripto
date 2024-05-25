package View;

import Model.Extrato;
import Model.Usuario;
import dao.ExtratoDAO;
import dao.conexao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExtratoView extends javax.swing.JFrame {
    private Usuario usuarioLogado;

    public ExtratoView(Usuario usuarioLogado) throws SQLException {
        this.usuarioLogado = usuarioLogado;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        exibirExtrato();
    }

    private void exibirExtrato() {

        try (Connection connection = new conexao().getConnection()) {
            ExtratoDAO extratoDAO = new ExtratoDAO(connection);
            List<Extrato> extratos = extratoDAO.buscarPorUsuario(usuarioLogado.getUsuario());

            StringBuilder extratoTexto = new StringBuilder();
            for (Extrato extrato : extratos) {
                extratoTexto.append(String.format("Data: %s, Horário: %s, Usuário: %s, Moeda: %s, Condição: %s, Cotação: %.2f, Taxa: %.2f, Valor Acionado: %.2f, Valor Final: %.2f%n",
                        extrato.getData(),
                        extrato.getHorario(),
                        extrato.getUsuario(),
                        extrato.getTipoMoeda(),
                        extrato.getCondicao(),
                        extrato.getCotacao(),
                        extrato.getTaxa(),
                        extrato.getValor_acionado(),
                        extrato.getValorFinal()));
            }
            txtDisplayExtrato.setText(extratoTexto.toString());
            jLabel4.setText(String.format("CPF: %s", usuarioLogado.getUsuario()));
            jLabel5.setText(String.format("Nome: %s", usuarioLogado.getNome()));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar o extrato.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDisplayExtrato = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Extrato");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 6, 200, -1));

        txtDisplayExtrato.setColumns(20);
        txtDisplayExtrato.setRows(5);
        jScrollPane1.setViewportView(txtDisplayExtrato);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 890, 150));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Nome:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 334, 46));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("CPF:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 334, 46));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, 290));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/Back_back.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDisplayExtrato;
    // End of variables declaration//GEN-END:variables
}
