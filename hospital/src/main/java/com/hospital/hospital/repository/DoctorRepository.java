/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.hospital.repository;

import com.hospital.hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author RicardoZ
 */
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    
}
