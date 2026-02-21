package dev.matheus.agendamento_clear.Core.UseCase;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.Gateway.AgendamentoGateway;
import java.time.LocalDateTime;

public class AtualizarAgendamentoUseCaseImpl implements AtualizarAgendamentoUseCase {

    private final AgendamentoGateway gateway;

    public AtualizarAgendamentoUseCaseImpl(AgendamentoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Agendamento execute(Agendamento agendamento) {
    var existente = gateway.buscarAgendamentoId(agendamento.id());
    if (existente == null){
        throw  new IllegalArgumentException("Agendamento não encontrado");
    }
        return gateway.atualizarAgendamento( new Agendamento(
            existente.id(),
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim(),
                agendamento.status(),
                agendamento.usuario(),
                existente.criadoEm(),
                LocalDateTime.now())
        );
    }
}
