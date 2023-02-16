package com.lojadegames.lojadegames.controller;

import com.lojadegames.lojadegames.model.Console;
import com.lojadegames.lojadegames.repository.ConsoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/console")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ConsoleController {

    @Autowired
    private ConsoleRepository consoleRepository;

    @GetMapping
    public ResponseEntity<List<Console>> getAll() {

        return ResponseEntity.ok(consoleRepository.findAll());


    }

    @GetMapping("/{id}")
    public ResponseEntity<Console> getById(@PathVariable Long id) {
        return consoleRepository.findById(id)
              .map(resposta -> ResponseEntity.ok(resposta))
              .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Console>> getByNome(@PathVariable String nome ) {
        return ResponseEntity.ok(consoleRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Console> post(@Valid @RequestBody Console console) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(consoleRepository.save(console));

    }

    @PutMapping
    public ResponseEntity<Console> put(@Valid @RequestBody Console console) {
        return consoleRepository.findById(console.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(consoleRepository.save(console)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        Optional<Console> console = consoleRepository.findById(id);

        if (console.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        consoleRepository.deleteById(id);
    }


}


