/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import persistência.BD;
import java.sql.Timestamp;

public class Agendamento {
    public enum Status {Pendente, Confirmado, Cancelado}
    private Timestamp dataHora;
    private int sequencial;
    private String acompanhante;
    private char possuiAcom;
    private Paciente paciente;
    private Campanha campanha;
    private Status status;
    
    public static Agendamento[] getVisões () {
        String sql = "SELECT Sequencial, PacienteCPF, CampanhaID FROM agendamentos";
        ResultSet lista_resultados = null;
        ArrayList<Agendamento> visões = new ArrayList ();
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                visões.add(new Agendamento (lista_resultados.getInt("Sequencial"),
                Paciente.buscarPaciente(lista_resultados.getString("PacienteCPF")).getVisão(),
                Campanha.buscarCampanha(lista_resultados.getInt("CampanhaID")).getVisão()));
            }
            lista_resultados.close();
            comando.close();
        }catch (SQLException exceção_sql) {exceção_sql.printStackTrace();}
        return visões.toArray(new Agendamento[visões.size()]);
    }
    
    public static boolean existeAgendamento (String chave_paciente, int chave_campanha) {
        String sql = "SELECT Sequencial FROM agendamento WHERE PacienteCPF = ? AND CampanhaId = ?";
        ResultSet lista_resultados = null;
        boolean existe = false;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement (sql);
            comando.setString (1, chave_paciente);
            comando.setInt(2, chave_campanha);
            lista_resultados = comando.executeQuery ();
            while (lista_resultados.next ()){
                existe = true;
            }
            lista_resultados.close ();
            comando.close ();
        }catch (SQLException exceção_sql){
            exceção_sql.printStackTrace();
        }
        return existe;
    }
    
    public static String inserirAgendamento (Agendamento agendamento) {
        String sql = "INSERT INTO agendamento(Acompanhante, PossuiAcom, PacienteCPF, CampanhaID,"
        + "Status_, DataHora) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement comando = BD.conexão.prepareStatement (sql);
            comando.setString(1, agendamento.getAcompanhante());
            comando.setString (2, agendamento.isPossuiAcom() + "");
            comando.setString (3, agendamento.getPaciente().getCpf());
            comando.setInt (4, agendamento.getCampanha().getSequencial());
            int indice_status = -1;
            if (agendamento.getStatus() != null) indice_status = agendamento.getStatus().ordinal();
            comando.setInt (5, indice_status);
            comando.setTimestamp(6, agendamento.getDataHora());
            comando.executeUpdate ();
            comando.close (); 
            return null;
        }catch (SQLException exceção_sql){
            exceção_sql.printStackTrace ();
            return "Erro na Inserção da Avaliação no BD";
        }
    }
    
    public static String alterarAgendamento (Agendamento agendamento) {
        String sql = "UPDATE agendamento SET Acompanhante = ?, PossuiAcom = ?,"
        + "Status_ = ?, DataHora = ? WHERE Sequencial = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement (sql);
            comando.setString(1, agendamento.getAcompanhante());
            comando.setString (2, agendamento.isPossuiAcom() + "");
            int indice_status = -1;
            if (agendamento.getStatus() != null) indice_status = agendamento.getStatus().ordinal();
            comando.setInt (3, indice_status);
            comando.setTimestamp(4, agendamento.getDataHora());
            comando.setInt (5,  agendamento.getSequencial());
            comando.executeUpdate ();
            comando.close ();
            return null;
        }catch (SQLException exceção_sql){
            exceção_sql.printStackTrace ();
            return "Erro na Alteração da Avaliação no BD";
        }   
    }
    
    public static Agendamento buscarAgendamento (int sequencial) {
        String sql = "SELECT * FROM agendamento WHERE Sequencial = ?";
        ResultSet lista_resultados = null;
        Agendamento agendamento = null;
        try {
            PreparedStatement comando = BD.conexão.prepareStatement (sql);
            comando.setInt (1, sequencial);
            lista_resultados = comando.executeQuery();
            while (lista_resultados.next()) {
                Status status = null;
                if (lista_resultados.getInt("Status") > -1)
                    status = Status.values()[lista_resultados.getInt("Status")];
                agendamento = new Agendamento (lista_resultados.getTimestamp("DataHora"),
                    sequencial,
                    Paciente.buscarPaciente(lista_resultados.getString("PacienteCPF")),
                    Campanha.buscarCampanha(lista_resultados.getInt("CampanhaID")),
                    lista_resultados.getString("Acompanhante"),
                    lista_resultados.getString("PossuiAcom").toCharArray()[0], status);
            }
            lista_resultados.close();
            comando.close ();
        }catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace ();
            agendamento = null;
        }
        return agendamento;
    }
    
    public static int últimoSequencial(){
        String sql = "SELECT MAX(Sequencial) FROM agendamento";
        ResultSet lista_resultados = null;
        int sequencial = 0;
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            lista_resultados = comando.executeQuery();
            while(lista_resultados.next()){
                sequencial = lista_resultados.getInt(1);
            }
            lista_resultados.close();
            comando.close();
        }catch(SQLException exceção_sql){exceção_sql.printStackTrace();}
        return sequencial;
    }
    
    public static String removerAgendamento(Agendamento agendamento) {
        int sequencial = agendamento.getSequencial();
        String sql = "DELETE FROM agendamento WHERE Sequencial = ?";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql);
                comando.setInt(1, sequencial);
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {
                exceção_sql.printStackTrace();
                return "Erro na remoção do agendamento do BD";}
            return null;
        }
    
    public Agendamento(Timestamp dataHora, int sequencial, Paciente paciente, Campanha campanha, String acompanhante, char possuiAcom, Status status)
    {
        this.dataHora = dataHora;
        this.sequencial = sequencial;
        this.acompanhante = acompanhante;
        this.possuiAcom = possuiAcom;
        this.status = status;
    }
    public Agendamento(int sequencial, Paciente paciente, Campanha campanha){}
    
     public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public int getSequencial() {
        return sequencial;
    }

    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }

    public String getAcompanhante() {
        return acompanhante;
    }

    public void setAcompanhante(String acompanhante) {
        this.acompanhante = acompanhante;
    }

    public char isPossuiAcom() {
        return possuiAcom;
    }

    public void setPossuiAcom(char possuiAcom) {
        this.possuiAcom = possuiAcom;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
