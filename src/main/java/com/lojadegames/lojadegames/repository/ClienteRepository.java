package com.lojadegames.lojadegames.repository;

import com.lojadegames.lojadegames.model.Cliente;
import com.lojadegames.lojadegames.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public List<Cliente > findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
