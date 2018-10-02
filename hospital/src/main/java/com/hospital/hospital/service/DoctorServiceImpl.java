/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.service;

import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.repository.DoctorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RicardoZ
 */
@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired(required=true)
    private DoctorRepository doctorRepository;
    
    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor find(Integer id) {
        return this.doctorRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        this.doctorRepository.deleteById(id);
    }

    @Override
    public boolean exist(Integer id) {
        return this.doctorRepository.existsById(id);
    }

    @Override
    public Long count() {
        return this.doctorRepository.count();
    }
    
}
