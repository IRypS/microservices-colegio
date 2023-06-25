package com.edu.certus.msalumno.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.msalumno.entity.AlumnoEntity;

@Repository
public interface AlumnoRepository extends JpaRepository< AlumnoEntity, Long > {
    
    public List< AlumnoEntity > findAllByEstadoTrue();
    public AlumnoEntity findByIdAndEstadoTrue( Long id );
    public AlumnoEntity findByNombresAndApellidosAndEstadoTrue( String nombres, String apellidos );
    
}
