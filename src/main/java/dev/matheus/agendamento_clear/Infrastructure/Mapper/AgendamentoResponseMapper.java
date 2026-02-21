package dev.matheus.agendamento_clear.Infrastructure.Mapper;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Infrastructure.DTO.AgendamentoResponse;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoResponseMapper {

    public AgendamentoResponse toDto(Agendamento agendamento){
        return new AgendamentoResponse(
                agendamento.id(),
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim(),
                agendamento.status(),
                agendamento.usuario(),
                agendamento.criadoEm(),
                agendamento.atualizadoEm()
        );
    }


    public Agendamento toEntity(AgendamentoResponse agendamentoResponse){
        return new Agendamento(
                agendamentoResponse.id(),
                agendamentoResponse.titiulo(),
                agendamentoResponse.descricao(),
                agendamentoResponse.dataInicio(),
                agendamentoResponse.dataFim(),
                agendamentoResponse.status(),
                agendamentoResponse.usuario(),
                agendamentoResponse.criadoEm(),
                agendamentoResponse.atualizadoEm()
        );
    }
}
