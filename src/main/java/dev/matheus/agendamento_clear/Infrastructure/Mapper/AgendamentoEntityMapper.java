package dev.matheus.agendamento_clear.Infrastructure.Mapper;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.Enums.StatusAgendamento;
import dev.matheus.agendamento_clear.Infrastructure.Persistence.AgendamentoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoEntityMapper {

    public AgendamentoEntity toEntity(Agendamento agendamento){
        return AgendamentoEntity.builder()
                .titulo(agendamento.titulo())
                .descricao(agendamento.descricao())
                .dataInicio(agendamento.dataInicio())
                .dataFim(agendamento.dataFim())
                .usuario(agendamento.usuario())
                .status(StatusAgendamento.AGENDADO)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();
    }


    public Agendamento toDomain(AgendamentoEntity agendamentoEntity){
        return new Agendamento(
                agendamentoEntity.getId(),
                agendamentoEntity.getTitulo(),
                agendamentoEntity.getDescricao(),
                agendamentoEntity.getDataInicio(),
                agendamentoEntity.getDataFim(),
                agendamentoEntity.getStatus(),
                agendamentoEntity.getUsuario(),
                agendamentoEntity.getCriadoEm(),
                agendamentoEntity.getAtualizadoEm()
        );
    }
}
