/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.tipoimovelservice.service;

import br.com.saks.tipoimovelservice.model.Imovel;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author 6962246
 */
@FeignClient(name="imovel-service")
public interface ImovelService {
    @GetMapping(value = "/imoveis/tiposimovel/{id_tipo_imovel}")
    List<Imovel> findAllByIdTipoImovel(@PathVariable("id_tipo_imovel") Long id_tipo_imovel);

    //Imovel listarPeloId(@PathVariable("idImovel") Long idImovel);
    //List<Imovel> findAllByImovelIdImovel(Long idImovel);
}


