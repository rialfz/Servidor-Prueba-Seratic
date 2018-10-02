/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.hospital.model.Usuario;
import com.hospital.hospital.service.UsuarioService;
import com.hospital.hospital.util.Cifrado;
import com.hospital.hospital.util.Data;
import com.hospital.hospital.util.Estructura;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
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
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioServiece;
    
    private ObjectMapper mapper;
    
    /**
     * Permite guardar un usuario paciente
     * @param 
     * @return  List JosnUser
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/usuario", method = RequestMethod.POST)
    public Estructura save(@RequestBody String userJson) throws IOException{
        try {
            this.mapper=new ObjectMapper();
            Usuario usuario = this.mapper.readValue(userJson, Usuario.class);
            if(!usuarioServiece.exist(usuario.getIdentificacion())){
            usuario.validarUsuario();
            usuario.setFechaRegistro(new Date ());
            usuario.setContrasena(Cifrado.cifrarContrasena(usuario.getContrasena()));
                       
            this.usuarioServiece.save(usuario);
             return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),new ArrayList<Data>());
            }else{
                   throw new Exception("el usuario ya se encuentra registrado");  
            }
            
           
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    
    
    /**
     * Permite optener el listado de usuarios
     * @param 
     * @return  List JosnUser
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/usuario", method = RequestMethod.GET)
    public Estructura getUsers() throws IOException{
        try {
            if(this.usuarioServiece.count()!=0){
            ArrayList<Usuario> users= (ArrayList<Usuario>) this.usuarioServiece.findAll();
            ArrayList<Data> listdata  = new ArrayList<Data>();
            Data data = new Data();
            data.setRows(users);
            data.setTotal(users.size());
            listdata.add(data);
            return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),listdata);
            }else{
                throw new Exception("no hay usuarios registrados");
            }            
            
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    
    /**
    Permite actualizar al usuario teniendo en cuenta el json recibido
     * @param userJson (idUser, email, password) 
     * @return  List JosnUser(id, email, password)
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/usuario", method = RequestMethod.PUT)
    public Estructura updateUser(@RequestBody String userJson) throws IOException{
        try {
            this.mapper=new ObjectMapper();
            Usuario usuario = this.mapper.readValue(userJson, Usuario.class);
            usuario.validarUsuario();
            
             usuario.setContrasena(cambioContrasena(usuario.getContrasena(), usuario.getIdentificacion()));
            
                       
            this.usuarioServiece.save(usuario);
            
            return new Estructura(true,"operacion exitosa",HttpStatus.OK.value(),new ArrayList<Data>());
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    
    
     /**
    Permite eliminar al usuario teniendo en cuenta el id recibido
     * @param String (id) 
     * @return  status
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/usuario", method = RequestMethod.DELETE)
    public Estructura deleteUser(@RequestHeader("id") Integer id){
        try {
            if(this.usuarioServiece.exist(id)){
                this.usuarioServiece.delete(id);
            }else{
                throw new Exception("no hay usuario registrado con identificador "+ id);
            }
            return new Estructura(true,"operacion exitosassss",HttpStatus.OK.value(),new ArrayList<Data>());
        } catch (Exception ex) {
            return new Estructura(false,ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),new ArrayList<Data>());
        }
    }
    
    
    
    /**
    Permite buscar al usuario teniendo en cuenta el id recibido
     * @param id
     * @param Integer (id) 
     * @return  JsonUser(id, email, password)
     * @throws IOException 
     */
    @RequestMapping(value ="hospital/usuario/find", method = RequestMethod.GET)
    public Estructura findUser(@RequestHeader("id") Integer id){
        try {
            if(this.usuarioServiece.exist(id)){
                Usuario usuario = this.usuarioServiece.find(id);
                ArrayList<Usuario> usuarios= new ArrayList<>();
                usuarios.add(usuario);
                ArrayList<Data> listdata  = new ArrayList<>();
                Data data = new Data();
                data.setRows(usuarios);
                data.setTotal(usuarios.size());
                listdata.add(data);
                return new Estructura(true,"operacion exitosassss",HttpStatus.OK.value(),listdata);
            }else{
                throw new Exception("no hay usuario registrado con identificador "+ id);
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
    public String cambioContrasena(String contrasenaNueva, Integer idUsuario) throws IOException{
        String cambio = "";
        Usuario usuario = this.usuarioServiece.find(idUsuario);
        
        String cifrado = Cifrado.cifrarContrasena(contrasenaNueva);
        if(!usuario.getContrasena().equals(cifrado)){
            cambio = cifrado;
        }else{
            cambio = usuario.getContrasena();
        }
        
        return cambio;
    }
    
    
    
    
}
