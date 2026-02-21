package dev.matheus.agendamento_clear.Core.UseCase;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.Gateway.AgendamentoGateway;

public class CriarAgendamentoUseCaseImpl implements CriarAgendamentoUseCase {

    private final AgendamentoGateway gateway;

    public CriarAgendamentoUseCaseImpl(AgendamentoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Agendamento execute(Agendamento agendamento) {
        return gateway.criarAgendamento(agendamento);
    }
}
