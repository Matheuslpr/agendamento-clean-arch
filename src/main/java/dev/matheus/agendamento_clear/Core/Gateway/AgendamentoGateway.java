package dev.matheus.agendamento_clear.Core.Gateway;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;

public interface AgendamentoGateway {

    Agendamento criarAgendamento(Agendamento agendamento);
    Agendamento buscarAgendamentoId(Long id);
    Agendamento atualizarAgendamento(Agendamento agendamento);
    Agendamento cancelarAgendamento(Long id);
    Agendamento concluirAgendamento(Long id);
}
