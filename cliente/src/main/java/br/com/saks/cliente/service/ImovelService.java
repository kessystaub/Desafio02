/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.cliente.service;

import br.com.saks.cliente.model.Imovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author 6962246
 */
@FeignClient(name="imovel-service")
public interface ImovelService {
    @GetMapping(value = "/imoveis/{idImovel}")
    Imovel listarPeloId(@PathVariable("idImovel") Long idImovel);
}
