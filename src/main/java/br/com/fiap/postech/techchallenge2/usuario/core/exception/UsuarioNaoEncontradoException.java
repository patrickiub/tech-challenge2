package br.com.fiap.postech.techchallenge2.usuario.core.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuario não encontrado com id: " + id);
    }
}
