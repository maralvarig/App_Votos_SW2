/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dnarc
 */
@Entity
@Table(name="escrutinio")
public class Escrutinio implements Serializable{
    
    Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int idEscrutinio;
    
    @Column(name="Fecha")
    private String Fecha;
}
