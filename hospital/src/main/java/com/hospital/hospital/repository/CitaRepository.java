/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.repository;

import com.hospital.hospital.model.Cita;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author RicardoZ
 */
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    
    @Query(value="select * from cita_medica where tarjeta_profesional = ?1", nativeQuery = true)
    public List<Cita> citasByDoctor (Integer tarjeta_profesional);
    
    @Query(value="select * from cita_medica where identificacion_paciente = ?1", nativeQuery = true)
    public List<Cita> citasByUsuario (Integer identificacion_paciente);
}
