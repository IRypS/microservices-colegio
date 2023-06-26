package com.edu.certus.msprofesor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.msprofesor.entity.ProfesorEntity;

@Repository
public interface ProfesorRepository extends JpaRepository< ProfesorEntity, Long >{

    public List< ProfesorEntity > findAllByEstadoTrue();
    public ProfesorEntity findByIdAndEstadoTrue( Long id );
    public ProfesorEntity findByNombresAndApellidosAndEstadoTrue( String nombres, String apellidos );
    
}
