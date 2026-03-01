package br.com.fiap.postech.techchallenge2.usuario.core.exception;

public class TipoUsuarioNaoEncontradoException extends RuntimeException {
    public TipoUsuarioNaoEncontradoException(Long id) {
        super("TipoUsuario não encontrado com id: " + id);
    }
}
