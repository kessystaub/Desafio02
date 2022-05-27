/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.interesseservice.controller;

import br.com.saks.interesseservice.model.Interesse;
import br.com.saks.interesseservice.model.InteresseIdentity;
import br.com.saks.interesseservice.repository.InteresseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 6962246
 */
@RestController
@RequestMapping("/interesses")
public class InteresseController {
    
    //Criar um Get para quando listar o cliente, trazer todos os imóveis de interesse
    //Criar um Get para quando listar o imóvel, trazer todos os clientes interessados.
    
    
    @Autowired
    private InteresseRepository InteresseRepository;
    
    @GetMapping
    public List<Interesse> listarTodos() {
        return InteresseRepository.findAll();
    }   
    
    @GetMapping(value = "/{idImovel}/{idCliente}")
    public Optional<Interesse> listarPeloImovelCliente(@PathVariable Long idImovel, @PathVariable Long idCliente) {
        final InteresseIdentity identity = new InteresseIdentity(idImovel, idCliente);
        return InteresseRepository.findById(identity);
    }
    
    /*
    @GetMapping(value = "/{idImovel}")
    public Optional<Interesse> listarPeloIdImovel(@PathVariable Long idImovel) {
        return InteresseRepository.findByInteresseIdentityIdImovel(idImovel);
    }*/
    
    @GetMapping(value = "/{idCliente}")
    public List<Interesse> listarPeloIdCliente(@PathVariable Long idCliente) {
        //InteresseRepository.findByInteresseIdentityIdCliente(idCliente);
        return InteresseRepository.findAllByInteresseIdentityIdCliente(idCliente);
    }
    
    @PostMapping
    public Interesse adicionar(@RequestBody Interesse Interesse) {
        return InteresseRepository.save(Interesse);
    }
        
    @DeleteMapping(value="/{idImovel}/{idCliente}")
    public ResponseEntity deletar(@PathVariable Long idImovel, @PathVariable Long idCliente) {
        final InteresseIdentity identity = new InteresseIdentity(idImovel, idCliente);
        return InteresseRepository.findById(identity)
                .map(record-> {
                    InteresseRepository.deleteById(identity);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    
}


