package dev.matheus.agendamento_clear.Core.UseCase;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.Gateway.AgendamentoGateway;

public class CancelarAgendamentoUseCaseImpl implements CancelarAgendamentoUseCase {

    private final AgendamentoGateway gateway;

    public CancelarAgendamentoUseCaseImpl(AgendamentoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Agendamento execute(Long id) {
        if (gateway.buscarAgendamentoId(id ) == null ){
            throw new IllegalArgumentException("Agendamento não encontrado");
        }
        return gateway.cancelarAgendamento(id);
    }
}
