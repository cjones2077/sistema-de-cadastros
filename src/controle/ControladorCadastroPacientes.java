package controle;
import interfaces.JanelaCadastroPacientes;
import entidade.Paciente;

public class ControladorCadastroPacientes {
    public ControladorCadastroPacientes(){
        new JanelaCadastroPacientes(this).setVisible(true);
    }
    public String inserirPaciente(Paciente novoPaciente){
        Paciente existente = Paciente.buscarPaciente(novoPaciente.getCpf());
        if (existente == null) return novoPaciente.inserirPaciente(novoPaciente);
        else return "CPF de paciente já cadastrado";
    }
    public String alterarPaciente(Paciente paciente){
        Paciente aux = Paciente.buscarPaciente(paciente.getCpf());
        if (aux != null) return Paciente.alterarPaciente(paciente);
        else return "Paciente não encontrado";
    }
    
    public String removerPaciente(String cpf){
	Paciente paciente = Paciente.buscarPaciente(cpf);
	if (paciente != null) return Paciente.removerPaciente(cpf);
	else return "CPF de paciente não cadastrado";
    }
}