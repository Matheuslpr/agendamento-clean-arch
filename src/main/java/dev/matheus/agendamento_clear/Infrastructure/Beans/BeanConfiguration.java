package dev.matheus.agendamento_clear.Infrastructure.Beans;

import dev.matheus.agendamento_clear.Core.Gateway.AgendamentoGateway;
import dev.matheus.agendamento_clear.Core.UseCase.*;
import dev.matheus.agendamento_clear.Infrastructure.Gateway.AgendamentoRepositoryGateway;
import dev.matheus.agendamento_clear.Infrastructure.Mapper.AgendamentoEntityMapper;
import dev.matheus.agendamento_clear.Infrastructure.Persistence.AgendamentoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CriarAgendamentoUseCase criarAgendamentoUseCase(AgendamentoGateway agendamentoGateway){

        return new CriarAgendamentoUseCaseImpl(agendamentoGateway);
    }


    @Bean
    public BuscarAgendamentoIdUseCase buscarAgendamentoIdUseCase(AgendamentoGateway agendamentoGateway){

        return new BuscarAgendamentoUseCaseImpl(agendamentoGateway);
    }


    @Bean
    public CancelarAgendamentoUseCase cancelarAgendamentoUseCase(AgendamentoGateway agendamentoGateway){

        return new CancelarAgendamentoUseCaseImpl(agendamentoGateway);
    }


    @Bean
    public ConcluirAgendamentoUseCase concluirAgendamentoUseCase(AgendamentoGateway agendamentoGateway){

        return new ConcluirAgendamentoUseCaseImpl(agendamentoGateway);
    }


    @Bean
    public AtualizarAgendamentoUseCase atualizarAgendamentoUseCase(AgendamentoGateway agendamentoGateway){

        return new AtualizarAgendamentoUseCaseImpl(agendamentoGateway);
    }


    @Bean
    public AgendamentoGateway agendamentoGateway(
            AgendamentoRepository repository,
            AgendamentoEntityMapper entityMapper
    ){
        return new AgendamentoRepositoryGateway(repository, entityMapper);
    }
}
