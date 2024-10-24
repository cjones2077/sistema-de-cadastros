package entidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import persistência.BD;
        
public class Paciente {
    public enum Comorbidade{Nenhuma, Cardíaco, Respiratório, Obeso, Diabético};
    private String nome, cpf, faixa_etaria;
    private Comorbidade comorbidade;
    private char sexo;
    
    public static Paciente buscarPaciente (String cpf){
        String sql = "SELECT * FROM pacientes WHERE Cpf = ?";
        ResultSet lista_resultados = null;
        Paciente novoPaciente = null;
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, cpf); 
            lista_resultados = comando.executeQuery (); 
            while (lista_resultados.next ()) {
                Comorbidade comorbidade = Comorbidade.values()[lista_resultados.getInt("Comorbidade")];
                novoPaciente = new Paciente(cpf, lista_resultados.getString("Nome"),
                    lista_resultados.getString ("FaixaEt"), comorbidade, lista_resultados.getString("sexo").toCharArray()[0]);
            }
            lista_resultados.close(); 
            comando.close();
        } catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace (); 
            novoPaciente = null;}
        
        return novoPaciente;
    }
              
    public static String inserirPaciente(Paciente novoPaciente) {
        String sql = "INSERT INTO pacientes (Cpf, Nome, FaixaEt, Comorbidade, Sexo) VALUES  (?, ?, ?, ?, ?)";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql); 
            comando.setString(1, novoPaciente.getCpf());
            comando.setString(2, novoPaciente.getNome());
            comando.setString(3, novoPaciente.getFaixaEt());
            comando.setInt(4, novoPaciente.getComorbidade().ordinal());
            comando.setString(5, (novoPaciente.getSexo() + ""));
            comando.executeUpdate();
            comando.close(); 
            return null;
        }catch (SQLException exceção_sql) {
        exceção_sql.printStackTrace ();
        return "Erro na Inserção do Paciente no BD";}
    }

    public static String alterarPaciente(Paciente novoPaciente){
        String sql = "UPDATE pacientes SET Nome = ?, FaixaEt = ?, Comorbidade = ?, Sexo = ? WHERE Cpf = ?";
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, novoPaciente.getNome());
            comando.setString(2, novoPaciente.getFaixaEt());
            comando.setInt(3, novoPaciente.getComorbidade().ordinal());
            comando.setString(4, (novoPaciente.getSexo() + ""));
            comando.setString(5, (novoPaciente.getCpf()));
            comando.executeUpdate();
            comando.close();
            return null;
        }catch (SQLException exceção_sql){
            exceção_sql.printStackTrace();
            return "Erro na Alteração no BD";
        }
    }
    
    public static String removerPaciente (String cpf) { 
        String sql = "DELETE FROM pacientes WHERE Cpf = ?";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setString(1, cpf);
            comando.executeUpdate();
            comando.close();
            return null;
        }catch (SQLException exceção_sql) {
            exceção_sql.printStackTrace();
            return "Erro na remoção do Paciente do BD";
        }
    }
    
    public Paciente(String cpf, String nome, String faixa_etaria, Comorbidade comorbidade, char sexo) {
        this.cpf = cpf;
        this.nome = nome;
        this.faixa_etaria = faixa_etaria;
        this.comorbidade = comorbidade;
        this.sexo = sexo;
    }
    
    public Paciente(String cpf, String nome, String faixaet) {
        this.cpf = cpf;
        this.nome = nome;
        this.faixa_etaria = faixaet;
    }
    
    public static Paciente[] getVisões() {
        String sql = "SELECT Cpf, Nome, FaixaEt FROM pacientes"; 
	ResultSet lista_resultados = null;
	ArrayList<Paciente> visões = new ArrayList();				
	try {				
		PreparedStatement comando = BD.conexão.prepareStatement(sql);
		 lista_resultados = comando.executeQuery ();				
		while (lista_resultados.next()) {	
                        String cpf = lista_resultados.getString("Cpf");
			String nome = lista_resultados.getString("Nome");				
			String faixaet = lista_resultados.getString ("FaixaEt"); 
			visões.add(new Paciente (cpf, nome, faixaet));				
		}				
		lista_resultados.close();
		 comando.close ();				
	} catch (SQLException exceção_sql) {exceção_sql.printStackTrace();}
	return	visões.toArray(new Paciente[visões.size()] );
    }
    
    public Paciente getVisão() {return new Paciente(cpf, nome, faixa_etaria);}
    
    
    public String getCpf(){return cpf;}
    public String getNome(){return nome;} 
    public String getFaixaEt(){return faixa_etaria;}
    public Comorbidade getComorbidade(){return comorbidade;}
    public char getSexo(){return sexo;}
    public String toString(){return "[" + cpf + "] " + nome + ", Faixa Etária: " + faixa_etaria;}
    public void setCpf(String cpf){this.cpf = cpf;}
    public void setNome(String nome){this.nome = nome;}
    public void setFaixaEt(String faixa_etaria){this.faixa_etaria = faixa_etaria;}
    public void setComorbidade(Comorbidade comorbidade){this.comorbidade = comorbidade;}
    
}