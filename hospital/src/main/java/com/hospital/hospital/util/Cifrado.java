/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RicardoZ
 */
public class Cifrado {
    
    
    /**
     * Permite cifrar la contraseña con algoritmo de base 64 
     * @param String contraseña
     * @return  String cifrado 
     * @throws IOException 
     */
    public static String cifrarContrasena(String contrasena) throws Exception{
        try {
            String cifrado = "";
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            
            byte[] stringye = contrasena.getBytes();
            cifrado = Base64.getEncoder().encodeToString(stringye);
            bos.close();
            return cifrado;
        } catch (IOException ex) {
            throw new Exception("error cifrando contraseña"); 
        }
    }
    
}
