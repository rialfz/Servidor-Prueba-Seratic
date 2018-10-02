/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.util.Cifrado;
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
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    private ObjectMapper mapper;
    
    @RequestMapping(value ="hospital/doctor", method = RequestMethod.POST)
    public Estructura save(@RequestBody String doctorJson) throws IOException{
        try {
            this.mapper=new ObjectMapper();
            Doctor doctor = this.mapper.readValue(doctorJson, Doctor.class);
            if(!doctorService.exist(doctor.getTarjetaProfesional())){
            doctor.validarDoctor();
            doctor.setFechaCreacion(new Date ());
            doctor.setContrasena(Cifrado.cifrarContrasena(doctor.getContrasena()));          
            this.doctorService.save(doctor);
             return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),new ArrayList<Data>());
            }else{
                   throw new Exception("el usuario ya se encuentra registrado");  
            }
            
           
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    /**
     * Permite optener el listado de doctores
     * @param 
     * @return  List JosnUser
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/doctor", method = RequestMethod.GET)
    public Estructura getDoctores() throws IOException{
        try {
            if(this.doctorService.count()!=0){
            ArrayList<Doctor> doctores= (ArrayList<Doctor>) this.doctorService.findAll();
            ArrayList<Data> listdata  = new ArrayList<Data>();
            Data data = new Data();
            data.setRows(doctores);
            data.setTotal(doctores.size());
            listdata.add(data);
            return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),listdata);
            }else{
                throw new Exception("no hay doctores registrados");
            }            
            
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    /**
    Permite actualizar al doctor teniendo en cuenta el json recibido
     * @param doctorJson 
     * @return  List doctorJson
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/doctor", method = RequestMethod.PUT)
    public Estructura updateDoctor(@RequestBody String doctorJson) throws IOException{
        try {
            this.mapper=new ObjectMapper();
            Doctor doctor = this.mapper.readValue(doctorJson, Doctor.class);
            doctor.validarDoctor();
            
             doctor.setContrasena(cambioContrasena(doctor.getContrasena(), doctor.getTarjetaProfesional()));
            
                       
            this.doctorService.save(doctor);
            
            return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),new ArrayList<Data>());
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
     /**
    Permite eliminar al doctor teniendo en cuenta el id recibido
     * @param Integer (id) 
     * @return  status
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/doctor", method = RequestMethod.DELETE)
    public Estructura deleteUser(@RequestHeader("id") Integer id){
        try {
            if(this.doctorService.exist(id)){
                this.doctorService.delete(id);
            }else{
                throw new Exception("no hay doctores registrado con identificador "+ id);
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
    @RequestMapping(value ="hospital/doctor/find", method = RequestMethod.GET)
    public Estructura findUser(@RequestHeader("id") Integer id){
        try {
            if(this.doctorService.exist(id)){
                Doctor doctor = this.doctorService.find(id);
                ArrayList<Doctor> doctores= new ArrayList<>();
                doctores.add(doctor);
                ArrayList<Data> listdata  = new ArrayList<>();
                Data data = new Data();
                data.setRows(doctores);
                data.setTotal(doctores.size());
                listdata.add(data);
                return new Estructura(true,"operacion exitosassss",HttpStatus.OK.value(),listdata);
            }else{
                throw new Exception("no hay doctores registrado con identificador "+ id);
            }
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    /**
    verifica que la contraseña haya cambiado para ser remplazada en la base de datos
     * @param String contraseña, identificacion Usuario 
     * @return  boolean
     * @throws IOException 
     */
    public String cambioContrasena(String contrasenaNueva, Integer idDoctor) throws IOException{
        String cambio = "";
        Doctor doctor = this.doctorService.find(idDoctor);
        
        String cifrado = Cifrado.cifrarContrasena(contrasenaNueva);
        if(!doctor.getContrasena().equals(cifrado)){
            cambio = cifrado;
        }else{
            cambio = doctor.getContrasena();
        }
        
        return cambio;
    }
}
