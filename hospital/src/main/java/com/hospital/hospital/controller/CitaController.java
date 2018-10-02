/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.hospital.model.Cita;
import com.hospital.hospital.service.CitaService;
import com.hospital.hospital.util.Data;
import com.hospital.hospital.util.Estructura;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RicardoZ
 */
@RestController
public class CitaController {
    
    @Autowired
    private CitaService citaService;
    private ObjectMapper mapper;
    
    
    
    @RequestMapping(value ="hospital/cita", method = RequestMethod.POST)
    public Estructura save(@RequestBody String citaJson) throws IOException{
        try {
            Date fecha = new Date();
            Integer hora = fecha.getHours();
            if((hora>=8 && hora<12) || (hora>=14 && hora<18) ){
            this.mapper=new ObjectMapper();
            Cita cita = this.mapper.readValue(citaJson, Cita.class);
            if(!citaService.exist(cita.getId())){
                cita.validarCita();
                cita.setFechaCreacion(new Date ());
                cita.setFechaCita(new Date());
                this.citaService.save(cita);
                return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),new ArrayList<Data>());
            }else{
                   throw new Exception("el usuario ya se encuentra registrado");  
            }
            }else{
                return new Estructura(false,"horario no habil para generar citas",HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
            }
           
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    
    /**
     * Permite optener el listado de citas
     * @param 
     * @return  List JosnUser
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/cita", method = RequestMethod.GET)
    public Estructura getCita() throws IOException{
        try {
            if(this.citaService.count()!=0){
            ArrayList<Cita> citas= (ArrayList<Cita>) this.citaService.findAll();
            ArrayList<Data> listdata  = new ArrayList<Data>();
            Data data = new Data();
            data.setRows(citas);
            data.setTotal(citas.size());
            listdata.add(data);
            return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),listdata);
            }else{
                throw new Exception("no hay citas registrados");
            }            
            
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    
    /**
    Permite actualizar la cita teniendo en cuenta el json recibido
     * @param citaJson 
     * @return  List citaJson
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/cita", method = RequestMethod.PUT)
    public Estructura updateCita(@RequestBody String doctorJson) throws IOException{
        try {
            this.mapper=new ObjectMapper();
            Cita cita = this.mapper.readValue(doctorJson, Cita.class);
            cita.validarCita();
            
            
            
                       
            this.citaService.save(cita);
            
            return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),new ArrayList<Data>());
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
     /**
    Permite eliminar una cita teniendo en cuenta el id recibido
     * @param Integer (id) 
     * @return  status
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/cita", method = RequestMethod.DELETE)
    public Estructura deleteCita(@RequestHeader("id") Integer id){
        try {
            if(this.citaService.exist(id)){
                this.citaService.delete(id);
            }else{
                throw new Exception("no hay citas registrado con identificador "+ id);
            }
            return new Estructura(true,"operacion exitosassss",HttpStatus.OK.value(),new ArrayList<Data>());
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    
    /**
    Permite buscar al doctor teniendo en cuenta el id recibido
     * @param id
     * @param Integer (id) 
     * @return  doctorJson
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/cita/find", method = RequestMethod.GET)
    public Estructura findCita(@RequestHeader("id") Integer id){
        try {
            if(this.citaService.exist(id)){
                Cita cita = this.citaService.find(id);
                ArrayList<Cita> doctores= new ArrayList<>();
                doctores.add(cita);
                ArrayList<Data> listdata  = new ArrayList<>();
                Data data = new Data();
                data.setRows(doctores);
                data.setTotal(doctores.size());
                listdata.add(data);
                return new Estructura(true,"operacion exitosassss",HttpStatus.OK.value(),listdata);
            }else{
                throw new Exception("no hay cita registrado con identificador "+ id);
            }
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    
    /**
     * Permite optener el listado de citas por doctor
     * @param 
     * @return  List JosnUser
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/cita/doctor", method = RequestMethod.GET)
    public Estructura getCitaByDoctor(@RequestHeader("tarjeta_profesional") Integer tarjeta) throws IOException{
        try {
            if(this.citaService.count()!=0){
            ArrayList<Cita> citas= (ArrayList<Cita>) this.citaService.findAllByDoctor(tarjeta);
            if(citas.size()>0){
                ArrayList<Data> listdata  = new ArrayList<Data>();
                Data data = new Data();
                data.setRows(citas);
                data.setTotal(citas.size());
                listdata.add(data);
                return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),listdata);
            }else{
                return new Estructura(true,"el doctor no tiene citas asignadas",HttpStatus.OK.value(),new ArrayList<Data>());
            }
            }else{
                throw new Exception("no hay citas registrados");
            }            
            
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    
    /**
     * Permite optener el listado de citas por paciente
     * @param 
     * @return  List JosnUser
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/cita/usuario", method = RequestMethod.GET)
    public Estructura getCitaByUsuario(@RequestHeader("identificacion") Integer identificacion) throws IOException{
        try {
            if(this.citaService.count()!=0){
            ArrayList<Cita> citas= (ArrayList<Cita>) this.citaService.findAllByUsuario(identificacion);
            if(citas.size()>0){
                ArrayList<Data> listdata  = new ArrayList<Data>();
                Data data = new Data();
                data.setRows(citas);
                data.setTotal(citas.size());
                listdata.add(data);
                return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),listdata);
            }else{
                return new Estructura(true,"el paciente no tiene citas asignadas",HttpStatus.OK.value(),new ArrayList<Data>());
            }
            }else{
                throw new Exception("no hay citas registrados");
            }            
            
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    
    
    
    
}
