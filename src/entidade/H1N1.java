package entidade;
       
public class H1N1 extends Campanha {
    private int cepas;
    private boolean emergencial;
    
    public H1N1(int sequencial, int numero_de_doses, String tipo, int cepas, boolean emergencial) {
        super(sequencial, numero_de_doses, tipo);
        this.cepas = cepas;
        this.emergencial = emergencial;
    }
    
    public int getCepas(){return cepas;}
    public boolean getEmergencial(){return emergencial;}
    public void setCepas(int cepas){this.cepas = cepas;}
    public void setEmergencial(boolean emergencial){this.emergencial = emergencial;}
    @Override
    public String toString(){return "[" + sequencial + "] H1N1 " + ", " + numero_de_doses + " doses";}
}
