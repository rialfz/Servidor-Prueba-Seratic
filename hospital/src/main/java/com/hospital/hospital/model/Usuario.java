/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.model;

import java.io.IOException;
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
@Table( name="usuario")
@Access(AccessType.FIELD)
public class Usuario {
    
    @Id
    @Column( name="identificacion", unique = true, nullable=true)
    private Integer identificacion;
    
    @Column( name="nombre_usuario", nullable=false)
    private String nombreUsuario;
    
    @Column( name="contrasena", nullable=false)
    private String contrasena;
    
    @Column( name="tipo", nullable=false)
    private String tipo;
    
    @Column( name="entidad_afiliacion", nullable=false)
    private String entidadAfiliacion;
    
    @Column( name="edad", nullable=false)
    private Integer edad;
    
    @Column( name="telefono_usuario", nullable=false)
    private String telefono;
    
    @Column( name="correo", nullable=false)
    private String correo;
    
    @Column( name="usuario", nullable=false)
    private String usuario;
    
    @Column( name="fecha_registro", nullable=false)
    private Date fechaRegistro;

    public Usuario() {
    }

    public Usuario(Integer identificacion, String nombreUsuario, String contrasena, String tipo, String entidadAfiliacion, Integer edad, String telefono, String correo, String usuario, Date fechaRegistro) {
        this.identificacion = identificacion;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.entidadAfiliacion = entidadAfiliacion;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario = usuario;
        this.fechaRegistro = fechaRegistro;
    }
    
    /**
     * Permite validar los campos obligatorios para el usuario 
     * @param 
     * @return  List JosnUser
     * @throws IOException 
     */
    public void validarUsuario() throws Exception{
       if( this.nombreUsuario==null || this.nombreUsuario.equals("")){
           throw new Exception("el nombre es obligatorio");
       }
       if( this.contrasena==null || this.contrasena.equals("")){
           throw new Exception("la contraseña obligatoria");
       }
       if( this.entidadAfiliacion==null || this.entidadAfiliacion.equals("")){
           throw new Exception("la entidad es obligatorio");
       }
       if( this.edad==null){
           //validar edad en un rango aceptable
           if(edad<0 || edad > 120){
                throw new Exception("la edad es inconsistente");
            }
           throw new Exception("la edad es obligatoria");
       }
       if( this.telefono==null || this.telefono.equals("")){
           throw new Exception("el telefono es obligatorio");
       }
        
       if( this.correo==null || this.correo.equals("")){
           throw new Exception("el correo es obligatorio");
       }
       
       if( this.usuario==null || this.usuario.equals("")){
           throw new Exception("el usuario es obligatorio");
       }
       
       if(this.identificacion==null){
               throw new Exception("la identificacion es obligatoria");
       }
    }
    

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEntidadAfiliacion() {
        return entidadAfiliacion;
    }

    public void setEntidadAfiliacion(String entidadAfiliacion) {
        this.entidadAfiliacion = entidadAfiliacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
    
    
}
