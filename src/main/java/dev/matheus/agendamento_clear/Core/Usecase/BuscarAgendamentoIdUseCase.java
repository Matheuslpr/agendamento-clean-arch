package dev.matheus.agendamento_clear.Core.UseCase;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;

public interface BuscarAgendamentoIdUseCase {

    Agendamento execute(Long id);

}
