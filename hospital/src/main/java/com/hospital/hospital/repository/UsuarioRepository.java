/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.repository;

import com.hospital.hospital.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author RicardoZ
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    @Query(value="select * from usuario where usuario = ?1", nativeQuery = true)
    public List<Usuario> usuarios (String usuario);
    
    @Query(value="SELECT * FROM hospital.usuario where hospital.usuario.nombre_usuario like ('?1%')", nativeQuery = true)
    public List<Usuario> findUsuarioFiltrobyNombre (String usuario);
    
}
