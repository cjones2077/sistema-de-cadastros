package entidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import persistência.BD;
        
public class Campanha {
    public String  tipo;
    public int numero_de_doses;
    public int sequencial;
    
    public static boolean existeCampanhaIgual(Campanha campanha){
	String sql = "SELECT COUNT(Sequencial) FROM campanhas WHERE NumeroDoses = ? AND Tipo = ?";
	ResultSet lista_resultados = null;
	int n_campanhas_mesmos_atributos = 0;
	try { 
		PreparedStatement comando = BD.conexão.prepareStatement(sql) ;
		comando.setInt(1, campanha.getNumeroDoses()); 
		comando.setString(2, campanha.getTipo());
		lista_resultados = comando.executeQuery();
		while(lista_resultados.next()){
			 n_campanhas_mesmos_atributos = lista_resultados.getInt(1);
		}
		lista_resultados.close(); 
		comando.close();
	} catch (SQLException exceção_sql) {exceção_sql.printStackTrace();} 
	if(n_campanhas_mesmos_atributos > 0) return true;
	else return false;
        }


    public static Campanha buscarCampanha(int sequencial){
        String sql = "SELECT * FROM campanhas WHERE Sequencial = ?";
        ResultSet lista_resultados = null;
        int doses = 0;
        String tipo = null;
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, sequencial); 
            lista_resultados = comando.executeQuery (); 
            while (lista_resultados.next ()) {
                doses = lista_resultados.getInt("NumeroDoses");
                tipo =  lista_resultados.getString("Tipo");
            }
            lista_resultados.close(); 
            comando.close();  
        } catch (SQLException exceção_sql) {exceção_sql.printStackTrace ();}
        if (doses == 0) return null;
        sql = "SELECT Marca, Reforço FROM covid WHERE CampanhaID = ?";
        lista_resultados = null;
         try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, sequencial); 
            lista_resultados = comando.executeQuery (); 
            while (lista_resultados.next ()) {
                return (new Covid19(sequencial, doses, tipo,
                lista_resultados.getString("Marca"),
                lista_resultados.getBoolean("Reforço")));
            }
            lista_resultados.close(); 
            comando.close();  
        } catch (SQLException exceção_sql) {exceção_sql.printStackTrace ();}
         sql = "SELECT Cepas, Emergencial FROM h1n1 WHERE CampanhaID = ?";
        lista_resultados = null;
         try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, sequencial); 
            lista_resultados = comando.executeQuery (); 
            while (lista_resultados.next ()) {
                return (new H1N1(sequencial, doses, tipo,
                lista_resultados.getInt("Cepas"),
                lista_resultados.getBoolean("Emergencial")));
            }
            lista_resultados.close(); 
            comando.close();  
        } catch (SQLException exceção_sql) {exceção_sql.printStackTrace ();}
         sql = "SELECT Intervalo, Adjuvante FROM sarampo WHERE CampanhaID = ?";
        lista_resultados = null;
         try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, sequencial); 
            lista_resultados = comando.executeQuery (); 
            while (lista_resultados.next ()) {
                return (new Sarampo(sequencial, doses, tipo,
                lista_resultados.getInt("Intervalo"),
                lista_resultados.getBoolean("Adjuvante")));
            }
            lista_resultados.close(); 
            comando.close();  
        } catch (SQLException exceção_sql) {exceção_sql.printStackTrace ();}
         return null;
    }
              
    public static String inserirCampanha(Campanha novaCampanha) {
        String sql = "INSERT INTO campanhas (NumeroDoses, Tipo) VALUES  (?, ?)";
        try {
            PreparedStatement comando = BD.conexão.prepareStatement(sql); 
            comando.setInt(1, novaCampanha.getNumeroDoses());
            comando.setString(2, novaCampanha.getTipo());
            comando.executeUpdate();
            comando.close(); 
        }catch (SQLException exceção_sql) {
        exceção_sql.printStackTrace ();
        return "Erro na Inserção da Campanha no BD";}
        int sequencial = últimoSequencial();
        if (novaCampanha instanceof Covid19){
            Covid19 campanha = (Covid19) novaCampanha;
            sql = "INSERT INTO covid (Marca, Reforço, CampanhaID) VALUES (?, ?, ?)";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql); 
                comando.setString(1, campanha.getMarca());
                comando.setBoolean(2,campanha.getReforço());
                comando.setInt(3, sequencial);
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {exceção_sql.printStackTrace ();} 
        }
        else if (novaCampanha instanceof H1N1){
            H1N1 campanha = (H1N1) novaCampanha;
            sql = "INSERT INTO h1n1 (Cepas, Emergencial, CampanhaID) VALUES (?, ?, ?)";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql); 
                comando.setInt(1, campanha.getCepas());
                comando.setBoolean(2,campanha.getEmergencial());
                comando.setInt(3, sequencial);
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {exceção_sql.printStackTrace ();}
        }  
        else if (novaCampanha instanceof Sarampo){
            Sarampo campanha = (Sarampo) novaCampanha;
            sql = "INSERT INTO sarampo (Intervalo, Adjuvante, CampanhaID) VALUES (?, ?, ?)";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql); 
                comando.setInt(1, campanha.getIntervalo());
                comando.setBoolean(2,campanha.getAdjuvante());
                comando.setInt(3, sequencial);
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {exceção_sql.printStackTrace ();}
        } 
           return null;
    }

    public static String alterarCampanha(Campanha campanha){
        String sql = "UPDATE campanhas SET NumeroDoses = ?, Tipo = ? WHERE Sequencial = ?";
        try{
            PreparedStatement comando = BD.conexão.prepareStatement(sql);
            comando.setInt(1, campanha.getNumeroDoses());
            comando.setString(2, campanha.getTipo());
            comando.setInt(3, campanha.getSequencial());
            comando.executeUpdate();
            comando.close();
        }catch (SQLException exceção_sql){
            exceção_sql.printStackTrace();
            return "Erro na alteração no BD";
        }
        if (campanha instanceof Covid19){
            Covid19 novaCampanha = (Covid19) campanha;
            sql = "UPDATE covid SET Marca = ?, Reforço = ? WHERE CampanhaID = ?";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql); 
                comando.setString(1, novaCampanha.getMarca());
                comando.setBoolean(2, novaCampanha.getReforço());
                comando.setInt(3, novaCampanha.getSequencial());
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {exceção_sql.printStackTrace (); return "Erro na alteração no BD";}
        }
        if (campanha instanceof H1N1){
            H1N1 novaCampanha = (H1N1) campanha;
            sql = "UPDATE h1n1 SET Cepas = ?, Emergencial = ? WHERE CampanhaID = ?";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql); 
                comando.setInt(1, novaCampanha.getCepas());
                comando.setBoolean(2, novaCampanha.getEmergencial());
                comando.setInt(3, novaCampanha.getSequencial());
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {exceção_sql.printStackTrace (); return "Erro na alteração no BD";}
        }
        if (campanha instanceof Sarampo){
            Sarampo novaCampanha = (Sarampo) campanha;
            sql = "UPDATE sarampo SET Intervalo = ?, Adjuvante = ? WHERE CampanhaID = ?";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql); 
                comando.setInt(1, novaCampanha.getIntervalo());
                comando.setBoolean(2, novaCampanha.getAdjuvante());
                comando.setInt(3, novaCampanha.getSequencial());
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {exceção_sql.printStackTrace (); return "Erro na alteração no BD";}
        }
        return null;
    }
    
    public static String removerCampanha(Campanha campanha) {
        int sequencial = campanha.getSequencial();
        
        if (campanha instanceof Covid19){
            String sql = "DELETE FROM covid WHERE CampanhaID = ?";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql);
                comando.setInt(1, sequencial);
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {
                exceção_sql.printStackTrace();
                return "Erro na remoção da Campanha do BD";
            }
        }
        else if (campanha instanceof H1N1){
            String sql = "DELETE FROM h1n1 WHERE CampanhaID = ?";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql);
                comando.setInt(1, sequencial);
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {
                exceção_sql.printStackTrace();
                return "Erro na remoção da Campanha do BD";
            }
        }
        else if (campanha instanceof Sarampo){
            String sql = "DELETE FROM sarampo WHERE CampanhaID = ?";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql);
                comando.setInt(1, sequencial);
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {
                exceção_sql.printStackTrace();
                return "Erro na remoção da Campanha do BD";
            }
        }
        String sql = "DELETE FROM campanhas WHERE Sequencial = ?";
            try {
                PreparedStatement comando = BD.conexão.prepareStatement(sql);
                comando.setInt(1, sequencial);
                comando.executeUpdate();
                comando.close();
            }catch (SQLException exceção_sql) {
                exceção_sql.printStackTrace();
                return "Erro na remoção da Campanha do BD";}
            return null;
        }
                
    
    
    public static Campanha[] getVisões() {
    String sql = "SELECT c.Sequencial, c.NumeroDoses, c.Tipo, " +
                 "v1.Marca, v1.Reforço, " +
                 "v2.Cepas, v2.Emergencial, " +
                 "v3.Intervalo, v3.Adjuvante " +
                 "FROM campanhas c " +
                 "LEFT JOIN covid v1 ON c.Sequencial = v1.CampanhaID " +
                 "LEFT JOIN h1n1 v2 ON c.Sequencial = v2.CampanhaID " +
                 "LEFT JOIN sarampo v3 ON c.Sequencial = v3.CampanhaID";

    ResultSet resultados = null;
    ArrayList<Campanha> visões = new ArrayList<>();

    try {
        PreparedStatement comando = BD.conexão.prepareStatement(sql);
        resultados = comando.executeQuery();

        while (resultados.next()) {
            int sequencial = resultados.getInt("Sequencial");
            int doses = resultados.getInt("NumeroDoses");
            String tipo = resultados.getString("Tipo");

            String marcaCovid = resultados.getString("Marca");
            boolean reforcoCovid = resultados.getBoolean("Reforço");
            int cepasH1N1 = resultados.getInt("Cepas");
            boolean emergencialH1N1 = resultados.getBoolean("Emergencial");
            int intervaloSarampo = resultados.getInt("Intervalo");
            boolean adjuvanteSarampo = resultados.getBoolean("Adjuvante");

            if (marcaCovid != null) {
                visões.add(new Covid19(sequencial, doses, tipo, marcaCovid, reforcoCovid));
            } else if (cepasH1N1 != 0) {
                visões.add(new H1N1(sequencial, doses, tipo, cepasH1N1, emergencialH1N1));
            } else if (intervaloSarampo != 0) {
                visões.add(new Sarampo(sequencial, doses, tipo, intervaloSarampo, adjuvanteSarampo));
            } 

        }

        resultados.close();
        comando.close();

    } catch (SQLException exceção_sql) {
        exceção_sql.printStackTrace();
    }

    return visões.toArray(new Campanha[visões.size()]);
}


    
    public Campanha getVisão() {return new Campanha(sequencial, numero_de_doses);}
    
    public static int últimoSequencial(){
        String sql = "SELECT MAX(Sequencial) FROM campanhas";
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
    
    public Campanha(int sequencial, int numero_de_doses, String tipo) {
        this.sequencial = sequencial;
        this.numero_de_doses = numero_de_doses;
        this.tipo = tipo;
    }
    
    public Campanha(int sequencial, int numero_de_doses) {
        this.sequencial = sequencial;
        this.numero_de_doses = numero_de_doses;
    }
    
    public int getNumeroDoses(){return numero_de_doses;}
    public int getSequencial(){return sequencial;}
    public String getTipo(){return tipo;}
    @Override
    public String toString(){return "[" + sequencial + "] " + ", " + numero_de_doses + " doses";}
    public void setSequencial(int sequencial){this.sequencial = sequencial;}
    public void setNumeroDoses(int numero_de_doses){this.numero_de_doses = numero_de_doses;}
    public void setTipo(String tipo){this.tipo = tipo;}
    
}
