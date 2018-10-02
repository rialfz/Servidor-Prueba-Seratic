/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.service;

import com.hospital.hospital.model.Cita;
import com.hospital.hospital.repository.CitaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RicardoZ
 */
@Service
public class CitaServiceImpl implements CitaService{

    @Autowired(required=true)
    private CitaRepository CitaRepository;
    
    @Override
    public Cita save(Cita doctor) {
        return this.CitaRepository.save(doctor);
    }

    @Override
    public List<Cita> findAll() {
        return this.CitaRepository.findAll();
    }

    @Override
    public Cita find(Integer id) {
        return this.CitaRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        this.CitaRepository.deleteById(id);
    }

    @Override
    public boolean exist(Integer id) {
        return this.CitaRepository.existsById(id);
    }

    @Override
    public Long count() {
        return this.CitaRepository.count();
    }
    
}
