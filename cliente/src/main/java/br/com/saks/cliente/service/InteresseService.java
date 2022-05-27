/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.cliente.service;

import java.util.List;
import br.com.saks.cliente.model.Interesse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author 6962246
 */
@FeignClient(name="interesse-service")
public interface InteresseService {
    
    @GetMapping(value = "/interesses/{idCliente}")
    List<Interesse> findAllByInteresseIdentityIdCliente(@PathVariable("idCliente") Long idCliente);
    //List<Interesse> listarPeloIdCliente(@PathVariable("idCliente") Long idCliente);
    //List<Interesse> findAllByInteresseIdentityIdCliente(@PathVariable("idCliente") Long idCliente);
   
    
}
