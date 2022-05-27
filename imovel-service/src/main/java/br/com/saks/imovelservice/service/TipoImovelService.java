/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.imovelservice.service;

import br.com.saks.imovelservice.model.TipoImovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author 6962246
 */
@FeignClient(name="tipo-imovel-service")
public interface TipoImovelService {
    @GetMapping(value = "/tiposimovel/{idTipoImovel}")
    TipoImovel listarPeloId(@PathVariable("idTipoImovel") Long idTipoImovel);
}
