package dev.matheus.agendamento_clear.Infrastructure.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    @Query("""
            SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
                FROM AgendamentoEntity a 
                WHERE a.usuario = :usuario
                    AND a.status = dev.matheus.agendamento_clear.Core.Enums.StatusAgendamento.AGENDADO
                    AND(a.dataInicio < :fim AND a.dataFim > :inicio)
                    AND(:ignoreId is Null OR a.id <> :ignoreId)
            
     """)
    boolean existsConflito(@Param("usuario") String usuario,
                           @Param("inicio")LocalDateTime inicio,
                           @Param("fim")LocalDateTime fim,
                           @Param("ignoreId")Long ignoreId);
}
