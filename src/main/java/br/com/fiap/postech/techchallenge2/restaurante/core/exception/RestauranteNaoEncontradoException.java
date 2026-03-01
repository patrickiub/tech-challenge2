package br.com.fiap.postech.techchallenge2.restaurante.core.exception;

public class RestauranteNaoEncontradoException extends RuntimeException {
    public RestauranteNaoEncontradoException(Long id) {
        super("Restaurante não encontrado com id: " + id);
    }
}
