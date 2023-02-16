package com.lojadegames.lojadegames.controller;

import com.lojadegames.lojadegames.model.Jogo;
import com.lojadegames.lojadegames.repository.ConsoleRepository;
import com.lojadegames.lojadegames.repository.JogoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogo")
@CrossOrigin (origins = "*", allowedHeaders = "*")

public class JogoController {


    @Autowired
    JogoRepository jogoRepository;

    @Autowired
    ConsoleRepository consoleRepository;

    @GetMapping
    public ResponseEntity<List<Jogo>> getAll(){

        return ResponseEntity.ok(jogoRepository.findAll());


    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> getById(@PathVariable Long id) {
        return jogoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Jogo>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(   jogoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Jogo> post(@Valid @RequestBody Jogo jogo) {
            if(consoleRepository.existsById(jogo.getConsole().getId()))
                return ResponseEntity.status(HttpStatus.CREATED)
                    .body(jogoRepository.save(jogo));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<Jogo> put(@Valid @RequestBody Jogo jogo) {
        if (jogoRepository.existsById(jogo.getId())) {
            if (consoleRepository.existsById(jogo.getConsole().getId())) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(jogoRepository.save(jogo));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        Optional<Jogo> jogo = jogoRepository.findById(id);

        if (jogo.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        jogoRepository.deleteById(id);
    }


}










