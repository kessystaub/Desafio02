/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.tipoimovelservice.controller;

import br.com.saks.tipoimovelservice.model.Imovel;
import br.com.saks.tipoimovelservice.model.TipoImovel;
import br.com.saks.tipoimovelservice.repository.TipoImovelRepository;
import br.com.saks.tipoimovelservice.service.ImovelService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 6962246
 */
@RestController
@RequestMapping("/tiposimovel")
public class TipoImovelController {
    //Criar  um  Get  para quando listar o  tipo  de  imóvel,  trazer  todos  os imóveis associados.
    //teste
    @Autowired
    private TipoImovelRepository TipoImovelRepository;
    
    @Autowired
    private ImovelService imovelService;
    
    @GetMapping
    public List<TipoImovel> listarTodos() {
        return TipoImovelRepository.findAll();
    }
    /*
    @GetMapping(value="/{id}")
    public Optional<TipoImovel> listarPeloId(@PathVariable Long id) {
        return TipoImovelRepository.findById(id);
    }*/
   
    //@GetMapping(value="/tipo/{id_tipo_imovel}")
    //List
    
    
    @GetMapping(value="/{id}")
    public TipoImovel listarPeloId(@PathVariable Long id) {
        Optional<TipoImovel> tipoimovelResponse = TipoImovelRepository.findById(id);
        TipoImovel tipoimovel = tipoimovelResponse.get();
        //tipoimovel.imoveis.add(imovelService.listarPeloId(imovel.getId()));
        tipoimovel.setImoveis(imovelService.findAllByIdTipoImovel(tipoimovel.getId()));
        return tipoimovel;
    }
    
    @PostMapping
    public TipoImovel adicionar(@RequestBody TipoImovel TipoImovel) {
        return TipoImovelRepository.save(TipoImovel);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody TipoImovel TipoImovel) {
        return TipoImovelRepository.findById(id)
                .map(record -> {
                    record.setNome(TipoImovel.getNome());
                    TipoImovel TipoImovelUpdated = TipoImovelRepository.save(record);
                    return ResponseEntity.ok().body(TipoImovelUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
   
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return TipoImovelRepository.findById(id)
                .map(record-> {
                    TipoImovelRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

