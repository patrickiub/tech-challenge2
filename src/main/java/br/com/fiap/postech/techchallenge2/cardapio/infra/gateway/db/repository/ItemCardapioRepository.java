package br.com.fiap.postech.techchallenge2.cardapio.infra.gateway.db.repository;

import br.com.fiap.postech.techchallenge2.cardapio.infra.gateway.db.entity.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long> {
    List<ItemCardapioEntity> findByRestauranteId(Long restauranteId);
}
