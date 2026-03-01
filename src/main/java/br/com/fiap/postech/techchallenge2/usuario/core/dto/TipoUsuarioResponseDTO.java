package br.com.fiap.postech.techchallenge2.usuario.core.dto;

import br.com.fiap.postech.techchallenge2.usuario.core.domain.TipoUsuario;

public record TipoUsuarioResponseDTO(Long id, String nome) {

    public static TipoUsuarioResponseDTO from(TipoUsuario tipoUsuario) {
        return new TipoUsuarioResponseDTO(tipoUsuario.getId(), tipoUsuario.getNome());
    }
}
