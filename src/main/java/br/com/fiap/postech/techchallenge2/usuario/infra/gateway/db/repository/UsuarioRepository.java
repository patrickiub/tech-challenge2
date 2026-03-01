package br.com.fiap.postech.techchallenge2.usuario.infra.gateway.db.repository;

import br.com.fiap.postech.techchallenge2.usuario.infra.gateway.db.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
