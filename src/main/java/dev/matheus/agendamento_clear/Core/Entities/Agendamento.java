package dev.matheus.agendamento_clear.Core.Entities;

import dev.matheus.agendamento_clear.Core.Enums.StatusAgendamento;
import java.time.LocalDateTime;

public record Agendamento(
        Long id,
        String titulo,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        StatusAgendamento status,
        String usuario,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
