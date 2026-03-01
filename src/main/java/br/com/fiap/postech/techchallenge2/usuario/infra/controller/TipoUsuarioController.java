package br.com.fiap.postech.techchallenge2.usuario.infra.controller;

import br.com.fiap.postech.techchallenge2.usuario.core.domain.TipoUsuario;
import br.com.fiap.postech.techchallenge2.usuario.core.dto.TipoUsuarioRequestDTO;
import br.com.fiap.postech.techchallenge2.usuario.core.dto.TipoUsuarioResponseDTO;
import br.com.fiap.postech.techchallenge2.usuario.core.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tipos-usuario")
@Tag(name = "TipoUsuario", description = "Gerenciamento de tipos de usuário")
public class TipoUsuarioController {

    private final CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;
    private final BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase;
    private final ListarTiposUsuarioUseCase listarTiposUsuarioUseCase;
    private final AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;
    private final DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase;

    public TipoUsuarioController(
            CriarTipoUsuarioUseCase criarTipoUsuarioUseCase,
            BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase,
            ListarTiposUsuarioUseCase listarTiposUsuarioUseCase,
            AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase,
            DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase) {
        this.criarTipoUsuarioUseCase = criarTipoUsuarioUseCase;
        this.buscarTipoUsuarioPorIdUseCase = buscarTipoUsuarioPorIdUseCase;
        this.listarTiposUsuarioUseCase = listarTiposUsuarioUseCase;
        this.atualizarTipoUsuarioUseCase = atualizarTipoUsuarioUseCase;
        this.deletarTipoUsuarioUseCase = deletarTipoUsuarioUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar tipo de usuário")
    public TipoUsuarioResponseDTO criar(@Valid @RequestBody TipoUsuarioRequestDTO dto) {
        TipoUsuario tipoUsuario = new TipoUsuario(null, dto.nome());
        return TipoUsuarioResponseDTO.from(criarTipoUsuarioUseCase.executar(tipoUsuario));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tipo de usuário por ID")
    public TipoUsuarioResponseDTO buscarPorId(@PathVariable Long id) {
        return TipoUsuarioResponseDTO.from(buscarTipoUsuarioPorIdUseCase.executar(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os tipos de usuário")
    public List<TipoUsuarioResponseDTO> listarTodos() {
        return listarTiposUsuarioUseCase.executar().stream()
                .map(TipoUsuarioResponseDTO::from)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tipo de usuário")
    public TipoUsuarioResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody TipoUsuarioRequestDTO dto) {
        TipoUsuario tipoUsuario = new TipoUsuario(id, dto.nome());
        return TipoUsuarioResponseDTO.from(atualizarTipoUsuarioUseCase.executar(id, tipoUsuario));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar tipo de usuário")
    public void deletar(@PathVariable Long id) {
        deletarTipoUsuarioUseCase.executar(id);
    }
}
