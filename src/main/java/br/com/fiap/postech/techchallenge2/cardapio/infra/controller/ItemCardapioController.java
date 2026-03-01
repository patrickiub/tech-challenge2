package br.com.fiap.postech.techchallenge2.cardapio.infra.controller;

import br.com.fiap.postech.techchallenge2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.postech.techchallenge2.cardapio.core.dto.ItemCardapioRequestDTO;
import br.com.fiap.postech.techchallenge2.cardapio.core.dto.ItemCardapioResponseDTO;
import br.com.fiap.postech.techchallenge2.cardapio.core.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/itens-cardapio")
@Tag(name = "ItemCardapio", description = "Gerenciamento de itens do cardápio")
public class ItemCardapioController {

    private final CriarItemCardapioUseCase criarItemCardapioUseCase;
    private final BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;
    private final ListarItensCardapioUseCase listarItensCardapioUseCase;
    private final ListarItensCardapioPorRestauranteUseCase listarItensCardapioPorRestauranteUseCase;
    private final AtualizarItemCardapioUseCase atualizarItemCardapioUseCase;
    private final DeletarItemCardapioUseCase deletarItemCardapioUseCase;

    public ItemCardapioController(
            CriarItemCardapioUseCase criarItemCardapioUseCase,
            BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase,
            ListarItensCardapioUseCase listarItensCardapioUseCase,
            ListarItensCardapioPorRestauranteUseCase listarItensCardapioPorRestauranteUseCase,
            AtualizarItemCardapioUseCase atualizarItemCardapioUseCase,
            DeletarItemCardapioUseCase deletarItemCardapioUseCase) {
        this.criarItemCardapioUseCase = criarItemCardapioUseCase;
        this.buscarItemCardapioPorIdUseCase = buscarItemCardapioPorIdUseCase;
        this.listarItensCardapioUseCase = listarItensCardapioUseCase;
        this.listarItensCardapioPorRestauranteUseCase = listarItensCardapioPorRestauranteUseCase;
        this.atualizarItemCardapioUseCase = atualizarItemCardapioUseCase;
        this.deletarItemCardapioUseCase = deletarItemCardapioUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar item de cardápio")
    public ItemCardapioResponseDTO criar(@Valid @RequestBody ItemCardapioRequestDTO dto) {
        ItemCardapio item = new ItemCardapio(null, dto.nome(), dto.descricao(),
                dto.preco(), dto.categoria(), dto.restauranteId());
        return ItemCardapioResponseDTO.from(criarItemCardapioUseCase.executar(item));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar item de cardápio por ID")
    public ItemCardapioResponseDTO buscarPorId(@PathVariable Long id) {
        return ItemCardapioResponseDTO.from(buscarItemCardapioPorIdUseCase.executar(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os itens de cardápio")
    public List<ItemCardapioResponseDTO> listarTodos() {
        return listarItensCardapioUseCase.executar().stream()
                .map(ItemCardapioResponseDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/restaurante/{restauranteId}")
    @Operation(summary = "Listar itens de cardápio por restaurante")
    public List<ItemCardapioResponseDTO> listarPorRestaurante(@PathVariable Long restauranteId) {
        return listarItensCardapioPorRestauranteUseCase.executar(restauranteId).stream()
                .map(ItemCardapioResponseDTO::from)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar item de cardápio")
    public ItemCardapioResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ItemCardapioRequestDTO dto) {
        ItemCardapio item = new ItemCardapio(id, dto.nome(), dto.descricao(),
                dto.preco(), dto.categoria(), dto.restauranteId());
        return ItemCardapioResponseDTO.from(atualizarItemCardapioUseCase.executar(id, item));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar item de cardápio")
    public void deletar(@PathVariable Long id) {
        deletarItemCardapioUseCase.executar(id);
    }
}
