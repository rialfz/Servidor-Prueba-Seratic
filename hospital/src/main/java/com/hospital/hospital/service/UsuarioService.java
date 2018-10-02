/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.service;

import com.hospital.hospital.model.Usuario;
import java.util.List;

/**
 *
 * @author RicardoZ
 */
public interface UsuarioService {
    public Usuario save(Usuario usuario);
    public List<Usuario> findAll();
    public Usuario find(Integer id);
    public void delete(Integer id);
    public boolean exist(Integer id);
    public Long count();
}
