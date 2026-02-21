package dev.matheus.agendamento_clear.Core.UseCase;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;

public interface ConcluirAgendamentoUseCase {

    Agendamento execute(Long id);
}
