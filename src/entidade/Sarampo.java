package entidade;
       
public class Sarampo extends Campanha {
    private int intervalo;
    private boolean adjuvante;
    
    public Sarampo(int sequencial, int numero_de_doses, String tipo, int intervalo, boolean adjuvante) {
        super(sequencial, numero_de_doses, tipo);
        this.intervalo = intervalo;
        this.adjuvante = adjuvante;
    }
    
    public int getIntervalo(){return intervalo;}
    public boolean getAdjuvante(){return adjuvante;}
    public void setIntervalo(int intervalo){this.intervalo = intervalo;}
    public void setAdjuvante(boolean adjuvante){this.adjuvante = adjuvante;}
    @Override
    public String toString(){return "[" + sequencial + "] Sarampo " + ", " + numero_de_doses + " doses";}
    
}
