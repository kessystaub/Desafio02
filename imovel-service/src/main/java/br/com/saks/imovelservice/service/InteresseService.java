/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.imovelservice.service;

import br.com.saks.imovelservice.model.Interesse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author 6962246
 */
@FeignClient(name="interesse-service")
public interface InteresseService {
    @GetMapping(value = "/interesses/{idImovel}")
    List<Interesse> findAllByInteresseIdentityIdImovel(@PathVariable("idImovel") Long idImovel);
}
