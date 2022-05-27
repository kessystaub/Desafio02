/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.administradorservice.controller;

import br.com.saks.administradorservice.model.Administrador;
import br.com.saks.administradorservice.repository.AdministradorRepository;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/administradores")
public class AdministradorController {
        
    public String criptografaSenha(String senha){
        String senhaHex = "";
        
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
            
            StringBuilder sb = new StringBuilder();
            
            for(byte b : messageDigest){
                sb.append(String.format("%02X", 0xFF & b));
            }
            senhaHex = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return senhaHex;
    }
    
    @Autowired
    private AdministradorRepository AdministradorRepository;
    
    @GetMapping
    public List<Administrador> listarTodos() {
        return AdministradorRepository.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Optional<Administrador> listarPeloId(@PathVariable Long id) {
        return AdministradorRepository.findById(id);
    }
    
    @PostMapping
    public Administrador adicionar(@RequestBody Administrador Administrador) {
        Administrador.setSenha(criptografaSenha(Administrador.getSenha()));
        return AdministradorRepository.save(Administrador);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Administrador Administrador) {
        return AdministradorRepository.findById(id)
                .map(record -> {
                    record.setNome(Administrador.getNome());
                    record.setEmail(Administrador.getEmail());
                    record.setSenha(criptografaSenha(Administrador.getSenha()));
                    record.setStatus(Administrador.getStatus());
                    Administrador AdministradorUpdated = AdministradorRepository.save(record);
                    return ResponseEntity.ok().body(AdministradorUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return AdministradorRepository.findById(id)
                .map(record-> {
                    AdministradorRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

