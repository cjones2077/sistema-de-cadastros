package interfaces;
import controle.ControladorCadastroCampanha;
import entidade.Sarampo;
import entidade.Covid19;
import entidade.H1N1;
import entidade.Campanha;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;

public class JanelaCadastroCampanha extends javax.swing.JFrame {

    ControladorCadastroCampanha controlador;
    DefaultListModel modelo_lista_campanhas;
    PainelCampanhaCovid19 campanha_covid19Painel;
    PainelCampanhaH1N1 campanha_h1n1Painel;
    PainelCampanhaSarampo campanha_sarampoPainel;
    
    public JanelaCadastroCampanha(ControladorCadastroCampanha controlador) {
        this.controlador = controlador;
        initComponents();
        campanha_covid19Painel = new PainelCampanhaCovid19();
        campanha_h1n1Painel = new PainelCampanhaH1N1();
        campanha_sarampoPainel = new PainelCampanhaSarampo();
        subclassesTabbedPane.addTab("Covid19", campanha_covid19Painel);
        subclassesTabbedPane.addTab("H1N1", campanha_h1n1Painel);  
        subclassesTabbedPane.addTab("Sarampo", campanha_sarampoPainel); 
        inicializarListaCampanhas();
        limparCampos();
    }
    
    public JanelaCadastroCampanha(){
    }
    
    private void inicializarListaCampanhas () {
	modelo_lista_campanhas =  (DefaultListModel)campanhas_cadastradasList.getModel(); 
	Campanha[] visões = Campanha.getVisões();
	for (Campanha visão :visões)modelo_lista_campanhas.addElement(visão);
    }
    private void limparCampos(){
        sequencialTextField.setText("");
        numeroDosesTextField.setText("");
        tipoTextField.setText("");
        campanha_covid19Painel.limparCampos();
        campanha_h1n1Painel.limparCampos();
        campanha_sarampoPainel.limparCampos();
    }
    
    private Campanha obterCampanhaInformada(){
        String sequencialStr = sequencialTextField.getText();
        int sequencial = 0;
        if (!sequencialStr.isEmpty()) sequencial = Integer.parseInt(sequencialStr);
        int numeroDoses = Integer.parseInt(numeroDosesTextField.getText());
        if (numeroDoses == 0) return null;
        String tipo = tipoTextField.getText();
        if (tipo.isEmpty()) return null;
        Campanha campanha = null;
        int i_aba = subclassesTabbedPane.getSelectedIndex();
        switch (i_aba){
            case 0:
                boolean reforço = campanha_covid19Painel.isReforço();
                String marca = campanha_covid19Painel.getMarca();
                campanha = new Covid19(sequencial, numeroDoses, tipo, marca, reforço);
                break;
            case 1:
                boolean emergencial = campanha_h1n1Painel.isEmergencial();
                int cepas = campanha_h1n1Painel.getCepas();
                campanha = new H1N1(sequencial, numeroDoses, tipo, cepas, emergencial);
                break;
            case 2:
                boolean adjuvante = campanha_sarampoPainel.isAdjuvante();
                int intervalo = campanha_sarampoPainel.getIntervalo();
                campanha = new Sarampo(sequencial, numeroDoses, tipo, intervalo, adjuvante);
                break;
        }
        return campanha;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sequencialLabel = new javax.swing.JLabel();
        sequencialTextField = new javax.swing.JTextField();
        numeroDosesLabel = new javax.swing.JLabel();
        numeroDosesTextField = new javax.swing.JTextField();
        tipoLabel = new javax.swing.JLabel();
        tipoTextField = new javax.swing.JTextField();
        comandosPanel = new javax.swing.JPanel();
        campanhas_cadastradasLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campanhas_cadastradasList = new javax.swing.JList<>();
        inserirButton = new javax.swing.JButton();
        alterarButton = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();
        limparButton = new javax.swing.JButton();
        consultarButton = new javax.swing.JButton();
        subclassesTabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Campanhas");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        sequencialLabel.setText("ID Sequencial");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 87, 0, 0);
        getContentPane().add(sequencialLabel, gridBagConstraints);

        sequencialTextField.setEditable(false);
        sequencialTextField.setColumns(4);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 86;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 0);
        getContentPane().add(sequencialTextField, gridBagConstraints);

        numeroDosesLabel.setText("Número de Doses");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 69, 0, 0);
        getContentPane().add(numeroDosesLabel, gridBagConstraints);

        numeroDosesTextField.setColumns(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 0);
        getContentPane().add(numeroDosesTextField, gridBagConstraints);

        tipoLabel.setText("Tipo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 139, 0, 0);
        getContentPane().add(tipoLabel, gridBagConstraints);

        tipoTextField.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 218;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 4, 0, 0);
        getContentPane().add(tipoTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 27;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(28, 5, 6, 0);
        getContentPane().add(comandosPanel, gridBagConstraints);

        campanhas_cadastradasLabel.setText("Campanhas Cadastradas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(60, 33, 0, 0);
        getContentPane().add(campanhas_cadastradasLabel, gridBagConstraints);

        campanhas_cadastradasList.setModel(new DefaultListModel());
        jScrollPane1.setViewportView(campanhas_cadastradasList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 23;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 499;
        gridBagConstraints.ipady = 127;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(29, 5, 0, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        inserirButton.setText("Inserir");
        inserirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirCampanha(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 104, 0, 0);
        getContentPane().add(inserirButton, gridBagConstraints);

        alterarButton.setText("Alterar");
        alterarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarCampanha(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 5, 0, 0);
        getContentPane().add(alterarButton, gridBagConstraints);

        removerButton.setText("Remover");
        removerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerCampanha(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 5, 0, 0);
        getContentPane().add(removerButton, gridBagConstraints);

        limparButton.setText("Limpar");
        limparButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparCampos(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 5, 0, 0);
        getContentPane().add(limparButton, gridBagConstraints);

        consultarButton.setText("Consultar");
        consultarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarCampanha(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 5, 0, 0);
        getContentPane().add(consultarButton, gridBagConstraints);

        subclassesTabbedPane.setPreferredSize(new java.awt.Dimension(295, 100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.ipadx = 295;
        gridBagConstraints.ipady = 99;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 139, 0, 0);
        getContentPane().add(subclassesTabbedPane, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limparCampos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparCampos
        limparCampos();
    }//GEN-LAST:event_limparCampos
   
    private void informarErro(String mensagem){
        JOptionPane.showMessageDialog(this, mensagem, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
    
    private void inserirCampanha(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirCampanha
        Campanha novaCampanha = obterCampanhaInformada();
        String mensagem_erro = null;
        if (novaCampanha != null) mensagem_erro = controlador.inserirCampanha(novaCampanha);
        else mensagem_erro = "Algum atributo não foi informado";
        if (mensagem_erro == null) {
           int sequencial = Campanha.últimoSequencial();
           novaCampanha.setSequencial(sequencial);
           modelo_lista_campanhas.addElement(novaCampanha);
           sequencialTextField.setText("" + sequencial);
        }else informarErro(mensagem_erro);
    }//GEN-LAST:event_inserirCampanha

    private void consultarCampanha(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarCampanha
        Campanha visão = (Campanha)campanhas_cadastradasList.getSelectedValue();
        Campanha campanha = null;
        String mensagem_erro = null;
        if (visão != null) {
                campanha = Campanha.buscarCampanha(visão.getSequencial());
                if (campanha == null) mensagem_erro = "Campanha não cadastrada";
        }else mensagem_erro = "Nenhuma campanha selecionada";
        if (mensagem_erro == null) {
                String str_seq = String.valueOf(campanha.getSequencial());
                sequencialTextField.setText(str_seq);
                String DosesStr = String.valueOf(campanha.getNumeroDoses());
                numeroDosesTextField.setText(DosesStr);
                tipoTextField.setText(campanha.getTipo());
                campanhas_cadastradasList.updateUI();
        } else informarErro (mensagem_erro);
        if (campanha instanceof Covid19){
            subclassesTabbedPane.setSelectedIndex(0);
            Covid19 aux = (Covid19) campanha;
            campanha_covid19Painel.setReforço(aux.getReforço());
            campanha_covid19Painel.setMarca(aux.getMarca());
        }
        else if (campanha instanceof H1N1){
            subclassesTabbedPane.setSelectedIndex(1);
            H1N1 aux = (H1N1) campanha;
            campanha_h1n1Painel.setEmergencial(aux.getEmergencial());
            campanha_h1n1Painel.setCepas(Integer.toString(aux.getCepas()));
        }
        else if (campanha instanceof Sarampo){
            subclassesTabbedPane.setSelectedIndex(2);
            Sarampo aux = (Sarampo) campanha;
            campanha_sarampoPainel.setAdjuvante(aux.getAdjuvante());
            campanha_sarampoPainel.setIntervalo(Integer.toString(aux.getIntervalo()));
        }
    }//GEN-LAST:event_consultarCampanha

    private void alterarCampanha(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarCampanha
        Campanha novaCampanha = obterCampanhaInformada();
        String mensagem_erro = null;
        if (novaCampanha != null) mensagem_erro = controlador.alterarCampanha(novaCampanha);
        else mensagem_erro = "Algum atributo não foi informado";
        if (mensagem_erro == null) {
           Campanha visão = (Campanha) campanhas_cadastradasList.getSelectedValue();
           if(visão != null){
               visão.setNumeroDoses(novaCampanha.getNumeroDoses());
               campanhas_cadastradasList.updateUI();
           }
        }else informarErro(mensagem_erro);
    }//GEN-LAST:event_alterarCampanha

    private void removerCampanha(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerCampanha
        Campanha visão = (Campanha) campanhas_cadastradasList.getSelectedValue();
        String mensagem_erro = null;
        if (visão != null) mensagem_erro = controlador.removerCampanha(visão);
        else mensagem_erro = "Nenhuma campanha selecionada";
        if (mensagem_erro == null) modelo_lista_campanhas.removeElement(visão);
        else informarErro (mensagem_erro);
       
    }//GEN-LAST:event_removerCampanha
   
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaCadastroCampanha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarButton;
    private javax.swing.JLabel campanhas_cadastradasLabel;
    private javax.swing.JList<Campanha> campanhas_cadastradasList;
    private javax.swing.JPanel comandosPanel;
    private javax.swing.JButton consultarButton;
    private javax.swing.JButton inserirButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limparButton;
    private javax.swing.JLabel numeroDosesLabel;
    private javax.swing.JTextField numeroDosesTextField;
    private javax.swing.JButton removerButton;
    private javax.swing.JLabel sequencialLabel;
    private javax.swing.JTextField sequencialTextField;
    private javax.swing.JTabbedPane subclassesTabbedPane;
    private javax.swing.JLabel tipoLabel;
    private javax.swing.JTextField tipoTextField;
    // End of variables declaration//GEN-END:variables
}
