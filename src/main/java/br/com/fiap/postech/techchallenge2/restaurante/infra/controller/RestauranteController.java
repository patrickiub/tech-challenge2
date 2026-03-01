package br.com.fiap.postech.techchallenge2.restaurante.infra.controller;

import br.com.fiap.postech.techchallenge2.restaurante.core.domain.Restaurante;
import br.com.fiap.postech.techchallenge2.restaurante.core.dto.RestauranteRequestDTO;
import br.com.fiap.postech.techchallenge2.restaurante.core.dto.RestauranteResponseDTO;
import br.com.fiap.postech.techchallenge2.restaurante.core.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/restaurantes")
@Tag(name = "Restaurante", description = "Gerenciamento de restaurantes")
public class RestauranteController {

    private final CriarRestauranteUseCase criarRestauranteUseCase;
    private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;
    private final ListarRestaurantesUseCase listarRestaurantesUseCase;
    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    private final DeletarRestauranteUseCase deletarRestauranteUseCase;

    public RestauranteController(
            CriarRestauranteUseCase criarRestauranteUseCase,
            BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase,
            ListarRestaurantesUseCase listarRestaurantesUseCase,
            AtualizarRestauranteUseCase atualizarRestauranteUseCase,
            DeletarRestauranteUseCase deletarRestauranteUseCase) {
        this.criarRestauranteUseCase = criarRestauranteUseCase;
        this.buscarRestaurantePorIdUseCase = buscarRestaurantePorIdUseCase;
        this.listarRestaurantesUseCase = listarRestaurantesUseCase;
        this.atualizarRestauranteUseCase = atualizarRestauranteUseCase;
        this.deletarRestauranteUseCase = deletarRestauranteUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar restaurante")
    public RestauranteResponseDTO criar(@Valid @RequestBody RestauranteRequestDTO dto) {
        Restaurante restaurante = new Restaurante(null, dto.nome(), dto.tipoCozinha(),
                dto.endereco(), dto.horarioFuncionamento(), dto.donoRestauranteId());
        return RestauranteResponseDTO.from(criarRestauranteUseCase.executar(restaurante));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar restaurante por ID")
    public RestauranteResponseDTO buscarPorId(@PathVariable Long id) {
        return RestauranteResponseDTO.from(buscarRestaurantePorIdUseCase.executar(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os restaurantes")
    public List<RestauranteResponseDTO> listarTodos() {
        return listarRestaurantesUseCase.executar().stream()
                .map(RestauranteResponseDTO::from)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar restaurante")
    public RestauranteResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody RestauranteRequestDTO dto) {
        Restaurante restaurante = new Restaurante(id, dto.nome(), dto.tipoCozinha(),
                dto.endereco(), dto.horarioFuncionamento(), dto.donoRestauranteId());
        return RestauranteResponseDTO.from(atualizarRestauranteUseCase.executar(id, restaurante));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar restaurante")
    public void deletar(@PathVariable Long id) {
        deletarRestauranteUseCase.executar(id);
    }
}
