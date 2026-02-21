package dev.matheus.agendamento_clear.Infrastructure.Mapper;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.Enums.StatusAgendamento;
import dev.matheus.agendamento_clear.Infrastructure.DTO.AgendamentoCreateRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoCreateMapper {

    public AgendamentoCreateRequest toDto(Agendamento agendamento){
        return new AgendamentoCreateRequest(
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim(),
                agendamento.usuario()
        );
    }


    public Agendamento toEntity(AgendamentoCreateRequest agendametoCreateRequest){
        return new Agendamento(
                null,
                agendametoCreateRequest.titulo(),
                agendametoCreateRequest.descricao(),
                agendametoCreateRequest.dataInicio(),
                agendametoCreateRequest.dataFim(),
                StatusAgendamento.AGENDADO,
                agendametoCreateRequest.usuario(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
