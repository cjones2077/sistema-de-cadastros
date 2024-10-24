package controle;
import interfaces.JanelaCadastroCampanha;
import entidade.Campanha;

public class ControladorCadastroCampanha {
    public ControladorCadastroCampanha(){
        new JanelaCadastroCampanha(this).setVisible(true);
    }
    public String inserirCampanha(Campanha novaCampanha){
        if (!novaCampanha.existeCampanhaIgual(novaCampanha)) return novaCampanha.inserirCampanha(novaCampanha);
       else return "Já existe uma campanha com os mesmos atributos";
    }
    
    public String alterarCampanha(Campanha campanha){
        Campanha aux = Campanha.buscarCampanha(campanha.getSequencial());
        if (aux != null) return Campanha.alterarCampanha(campanha);
        else return "Codigo não encontrado";
    }
    
    public String removerCampanha(Campanha campanha){
	Campanha aux = Campanha.buscarCampanha(campanha.getSequencial());
	if (aux != null) return Campanha.removerCampanha(aux) ;
	else return "Campanha não cadastrada";
    }
}
    

