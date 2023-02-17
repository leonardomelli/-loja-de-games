package com.lojadegames.lojadegames.controller;


import com.lojadegames.lojadegames.model.Cliente;
import com.lojadegames.lojadegames.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository   clienteRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {

        return ResponseEntity.ok(clienteRepository.findAll());


    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cliente>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(clienteRepository.findAllByNomeContainingIgnoreCase(nome));
    }



    @PostMapping
    public ResponseEntity<Cliente> post(@Valid @RequestBody  Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> put(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

}


