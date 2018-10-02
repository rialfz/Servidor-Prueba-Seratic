/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.service;

import com.hospital.hospital.model.Usuario;
import com.hospital.hospital.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RicardoZ
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired(required=true)
    private UsuarioRepository usuarioRepository;
    
    @Override
    public Usuario save(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return  usuarioRepository.findAll();
    }

    @Override
    public Usuario find(Integer id) {
        return this.usuarioRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        this.usuarioRepository.deleteById(id);
    }

    @Override
    public boolean exist(Integer id) {
        return this.usuarioRepository.existsById(id);
    }

    @Override
    public Long count() {
        return this.usuarioRepository.count();
    }
    
}
