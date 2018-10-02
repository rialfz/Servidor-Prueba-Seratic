/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.controller;

import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author RicardoZ
 */
public class LoginController {
    
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private UsuarioService usuarioServiece;
}
