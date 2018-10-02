/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.controller;

import com.hospital.hospital.model.Usuario;
import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.service.UsuarioService;
import com.hospital.hospital.util.Cifrado;
import com.hospital.hospital.util.Data;
import com.hospital.hospital.util.Estructura;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RicardoZ
 */
@RestController
public class LoginController {
    
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private UsuarioService usuarioServiece;
    
    @RequestMapping(value ="hospital/login", method = RequestMethod.POST)
    public Estructura login(@RequestHeader("usuario")  String usuario, @RequestHeader("contrasena") String contrasena) throws IOException{
            
            
                return new Estructura(false,"error validacion",HttpStatus.OK.value(),new ArrayList<Data>());
            
           
    
    }
    
    
    public boolean validar(String usuario, String pass){
        boolean b = false ;
        Usuario user = usuarioServiece.findByUser(usuario).get(0);
        try{
            String cifrado = Cifrado.cifrarContrasena(pass);
            if(user.getContrasena().equals(pass)){
                b=true;
            }
            
        }catch(Exception e){
          b=false;
        }
        return b;
    }
     
}
