package com.lojadegames.lojadegames.repository;

import com.lojadegames.lojadegames.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

    public List<Jogo> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
