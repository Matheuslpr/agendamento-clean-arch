package dev.matheus.agendamento_clear.Infrastructure.Mapper;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.Enums.StatusAgendamento;
import dev.matheus.agendamento_clear.Infrastructure.DTO.AgendamentoUpdateRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoUpdadeRequestMapper {

    public AgendamentoUpdateRequest toDto(Agendamento agendamento){
        return new AgendamentoUpdateRequest(
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim()
        );
    }


    public Agendamento merge(Agendamento agendamentoExistente, AgendamentoUpdateRequest request){
        return  new Agendamento(
                agendamentoExistente.id(),
                request.titulo() != null ? request.titulo() : agendamentoExistente.titulo(),
                request.descricao() != null ? request.descricao() : agendamentoExistente.descricao(),
                request.dataInicio() != null ? request.dataInicio() : agendamentoExistente.dataInicio(),
                request.dataFim() != null ? request.dataFim() : agendamentoExistente.dataFim(),
                agendamentoExistente.status(),
                agendamentoExistente.usuario(),
                agendamentoExistente.criadoEm(),
                LocalDateTime.now()
        );

    }


    public Agendamento toEntity(AgendamentoUpdateRequest agendamentoUpdateRequest){
        return new Agendamento(
                null,
                    agendamentoUpdateRequest.titulo(),
                    agendamentoUpdateRequest.descricao(),
                    agendamentoUpdateRequest.dataInicio(),
                    agendamentoUpdateRequest.dataFim(),
                    StatusAgendamento.AGENDADO,
                    null,
                    LocalDateTime.now(),
                    LocalDateTime.now()
        );
    }

}
