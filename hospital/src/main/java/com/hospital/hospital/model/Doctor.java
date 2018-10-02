/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.model;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 *
 * @author RicardoZ
 */
@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@Entity
@Table( name="doctor")
@Access(AccessType.FIELD)
public class Doctor {
    
    @Id
    @Column( name="tarjeta_profesional", unique = true, nullable=false)
    private Integer tarjetaProfesional;
    
    @Column( name="nombre_doctor", nullable=false)
    private String nombreDoctor;
    
    @Column( name="titulo_doctor", nullable=false)
    private String titulo;
    
    @Column( name="usuario", nullable=false)
    private String usuario;
    
    @Column( name="contrasena", nullable=false)
    private String contrasena;
    
    @Column( name="fecha_creacion", nullable=false)
    private Date fechaCreacion;

    public Doctor() {
    }

    public Doctor(Integer tarjetaProfesional, String nombreDoctor, String titulo, String usuario, String contrasena, Date fechaCreacion) {
        this.tarjetaProfesional = tarjetaProfesional;
        this.nombreDoctor = nombreDoctor;
        this.titulo = titulo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fechaCreacion = fechaCreacion;
    }
    
     public void validarDoctor() throws Exception{
       if( this.tarjetaProfesional==null){
           throw new Exception("la tarjeta profecional es obligatoria");
       }
       if( this.contrasena==null||this.contrasena.equals("")){
           throw new Exception("la contraseña es obligatoria");
       }
      if( this.nombreDoctor==null||this.nombreDoctor.equals("")){
           throw new Exception("el nombre es obligatorio");
       }
      if( this.titulo==null||this.titulo.equals("")){
           throw new Exception("el titulo es bligatorio");
       }

      if( this.usuario==null||this.usuario.equals("")){
           throw new Exception("el usuario es obligatoria");
       }
       
     }

    public Integer getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(Integer tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
    
}
