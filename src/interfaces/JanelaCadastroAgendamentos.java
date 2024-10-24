/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import entidade.Paciente;
import entidade.Campanha;
import entidade.Agendamento;
import javax.swing.DefaultListModel;
import controle.ControladorCadastroAgendamentos;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import entidade.Agendamento.Status;
import java.util.Calendar;
/**
 *
 * @author cadug
 */
public class JanelaCadastroAgendamentos extends javax.swing.JFrame {
    ControladorCadastroAgendamentos controlador;
    DefaultListModel modelo_lista_agendamentos;
    Paciente[] pacientes_cadastrados;
    Campanha[] campanhas_cadastradas;
    
    public JanelaCadastroAgendamentos(ControladorCadastroAgendamentos controlador) {
        this.controlador = controlador;
        pacientes_cadastrados = Paciente.getVisões();
        campanhas_cadastradas = Campanha.getVisões();
        initComponents();
        inicializarListaAvaliacoes();
        limparCampos();
    }
    
    public JanelaCadastroAgendamentos(){}
    
    private void inicializarListaAvaliacoes() {
	modelo_lista_agendamentos =  (DefaultListModel)agendamentosList.getModel(); 
	Agendamento[] visões = Agendamento.getVisões();
	for (Agendamento visão :visões)modelo_lista_agendamentos.addElement(visão);
    }
    
    private void limparCampos(){
        sequencialTextField.setText("");
        nomeAcomTextField.setText("");
        dataHoraTextField.setText("");
        pacientesComboBox.setSelectedIndex(-1);
        campanhasComboBox.setSelectedIndex(-1);
        acompanhantebuttonGroup.clearSelection();
    }

    private void inserirAgendamento (java.awt.event.ActionEvent evt){
        Agendamento agendamento = obtémAgendamentoInformado();
        String mensagem_erro = null;
        if (agendamento != null) mensagem_erro = controlador.inserirAgendamento(agendamento);
        else mensagem_erro = "Algum atributo do agendamento não foi informado";
        if (mensagem_erro == null) {
            int sequencial = Agendamento.últimoSequencial();
            agendamento.setSequencial(sequencial);
            modelo_lista_agendamentos.addElement(agendamento.getVisão());
            agendamentosList.setSelectedIndex(modelo_lista_agendamentos.size() - 1);
            sequencialTextField.setText("" + sequencial);
            dataHoraTextField.setText(Agendamento.formatarDataHora(agendamento.getDataHora().toString()));
        }else informarErro (mensagem_erro);
    }
    private void informarErro(String mensagem){
        JOptionPane.showMessageDialog(this, mensagem, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
    
    private Agendamento obtémAgendamentoInformado() {
        String sequencial_str = sequencialTextField.getText();
        int sequencial = 0;
        if (!sequencial_str.isEmpty()) sequencial = Integer.parseInt(sequencial_str);
        String acompanhante = nomeAcomTextField.getText();
        if (acompanhante.isEmpty()) return null;
        Paciente visão_paciente = (Paciente) pacientesComboBox.getSelectedItem();
        if (visão_paciente == null) return null;
        Campanha visão_campanha = (Campanha) campanhasComboBox.getSelectedItem ();
        if (visão_campanha == null) return null;
        Status status_agendamento = null;
        if (statusComboBox.getSelectedItem() != null)
        status_agendamento = (Status) statusComboBox.getSelectedItem();
        else return null;
        char possuiAcom = 'X';
        if (acompanhantebuttonGroup.getSelection() != null)
        possuiAcom = (char) acompanhantebuttonGroup.getSelection().getMnemonic();
        Timestamp data_hora = new Timestamp (Calendar.getInstance().getTimeInMillis());
        return new Agendamento(data_hora, sequencial, visão_paciente, visão_campanha, acompanhante,
        possuiAcom, status_agendamento);
        
    }
    
    
    
    private void alterarAgendamento(java.awt.event.ActionEvent evt){
        Agendamento agendamento = obtémAgendamentoInformado ();
        String mensagem_erro = null;
        if (agendamento != null) mensagem_erro = controlador.alterarAgendamento(agendamento);
        else mensagem_erro = "Algum atributo do agendaemnto não fol informado";
        if (mensagem_erro != null) informarErro (mensagem_erro);
}
    
     private void consultarAgendamento(java.awt.event.ActionEvent evt) {                                   
        Agendamento visão = (Agendamento)agendamentosList.getSelectedValue();
        Agendamento agendamento = null;
        String mensagem_erro = null;
        if (visão != null) {
                agendamento = Agendamento.buscarAgendamento(visão.getSequencial());
                if (agendamento == null) mensagem_erro = "Agendamento não cadastrada";
        }else mensagem_erro = "Nenhuma campanha selecionada";
        if (mensagem_erro == null) {
            //Timestamp dataHora, int sequencial, Paciente paciente, Campanha campanha, 
            //String acompanhante, char possuiAcom, Status status
                String str_seq = String.valueOf(agendamento.getSequencial());
                sequencialTextField.setText(str_seq);
                nomeAcomTextField.setText(agendamento.getAcompanhante());
                selecionarAcompanhanteRadioButton(agendamento.isPossuiAcom());
                statusComboBox.setSelectedItem(agendamento.getStatus());
                agendamentosList.updateUI();
                campanhasComboBox.setSelectedItem(getVisãoCampanhaSelecionada(agendamento));
                pacientesComboBox.setSelectedItem(getVisãoPacienteSelecionado(agendamento));
                dataHoraTextField.setText(Agendamento.formatarDataHora(agendamento.getDataHora().toString()));
        } else informarErro (mensagem_erro);
        
    }
     
    private Paciente getVisãoPacienteSelecionado(Agendamento agendamento){
        pacientes_cadastrados = Paciente.getVisões();
        for (Paciente visão : pacientes_cadastrados){
            if (visão.getCpf().equals(agendamento.getPaciente().getCpf()))
                return visão;
        }
        return null;
    }
    
    private Campanha getVisãoCampanhaSelecionada(Agendamento agendamento){
        campanhas_cadastradas = Campanha.getVisões();
        for (Campanha visão : campanhas_cadastradas){
            if (visão.getSequencial() == agendamento.getCampanha().getSequencial())
                return visão;
        }
        return null;
    }
    
     private void selecionarAcompanhanteRadioButton(char selecao){
        switch (selecao){
            case 'T': simRadioButton.setSelected(true); break;
            case 'F': naoRadioButton.setSelected(true);
        }
    }
    
     private void removerAgendamento(java.awt.event.ActionEvent evt) {                                 
        Agendamento visão = (Agendamento) agendamentosList.getSelectedValue();
        String mensagem_erro = null;
        if (visão != null) mensagem_erro = controlador.removerAgendamento(visão.getSequencial());
        else mensagem_erro = "Nenhum agendamento selecionadp";
        if (mensagem_erro == null) modelo_lista_agendamentos.removeElement(visão);
        else informarErro (mensagem_erro);
       
    }  
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        acompanhantebuttonGroup = new javax.swing.ButtonGroup();
        sequencialLabel = new javax.swing.JLabel();
        sequencialTextField = new javax.swing.JTextField();
        possuiAcomLabel = new javax.swing.JLabel();
        simRadioButton = new javax.swing.JRadioButton();
        naoRadioButton = new javax.swing.JRadioButton();
        nomeAcomLabel = new javax.swing.JLabel();
        nomeAcomTextField = new javax.swing.JTextField();
        dataHoraLabel = new javax.swing.JLabel();
        dataHoraTextField = new javax.swing.JTextField();
        pacientesLabel = new javax.swing.JLabel();
        pacientesComboBox = new javax.swing.JComboBox<>();
        campanhasLabel = new javax.swing.JLabel();
        campanhasComboBox = new javax.swing.JComboBox<>();
        agendamentosLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        agendamentosList = new javax.swing.JList<>();
        statusLabel = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        sequencialLabel.setText("ID Sequencial");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 110, 0, 0);
        getContentPane().add(sequencialLabel, gridBagConstraints);

        sequencialTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 18, 0, 0);
        getContentPane().add(sequencialTextField, gridBagConstraints);

        possuiAcomLabel.setText("Possui Acompanhante");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 62, 0, 0);
        getContentPane().add(possuiAcomLabel, gridBagConstraints);

        acompanhantebuttonGroup.add(simRadioButton);
        simRadioButton.setMnemonic('T');
        simRadioButton.setText("Sim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 0, 0);
        getContentPane().add(simRadioButton, gridBagConstraints);

        acompanhantebuttonGroup.add(naoRadioButton);
        naoRadioButton.setMnemonic('F');
        naoRadioButton.setText("Não");
        naoRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naoRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 0, 0);
        getContentPane().add(naoRadioButton, gridBagConstraints);

        nomeAcomLabel.setText("Nome do(a) Acompanhante");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 32, 0, 0);
        getContentPane().add(nomeAcomLabel, gridBagConstraints);

        nomeAcomTextField.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
        getContentPane().add(nomeAcomTextField, gridBagConstraints);

        dataHoraLabel.setText("Data e Hora");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 119, 0, 0);
        getContentPane().add(dataHoraLabel, gridBagConstraints);

        dataHoraTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 109;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 18, 0, 0);
        getContentPane().add(dataHoraTextField, gridBagConstraints);

        pacientesLabel.setText("Pacientes Cadastrados");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 63, 0, 0);
        getContentPane().add(pacientesLabel, gridBagConstraints);

        pacientesComboBox.setModel(new DefaultComboBoxModel(pacientes_cadastrados));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 18, 0, 0);
        getContentPane().add(pacientesComboBox, gridBagConstraints);

        campanhasLabel.setText("Campanhas Cadastradas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 51, 0, 0);
        getContentPane().add(campanhasLabel, gridBagConstraints);

        campanhasComboBox.setModel(new DefaultComboBoxModel(campanhas_cadastrados));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 0, 0);
        getContentPane().add(campanhasComboBox, gridBagConstraints);

        agendamentosLabel.setText("Agendamentos Cadastrados");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(119, 32, 0, 0);
        getContentPane().add(agendamentosLabel, gridBagConstraints);

        agendamentosList.setModel(new DefaultListModel());
        jScrollPane1.setViewportView(agendamentosList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 263;
        gridBagConstraints.ipady = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(30, 18, 0, 82);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        statusLabel.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 149, 0, 0);
        getContentPane().add(statusLabel, gridBagConstraints);

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Confirmado", "Cancelado" }));
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 58, 0);
        getContentPane().add(statusComboBox, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void naoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naoRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_naoRadioButtonActionPerformed

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(JanelaCadastroAgendamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroAgendamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroAgendamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroAgendamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaCadastroAgendamentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup acompanhantebuttonGroup;
    private javax.swing.JLabel agendamentosLabel;
    private javax.swing.JList<Agendamento> agendamentosList;
    private javax.swing.JComboBox<Campanha> campanhasComboBox;
    private javax.swing.JLabel campanhasLabel;
    private javax.swing.JLabel dataHoraLabel;
    private javax.swing.JTextField dataHoraTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton naoRadioButton;
    private javax.swing.JLabel nomeAcomLabel;
    private javax.swing.JTextField nomeAcomTextField;
    private javax.swing.JComboBox<Paciente> pacientesComboBox;
    private javax.swing.JLabel pacientesLabel;
    private javax.swing.JLabel possuiAcomLabel;
    private javax.swing.JLabel sequencialLabel;
    private javax.swing.JTextField sequencialTextField;
    private javax.swing.JRadioButton simRadioButton;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
