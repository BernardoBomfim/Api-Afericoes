package application.afericao;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import application.dto.ApiResponse;


@RestController
@RequestMapping("/afericoes")
public class AfericaoController {
    @Autowired
    private AfericaoService afericaoService;

    @PostMapping
    @Operation(summary = "Criar nova aferição", description = "Insere uma nova aferição a partir dos dados recebidos")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Aferição criada", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    public ResponseEntity<ApiResponse> insert(@RequestBody AfericaoInsertDTO novaAfericao) {
        AfericaoDTO dto = afericaoService.insert(novaAfericao);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(dto, "Aferição criada com sucesso"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter aferição", description = "Retorna a aferição com o id informado")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Aferição encontrada", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Aferição não encontrada", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    public ResponseEntity<ApiResponse> getOne(@PathVariable long id) {
        AfericaoDTO dto = afericaoService.getOne(id);
        return ResponseEntity.ok(ApiResponse.ok(dto));
    }

    @GetMapping
    @Operation(summary = "Listar aferições", description = "Retorna todas as aferições cadastradas")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de aferições", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    public ResponseEntity<ApiResponse> getAll() {
        Iterable<AfericaoDTO> list = afericaoService.getAll();
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aferição", description = "Atualiza a aferição indicada pelo id com os novos dados")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Aferição atualizada", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Aferição não encontrada", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    public ResponseEntity<ApiResponse> update(@PathVariable long id, @RequestBody AfericaoInsertDTO novosDados) {
        AfericaoDTO dto = afericaoService.update(id, novosDados);
        return ResponseEntity.ok(ApiResponse.ok(dto, "Aferição atualizada com sucesso"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover aferição", description = "Remove a aferição pelo id")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Aferição removida", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Aferição não encontrada", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    public ResponseEntity<ApiResponse> remove(@PathVariable long id) {
        afericaoService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok(null, "Aferição removida"));
    }
}