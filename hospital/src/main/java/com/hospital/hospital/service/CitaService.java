/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.service;

import com.hospital.hospital.model.Cita;
import java.util.List;

/**
 *
 * @author RicardoZ
 */
public interface CitaService {
    
    public Cita save(Cita doctor);
    public List<Cita> findAll();
    public List<Cita> findAllByDoctor(Integer tarjeta);
    public List<Cita> findAllByUsuario(Integer identificacin);
    public Cita find(Integer id);
    public void delete(Integer id);
    public boolean exist(Integer id);
    public Long count();
}
