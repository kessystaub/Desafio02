/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.imovelservice.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import lombok.Data;

/**
 *
 * @author 6962246
 */
@Data
@Entity
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,name="id_tipo_imovel")
    private Long idTipoImovel;
    @Column(nullable = false, length = 100)
    private String titulo;
    @Column(length = 500)
    private String descricao;
    @Column(nullable = false,name="data_criacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCriacao;
    @Column(length = 100)
    private float valor;
    @Column(nullable=false)
    private Integer status;
    @Transient
    TipoImovel tipoImovel;
    @Transient
    public List<Interesse> interesse;
    
}
