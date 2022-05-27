/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.imovelservice.controller;

import br.com.saks.imovelservice.model.Imovel;
import br.com.saks.imovelservice.model.TipoImovel;
import br.com.saks.imovelservice.repository.ImovelRepository;
import br.com.saks.imovelservice.service.TipoImovelService;
import java.util.List;
import java.util.Optional;
import javax.persistence.Transient;
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

//-Criar  um  Get  para quando listar o  tipo  de  imóvel,  trazer  todos  os imóveis associados.

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
    
    @Autowired
    private TipoImovelService tipoImovelService;
    
    @Transient
    TipoImovel tipoImovel;
    
    @Autowired
    private ImovelRepository ImovelRepository;
    
    @GetMapping
    public List<Imovel> listarTodos() {
        return ImovelRepository.findAll();
    }
    
    @GetMapping(value="{id}")
    public Imovel listarPeloId(@PathVariable Long id) {
        Optional<Imovel> imovelResponse = ImovelRepository.findById(id);
        Imovel imovel = imovelResponse.get();
        imovel.setTipoImovel(tipoImovelService.listarPeloId(imovel.getIdTipoImovel()));
        return imovel;
    }
    
    //@GetMapping(value="/tipo/{id_tipo_imovel}")
    //List
    
    /*
    @GetMapping(value="/{id}")
    public Optional<Imovel> listarPeloId(@PathVariable Long id) {
        return ImovelRepository.findById(id);
    }
    */
    
    @PostMapping
    public Imovel adicionar(@RequestBody Imovel Imovel) {
        return ImovelRepository.save(Imovel);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Imovel Imovel) {
        return ImovelRepository.findById(id)
                .map(record -> {
                    record.setTitulo(Imovel.getTitulo());
                    record.setDescricao(Imovel.getDescricao());
                    record.setStatus(Imovel.getStatus());
                    Imovel ImovelUpdated = ImovelRepository.save(record);
                    return ResponseEntity.ok().body(ImovelUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
   
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return ImovelRepository.findById(id)
                .map(record-> {
                    ImovelRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
