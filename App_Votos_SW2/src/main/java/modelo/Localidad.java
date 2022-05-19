/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author dnarc
 */
@Entity
@Table(name="Localidad")
public class Localidad {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idLocalidad;
    
    @Column(name="Pais")
    private String Pais;
    
    @Column(name="Comunidad_Autonoma")
    private String Comunidad_Autonoma;
    
    @Column(name="Provincia")
    private String Provincia;
    
    @Column(name="Municipio")
    private String Municipio;
    
    @Column(name="Codigo_Postal")
    private String Codigo_Postal;
    
}
