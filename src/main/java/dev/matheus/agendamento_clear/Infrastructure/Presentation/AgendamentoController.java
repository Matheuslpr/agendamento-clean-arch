package dev.matheus.agendamento_clear.Infrastructure.Presentation;

import dev.matheus.agendamento_clear.Core.Entities.Agendamento;
import dev.matheus.agendamento_clear.Core.UseCase.*;
import dev.matheus.agendamento_clear.Infrastructure.DTO.AgendamentoCreateRequest;
import dev.matheus.agendamento_clear.Infrastructure.DTO.AgendamentoResponse;
import dev.matheus.agendamento_clear.Infrastructure.DTO.AgendamentoUpdateRequest;
import dev.matheus.agendamento_clear.Infrastructure.Mapper.AgendamentoCreateMapper;
import dev.matheus.agendamento_clear.Infrastructure.Mapper.AgendamentoResponseMapper;
import dev.matheus.agendamento_clear.Infrastructure.Mapper.AgendamentoUpdateRequestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Endpoints para gerenciamento de agendamentos")
public class AgendamentoController {

    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final BuscarAgendamentoIdUseCase buscarAgendamentoIdUseCase;
    private final AtualizarAgendamentoUseCase atualizarAgendamentoUseCase;
    private final CancelarAgendamentoUseCase cancelarAgendamentoUseCase;
    private final ConcluirAgendamentoUseCase concluirAgendamentoUseCase;
    private final AgendamentoCreateMapper agendamentoCreateMapper;
    private final AgendamentoResponseMapper agendamentoResponseMapper;
    private final AgendamentoUpdateRequestMapper agendamentoUpdadeRequestMapper;

    public AgendamentoController(CriarAgendamentoUseCase criarAgendamentoUseCase, BuscarAgendamentoIdUseCase buscarAgendamentoIdUseCase, AtualizarAgendamentoUseCase atualizarAgendamentoUseCase, CancelarAgendamentoUseCase cancelarAgendamentoUseCase, ConcluirAgendamentoUseCase concluirAgendamentoUseCase, AgendamentoCreateMapper agendamentoCreateMapper, AgendamentoResponseMapper agendamentoResponseMapper, AgendamentoUpdateRequestMapper agendamentoUpdadeRequestMapper) {
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
    @Operation(summary = "Criar um novo agendamento",description = "Endpoint para criar um novo agendamento com os dados fornecidos no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento cadastrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar agendamento")
    })
    public ResponseEntity<Map<String, Object>> criarAgendamento(@RequestBody AgendamentoCreateRequest request){
        Agendamento criado = criarAgendamentoUseCase.execute(agendamentoCreateMapper.toEntity(request));

        Map<String, Object> response = new HashMap<>();
        response.put("mensagem" , "Agendamento criado com sucesso");
        response.put("Agendamento" , agendamentoResponseMapper.toDto(criado));

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar um agendamento por ID", description = "Endpoint para buscar um agendamento específico usando seu ID ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao retorna agendamento")
    })
    public ResponseEntity<AgendamentoResponse> buscarId(@PathVariable("id") Long id){
        return ResponseEntity.ok(agendamentoResponseMapper.toDto(buscarAgendamentoIdUseCase.execute(id)));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um agendamento existente", description = "Endpoint para atualizar um agendamento existente usando seu ID e os dados fornecidos no corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar agendamento")
    })
    public ResponseEntity<Map<String, Object>> atualizarAgendamento(@PathVariable("id") Long id, @RequestBody AgendamentoUpdateRequest request){
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
    @Operation(summary = "Cancelar agendamento", description = "Endpoint para cancelar um agendamento existente usando seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento cancelado"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao cancelar agendamento")
    })
    public  ResponseEntity<Map<String, Object>> cancelarAgendamento(@PathVariable("id") Long id){
        Agendamento existente = buscarAgendamentoIdUseCase.execute(id);
        if(existente == null){
            return ResponseEntity.notFound().build();
        }
        Agendamento cancelado = cancelarAgendamentoUseCase.execute(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento cancelado com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toDto(cancelado));
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}/concluir")
    @Operation(summary = "Concluir agendamento", description = "Endpoint para concluir um agendamento existente usando seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento concluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao concluir agendamento")
    })
    public  ResponseEntity<Map<String, Object>> concluirAgendamento(@PathVariable("id") Long id) {
        Agendamento existente = buscarAgendamentoIdUseCase.execute(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        Agendamento concluir = concluirAgendamentoUseCase.execute(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento concluido com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toDto(concluir));
        return ResponseEntity.ok(response);

    }

}
