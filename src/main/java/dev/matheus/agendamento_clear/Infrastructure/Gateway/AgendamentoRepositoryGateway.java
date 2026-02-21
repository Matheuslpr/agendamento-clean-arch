package dev.matheus.agendamento_clear.Infrastructure.Gateway;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.Enums.StatusAgendamento;
import dev.matheus.agendamento_clear.Core.Gateway.AgendamentoGateway;
import dev.matheus.agendamento_clear.Infrastructure.Mapper.AgendamentoEntityMapper;
import dev.matheus.agendamento_clear.Infrastructure.Persistence.AgendamentoEntity;
import dev.matheus.agendamento_clear.Infrastructure.Persistence.AgendamentoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoRepositoryGateway implements AgendamentoGateway {

    private final AgendamentoRepository repository;
    private final AgendamentoEntityMapper entityMapper;

    public AgendamentoRepositoryGateway(AgendamentoRepository repository, AgendamentoEntityMapper entityMapper) {
        this.repository = repository;
        this.entityMapper = entityMapper;
    }


    @Override
    public Agendamento criarAgendamento(Agendamento agendamento) {
        validarIntervalor(agendamento.dataInicio(), agendamento.dataFim());
        checkConflito(agendamento.usuario(),agendamento.dataInicio(), agendamento.dataFim(), null);

        AgendamentoEntity agendamentoEntity = repository.save(entityMapper.toEntity(agendamento));
        return entityMapper.toDomain(agendamentoEntity);
    }


    @Override
    public Agendamento buscarAgendamentoId(Long id) {
        return repository.findById(id)
                .map(entityMapper::toDomain)
                .orElse(null);
    }


    @Override
    public Agendamento atualizarAgendamento(Agendamento agendamento) {
        return entityMapper.toDomain(repository.save(entityMapper.toEntity(agendamento)));
    }


    @Override
    public Agendamento cancelarAgendamento(Long id) {
        var existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        existente.setStatus(StatusAgendamento.CANCELADO);
        existente = repository.save(existente);
        existente.setAtualizadoEm(LocalDateTime.now());
        return entityMapper.toDomain(repository.save(existente));
    }


    @Override
    public Agendamento concluirAgendamento(Long id) {
        var existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        existente.setStatus(StatusAgendamento.CONCLUIDO);
        existente = repository.save(existente);
        existente.setAtualizadoEm(LocalDateTime.now());
        return entityMapper.toDomain(repository.save(existente));
    }



    private void validarIntervalor(LocalDateTime inicio, LocalDateTime fim){
        if(inicio == null || fim == null || !inicio.isBefore(fim)){
            throw new IllegalArgumentException("Intervalo inválido");
        }
    }


    private void checkConflito(String usuario, LocalDateTime inicio, LocalDateTime fim, Long ignoreId){
        boolean conflito = repository.existsConflito(usuario, inicio, fim, ignoreId);
        if (conflito){
            throw new IllegalArgumentException("Conflito na agenda: já existe um agendamento nesse periodo");

        }
    }

}
