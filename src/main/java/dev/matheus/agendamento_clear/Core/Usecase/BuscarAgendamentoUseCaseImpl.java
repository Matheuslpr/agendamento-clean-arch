package dev.matheus.agendamento_clear.Core.UseCase;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.Gateway.AgendamentoGateway;

public class BuscarAgendamentoUseCaseImpl implements BuscarAgendamentoIdUseCase {

   private final AgendamentoGateway gateway;

    public BuscarAgendamentoUseCaseImpl(AgendamentoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Agendamento execute(Long id) {
        var agendamento = gateway.buscarAgendamentoId(id);
        if (agendamento == null){
            throw  new IllegalArgumentException("Agendamento não encontrado");
        }

        return null;
    }
}
