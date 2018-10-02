/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.service;

import com.hospital.hospital.model.Doctor;
import java.util.List;

/**
 *
 * @author RicardoZ
 */
public interface DoctorService {
    
    public Doctor save(Doctor doctor);
    public List<Doctor> findAll();
    public Doctor find(Integer id);
    public void delete(Integer id);
    public boolean exist(Integer id);
    public Long count();
}
