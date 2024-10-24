package controle;
import entidade.Agendamento;

public class ControladorCadastroAgendamentos {
    
    public String inserirAgendamento(Agendamento novoAgendamento){
        if (!novoAgendamento.existeAgendamento(novoAgendamento.getPaciente().getCpf(), novoAgendamento.getCampanha().getSequencial())) 
            return novoAgendamento.inserirAgendamento(novoAgendamento);
       else return "Agendamento já existe";
    }
    
    public String alterarAgendamento (Agendamento agendamento_informado) {
        Agendamento agendamentos_cadastrados = Agendamento.buscarAgendamento(agendamento_informado.getSequencial());
        if ((agendamento_informado.getPaciente().getCpf().equals(agendamentos_cadastrados.getPaciente().getCpf()))
            && (agendamento_informado.getCampanha().getSequencial()
                == agendamentos_cadastrados.getCampanha().getSequencial())){
        return Agendamento.alterarAgendamento(agendamento_informado);
        } else return "Alteração não permitida : chave do paciente e/ou do campanha foram alteradas";
    }
    public String removerAgendamento(int sequencial){
	if (Agendamento.existeAgendamento(sequencial)) return Agendamento.removerAgendamento(sequencial);
	else return "Sequencial não corresponde a nenhuma reserva cadastrada";
    }
}
