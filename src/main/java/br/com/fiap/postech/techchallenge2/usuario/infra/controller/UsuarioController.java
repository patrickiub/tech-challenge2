package br.com.fiap.postech.techchallenge2.usuario.infra.controller;

import br.com.fiap.postech.techchallenge2.usuario.core.domain.TipoUsuario;
import br.com.fiap.postech.techchallenge2.usuario.core.domain.Usuario;
import br.com.fiap.postech.techchallenge2.usuario.core.dto.UsuarioRequestDTO;
import br.com.fiap.postech.techchallenge2.usuario.core.dto.UsuarioResponseDTO;
import br.com.fiap.postech.techchallenge2.usuario.core.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuario", description = "Gerenciamento de usuários")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;

    public UsuarioController(
            CriarUsuarioUseCase criarUsuarioUseCase,
            BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
            ListarUsuariosUseCase listarUsuariosUseCase,
            AtualizarUsuarioUseCase atualizarUsuarioUseCase,
            DeletarUsuarioUseCase deletarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar usuário")
    public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario(null, dto.nome(), dto.email(), dto.senha(),
                new TipoUsuario(dto.tipoUsuarioId(), null));
        return UsuarioResponseDTO.from(criarUsuarioUseCase.executar(usuario));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id) {
        return UsuarioResponseDTO.from(buscarUsuarioPorIdUseCase.executar(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    public List<UsuarioResponseDTO> listarTodos() {
        return listarUsuariosUseCase.executar().stream()
                .map(UsuarioResponseDTO::from)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário")
    public UsuarioResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario(id, dto.nome(), dto.email(), dto.senha(),
                new TipoUsuario(dto.tipoUsuarioId(), null));
        return UsuarioResponseDTO.from(atualizarUsuarioUseCase.executar(id, usuario));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar usuário")
    public void deletar(@PathVariable Long id) {
        deletarUsuarioUseCase.executar(id);
    }
}
