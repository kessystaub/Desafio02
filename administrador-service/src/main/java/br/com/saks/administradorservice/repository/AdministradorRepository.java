/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.administradorservice.repository;

import br.com.saks.administradorservice.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 6962246
 */
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
    
}
