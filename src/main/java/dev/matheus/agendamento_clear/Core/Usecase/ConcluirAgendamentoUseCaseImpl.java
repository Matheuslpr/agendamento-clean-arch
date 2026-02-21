package dev.matheus.agendamento_clear.Core.UseCase;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.Gateway.AgendamentoGateway;

public class ConcluirAgendamentoUseCaseImpl implements ConcluirAgendamentoUseCase {

   private final AgendamentoGateway gateway;

    public ConcluirAgendamentoUseCaseImpl(AgendamentoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Agendamento execute(Long id) {
        if (gateway.buscarAgendamentoId(id ) == null ){
            throw new IllegalArgumentException("Agendamento não encontrado");
        }
        return gateway.concluirAgendamento(id);
    }
}
