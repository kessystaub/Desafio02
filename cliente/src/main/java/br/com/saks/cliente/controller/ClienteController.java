/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.cliente.controller;

import br.com.saks.cliente.model.Cliente;
import br.com.saks.cliente.repository.ClienteRepository;
import br.com.saks.cliente.service.InteresseService;
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
@RequestMapping("/clientes")
public class ClienteController {
        
    @Autowired
    InteresseService interesseService;
    
    @Autowired
    private ClienteRepository ClienteRepository;
    
    @GetMapping
    public List<Cliente> listarTodos() {
        return ClienteRepository.findAll();
    }
    
    
    /*
    @GetMapping(value="/{id}")
    public Optional<Cliente> listarPeloId(@PathVariable Long id) {
        return ClienteRepository.findById(id);
    }*/
    
    
    int i = 2;
    Long n = Long.valueOf (i);
    
    @GetMapping(value="/{id}")
    public Cliente listarPeloId(@PathVariable Long id) {
        Optional<Cliente> clienteResponse = ClienteRepository.findById(id);
        Cliente cliente = clienteResponse.get();
        //System.out.println("antes do set.interesse, cliente.getInteresse: "+cliente.getInteresse());
        //System.out.println("cliente.getId(): "+cliente.getId());
        System.out.println("interesseService.findAllByInteresseIdCliente(cliente.getId()): "+interesseService.findAllByInteresseIdCliente(cliente.getId()));
        //cliente.setInteresse(interesseService.listarPeloIdCliente(cliente.getId()));
        
        cliente.setInteresse(interesseService.findAllByInteresseIdCliente(cliente.getId()));
        
        //System.out.println("depois do set.interesse, cliente.getInteresse: "+cliente.getInteresse());
        return cliente;
    }
    
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
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return senhaHex;
    }
    
    @PostMapping
    public Cliente adicionar(@RequestBody Cliente Cliente) {
        Cliente.setSenha(criptografaSenha(Cliente.getSenha()));
        return ClienteRepository.save(Cliente);
        
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Cliente Cliente) {
        return ClienteRepository.findById(id)
                .map(record -> {
                    record.setNome(Cliente.getNome());
                    record.setEmail(Cliente.getEmail());
                    record.setSenha(criptografaSenha(Cliente.getSenha()));
                    record.setTelefone(Cliente.getTelefone());
                    Cliente ClienteUpdated = ClienteRepository.save(record);
                    return ResponseEntity.ok().body(ClienteUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return ClienteRepository.findById(id)
                .map(record-> {
                    ClienteRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
