package entidade;
       
public class Covid19 extends Campanha {
    private String marca;
    private boolean reforço;
    
    public Covid19(int sequencial, int numero_de_doses, String tipo, String marca, boolean reforço) {
        super(sequencial, numero_de_doses, tipo);
        this.marca = marca;
        this.reforço = reforço;
    }
    
    public String getMarca(){return marca;}
    public boolean getReforço(){return reforço;}
    public void setMarca(String marca){this.marca = marca;}
    public void setReforço(boolean reforço){this.reforço = reforço;}
    @Override
    public String toString(){return "[" + sequencial + "] Covid19, " + numero_de_doses + " doses";}
}
