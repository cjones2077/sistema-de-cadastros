/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import controle.ControladorCadastroPacientes;
import entidade.Paciente;
import entidade.Paciente.Comorbidade;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author cadug
 */
public class JanelaCadastroPacientes extends javax.swing.JFrame {

    ControladorCadastroPacientes controlador;
    Paciente[] pacientes_cadastrados;
    
    public JanelaCadastroPacientes(ControladorCadastroPacientes controlador) {
        this.controlador = controlador;
        pacientes_cadastrados = Paciente.getVisões();
        initComponents();
        limparCampos();
    }
    public JanelaCadastroPacientes(){
        
    }
   
    private void limparCampos(){
        nomeTextField.setText("");
        cpfTextField.setText("");
        faixa_etariaTextField.setText("");
        comorbidadeComboBox.setSelectedIndex(-1);
        sexoButtonGroup.clearSelection();
    }
   
    private void informarErro(String mensagem){
        JOptionPane.showMessageDialog(this, mensagem, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
    
    private Paciente obterPacienteInformado(){
        String Nome = nomeTextField.getText();
        if (Nome.isEmpty()) return null;
        String CPF = cpfTextField.getText();
        if (CPF.isEmpty()) return null;
        String FaixaEt = faixa_etariaTextField.getText();
        if (FaixaEt.isEmpty()) return null;
        Comorbidade comorbidade = null;
        if (comorbidadeComboBox.getSelectedItem() != null)
        comorbidade = (Comorbidade)comorbidadeComboBox.getSelectedItem();
        else return null;
        char sexo = 'X';
        if (sexoButtonGroup.getSelection() != null)
            sexo = (char) sexoButtonGroup.getSelection().getMnemonic();
        else return null;
        return new Paciente(CPF, Nome, FaixaEt, comorbidade, sexo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sexoButtonGroup = new javax.swing.ButtonGroup();
        pacientes_cadastradosLabel = new javax.swing.JLabel();
        comorbidadeComboBox = new javax.swing.JComboBox<>();
        comorbidadeLabel = new javax.swing.JLabel();
        cpfLabel = new javax.swing.JLabel();
        cpfTextField = new javax.swing.JTextField();
        nomeLabel = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        faixa_etariaLabel = new javax.swing.JLabel();
        faixa_etariaTextField = new javax.swing.JTextField();
        pacientes_cadastradosComboBox = new javax.swing.JComboBox<>();
        sexoPanel = new javax.swing.JPanel();
        masculinoRadioButton = new javax.swing.JRadioButton();
        femininoRadioButton = new javax.swing.JRadioButton();
        sexoLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        inserirButton = new javax.swing.JButton();
        alterarButton = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();
        limparButton = new javax.swing.JButton();
        consultarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Pacientes");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pacientes_cadastradosLabel.setText("Pacientes Cadastrados");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(60, 8, 0, 0);
        getContentPane().add(pacientes_cadastradosLabel, gridBagConstraints);

        comorbidadeComboBox.setModel(new DefaultComboBoxModel(Comorbidade.values()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 1, 0, 0);
        getContentPane().add(comorbidadeComboBox, gridBagConstraints);

        comorbidadeLabel.setText("Comorbidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 53, 0, 0);
        getContentPane().add(comorbidadeLabel, gridBagConstraints);

        cpfLabel.setText("CPF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 105, 0, 0);
        getContentPane().add(cpfLabel, gridBagConstraints);

        cpfTextField.setColumns(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 1, 0, 0);
        getContentPane().add(cpfTextField, gridBagConstraints);

        nomeLabel.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 93, 0, 0);
        getContentPane().add(nomeLabel, gridBagConstraints);

        nomeTextField.setColumns(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 1, 0, 0);
        getContentPane().add(nomeTextField, gridBagConstraints);

        faixa_etariaLabel.setText("Faixa Etária");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 67, 0, 0);
        getContentPane().add(faixa_etariaLabel, gridBagConstraints);

        faixa_etariaTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 217;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 1, 0, 0);
        getContentPane().add(faixa_etariaTextField, gridBagConstraints);

        pacientes_cadastradosComboBox.setModel(new DefaultComboBoxModel(pacientes_cadastrados));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(57, 1, 0, 0);
        getContentPane().add(pacientes_cadastradosComboBox, gridBagConstraints);

        sexoPanel.setPreferredSize(new java.awt.Dimension(151, 33));

        sexoButtonGroup.add(masculinoRadioButton);
        masculinoRadioButton.setMnemonic('M');
        masculinoRadioButton.setText("Masculino");
        masculinoRadioButton.setActionCommand("");
        sexoPanel.add(masculinoRadioButton);

        sexoButtonGroup.add(femininoRadioButton);
        femininoRadioButton.setMnemonic('F');
        femininoRadioButton.setText("Feminino");
        femininoRadioButton.setToolTipText("");
        femininoRadioButton.setActionCommand("");
        sexoPanel.add(femininoRadioButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        getContentPane().add(sexoPanel, gridBagConstraints);

        sexoLabel.setText("Sexo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 106, 0, 0);
        getContentPane().add(sexoLabel, gridBagConstraints);

        inserirButton.setText("Inserir");
        inserirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirPaciente(evt);
            }
        });
        jPanel1.add(inserirButton);

        alterarButton.setText("Alterar");
        alterarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarPaciente(evt);
            }
        });
        jPanel1.add(alterarButton);

        removerButton.setText("Remover");
        removerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerPaciente(evt);
            }
        });
        jPanel1.add(removerButton);

        limparButton.setText("Limpar");
        limparButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparButtonlimparCampos(evt);
            }
        });
        jPanel1.add(limparButton);

        consultarButton.setText("Consultar");
        consultarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarPaciente(evt);
            }
        });
        jPanel1.add(consultarButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 24, 17, 99);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inserirPaciente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirPaciente
        Paciente novoPaciente = obterPacienteInformado();
        String mensagem_erro = null;
        if (novoPaciente != null) mensagem_erro = controlador.inserirPaciente(novoPaciente);
        else mensagem_erro = "Algum atributo do paciente não foi informado";
        if (mensagem_erro == null){
            Paciente visão = novoPaciente.getVisão();
            pacientes_cadastrados = Paciente.getVisões();
            pacientes_cadastradosComboBox.addItem(novoPaciente);
            pacientes_cadastradosComboBox.setSelectedItem(novoPaciente);
        }
        else informarErro(mensagem_erro);
        
    }//GEN-LAST:event_inserirPaciente

    private Paciente getVisãoAlterada(String cpf){
        for (Paciente visão : pacientes_cadastrados){
            if (visão.getCpf().equals(cpf)) return visão;
        }
        return null;
    }
    
    private void alterarPaciente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarPaciente
        Paciente paciente = obterPacienteInformado();
        String mensagem_erro = null;
        if (paciente != null) mensagem_erro = controlador.alterarPaciente(paciente); 
        else mensagem_erro = "Algum atributo do paciente não foi informado";
        if (mensagem_erro == null) {
            Paciente visão = getVisãoAlterada(paciente.getCpf());
            if (visão != null) {
                visão.setNome(paciente.getNome());
                visão.setFaixaEt(paciente.getFaixaEt());
                pacientes_cadastradosComboBox.removeAllItems();
                for (Paciente p : pacientes_cadastrados) pacientes_cadastradosComboBox.addItem(p);
                pacientes_cadastradosComboBox.setSelectedItem(visão);
            }
        } else informarErro (mensagem_erro);

    }//GEN-LAST:event_alterarPaciente

    private void removerPaciente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerPaciente
        Paciente visão = (Paciente) pacientes_cadastradosComboBox.getSelectedItem();
        String mensagem_erro = null;
        if (visão != null) mensagem_erro = controlador.removerPaciente(visão.getCpf());
        else mensagem_erro = "Nenhum paciente selecionada";
        if (mensagem_erro == null){
            pacientes_cadastradosComboBox.removeItem(visão);
            if (pacientes_cadastrados.length >= 1) pacientes_cadastradosComboBox.setSelectedIndex(0);
            else pacientes_cadastradosComboBox.setSelectedIndex(-1);
        } else informarErro(mensagem_erro);
    }//GEN-LAST:event_removerPaciente

    private void limparButtonlimparCampos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparButtonlimparCampos
        limparCampos();
    }//GEN-LAST:event_limparButtonlimparCampos

    private void consultarPaciente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarPaciente
        Paciente visão = (Paciente) pacientes_cadastradosComboBox.getSelectedItem();
        Paciente paciente = null;
        String mensagem_erro = null;
        if (visão != null) {
            paciente = Paciente.buscarPaciente(visão.getCpf());
            if (paciente == null) mensagem_erro = "Campanha não cadastrada";
        }else mensagem_erro = "Nenhuma paciente selecionada";
        if (mensagem_erro == null) {
            cpfTextField.setText(paciente.getCpf());
            faixa_etariaTextField.setText(paciente.getFaixaEt());
            nomeTextField.setText(paciente.getNome());
            comorbidadeComboBox.setSelectedItem(paciente.getComorbidade());
            selecionarSexoRadioButton(paciente.getSexo());
        } else informarErro (mensagem_erro);
    }//GEN-LAST:event_consultarPaciente
    private void selecionarSexoRadioButton(char sexo){
        switch (sexo){
            case 'M': masculinoRadioButton.setSelected(true); break;
            case 'F': femininoRadioButton.setSelected(true);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaCadastroPacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarButton;
    private javax.swing.JComboBox<String> comorbidadeComboBox;
    private javax.swing.JLabel comorbidadeLabel;
    private javax.swing.JButton consultarButton;
    private javax.swing.JLabel cpfLabel;
    private javax.swing.JTextField cpfTextField;
    private javax.swing.JLabel faixa_etariaLabel;
    private javax.swing.JTextField faixa_etariaTextField;
    private javax.swing.JRadioButton femininoRadioButton;
    private javax.swing.JButton inserirButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton limparButton;
    private javax.swing.JRadioButton masculinoRadioButton;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JComboBox<Paciente> pacientes_cadastradosComboBox;
    private javax.swing.JLabel pacientes_cadastradosLabel;
    private javax.swing.JButton removerButton;
    private javax.swing.ButtonGroup sexoButtonGroup;
    private javax.swing.JLabel sexoLabel;
    private javax.swing.JPanel sexoPanel;
    // End of variables declaration//GEN-END:variables
}
