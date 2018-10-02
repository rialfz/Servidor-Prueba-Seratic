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
    @RequestMapping(value ="hospital/doctor", method = RequestMethod.GET)
    public Estructura getDoctores() throws IOException{
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
    
}
