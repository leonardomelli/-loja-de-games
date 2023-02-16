package com.lojadegames.lojadegames.repository;

import com.lojadegames.lojadegames.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ConsoleRepository extends JpaRepository<Console, Long> {
    public List<Console>  findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
