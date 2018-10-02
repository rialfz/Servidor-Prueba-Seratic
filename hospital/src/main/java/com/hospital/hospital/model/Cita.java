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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table( name="cita_medica")
@Access(AccessType.FIELD)
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name="id", unique = true, nullable=true)
    private Integer id;
    
    @Column( name="identificacion_paciente",  nullable=true)
    private Integer identificacionPaciente;
    
    @Column( name="nombre_paciente",  nullable=true)
    private String nombrePaciente;
    
    @Column( name="entidad_afiliacion",  nullable=true)
    private String entidad;
    
    @Column( name="tarjeta_profecional",  nullable=true)
    private Integer tarjetaProfesional;
    
    @Column( name="nombre_doctor",  nullable=true)
    private String nombreDoctor;
    
    @Column( name="titulo_doctor",  nullable=true)
    private String titulo;
    
    @Column( name="motivo_consulta",  nullable=true)
    private String motivo;
    
    @Column( name="fecha_cita",  nullable=true)
    private Date fechaCita;
    
    @Column( name="fecha_creacion",  nullable=true)
    private Date fechaCreacion;

    public Cita() {
    }

    public Cita(Integer id, Integer identificacionPaciente, String nombrePaciente, String entidad, Integer tarjetaProfesional, String nombreDoctor, String titulo, String motivo, Date fechaCita, Date fechaCreacion) {
        this.id = id;
        this.identificacionPaciente = identificacionPaciente;
        this.nombrePaciente = nombrePaciente;
        this.entidad = entidad;
        this.tarjetaProfesional = tarjetaProfesional;
        this.nombreDoctor = nombreDoctor;
        this.titulo = titulo;
        this.motivo = motivo;
        this.fechaCita = fechaCita;
        this.fechaCreacion = fechaCreacion;
    }

    public void validarCita() throws Exception{
    if( this.identificacionPaciente==null){
           throw new Exception("la identificacion del paciente es necesaria");
       }
    if( this.tarjetaProfesional==null){
           throw new Exception("la tarjeta profesional del doctor es necesaria");
       }
    if( this.nombrePaciente==null || this.nombrePaciente.equals("")){
           throw new Exception("el nombre del paciente es necesario");
       }
    if( this.entidad==null || this.entidad.equals("")){
           throw new Exception("la entidad del paciente es necesaria");
       }
    if( this.nombreDoctor==null || this.nombreDoctor.equals("")){
           throw new Exception("el nombre del doctor es necesario");
       }
    if( this.titulo==null || this.titulo.equals("")){
           throw new Exception("el titulo del doctor es necesario");
       }
    if( this.motivo==null || this.motivo.equals("")){
           throw new Exception("el motivo de la consulta es necesario");
       }
    if( this.fechaCita==null ){
           throw new Exception("la fecha de la cita es necesaria");
       }
    
    
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdentificacionPaciente() {
        return identificacionPaciente;
    }

    public void setIdentificacionPaciente(Integer identificacionPaciente) {
        this.identificacionPaciente = identificacionPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
    
}
