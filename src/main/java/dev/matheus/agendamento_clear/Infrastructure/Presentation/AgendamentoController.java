package dev.matheus.agendamento_clear.Infrastructure.Presentation;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.UseCase.*;
import dev.matheus.agendamento_clear.Infrastructure.DTO.AgendamentoCreateRequest;
import dev.matheus.agendamento_clear.Infrastructure.DTO.AgendamentoResponse;
import dev.matheus.agendamento_clear.Infrastructure.DTO.AgendamentoUpdateRequest;
import dev.matheus.agendamento_clear.Infrastructure.Mapper.AgendamentoCreateMapper;
import dev.matheus.agendamento_clear.Infrastructure.Mapper.AgendamentoResponseMapper;
import dev.matheus.agendamento_clear.Infrastructure.Mapper.AgendamentoUpdadeRequestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final BuscarAgendamentoIdUseCase buscarAgendamentoIdUseCase;
    private final AtualizarAgendamentoUseCase atualizarAgendamentoUseCase;
    private final CancelarAgendamentoUseCase cancelarAgendamentoUseCase;
    private final ConcluirAgendamentoUseCase concluirAgendamentoUseCase;
    private final AgendamentoCreateMapper agendamentoCreateMapper;
    private final AgendamentoResponseMapper agendamentoResponseMapper;
    private final AgendamentoUpdadeRequestMapper agendamentoUpdadeRequestMapper;

    public AgendamentoController(CriarAgendamentoUseCase criarAgendamentoUseCase, BuscarAgendamentoIdUseCase buscarAgendamentoIdUseCase, AtualizarAgendamentoUseCase atualizarAgendamentoUseCase, CancelarAgendamentoUseCase cancelarAgendamentoUseCase, ConcluirAgendamentoUseCase concluirAgendamentoUseCase, AgendamentoCreateMapper agendamentoCreateMapper, AgendamentoResponseMapper agendamentoResponseMapper, AgendamentoUpdadeRequestMapper agendamentoUpdadeRequestMapper) {
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.buscarAgendamentoIdUseCase = buscarAgendamentoIdUseCase;
        this.atualizarAgendamentoUseCase = atualizarAgendamentoUseCase;
        this.cancelarAgendamentoUseCase = cancelarAgendamentoUseCase;
        this.concluirAgendamentoUseCase = concluirAgendamentoUseCase;
        this.agendamentoCreateMapper = agendamentoCreateMapper;
        this.agendamentoResponseMapper = agendamentoResponseMapper;
        this.agendamentoUpdadeRequestMapper = agendamentoUpdadeRequestMapper;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> criarAgendamento(@RequestBody AgendamentoCreateRequest request){
        Agendamento criado = criarAgendamentoUseCase.execute(agendamentoCreateMapper.toEntity(request));

        Map<String, Object> response = new HashMap<>();
        response.put("mensagem" , "Agendamento criado com sucesso");
        response.put("Agendamento" , agendamentoResponseMapper.toDto(criado));

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(agendamentoResponseMapper.toDto(buscarAgendamentoIdUseCase.execute(id)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarAgendamento(@PathVariable Long id, @RequestBody AgendamentoUpdateRequest request){
        Agendamento existente = buscarAgendamentoIdUseCase.execute(id);
        if(existente == null){
            return ResponseEntity.notFound().build();
        }
        Agendamento atualizado = atualizarAgendamentoUseCase.execute(agendamentoUpdadeRequestMapper.merge(existente, request));
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem" , "Agendamento criado com sucesso");
        response.put("Agendamento" , agendamentoResponseMapper.toDto(atualizado));

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}/cancelar")
    public  ResponseEntity<Map<String, Object>> cancelarAgendamento(@PathVariable Long id){
        Agendamento existente = buscarAgendamentoIdUseCase.execute(id);
        if(existente == null){
            return ResponseEntity.notFound().build();
        }
        Agendamento cancelado = cancelarAgendamentoUseCase.execute(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toDto(cancelado));
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}/concluir")
    public  ResponseEntity<Map<String, Object>> concluirAgendamento(@PathVariable Long id) {
        Agendamento existente = buscarAgendamentoIdUseCase.execute(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        Agendamento concluir = concluirAgendamentoUseCase.execute(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toDto(concluir));
        return ResponseEntity.ok(response);

    }

}
