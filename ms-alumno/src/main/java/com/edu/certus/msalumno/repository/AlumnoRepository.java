package com.edu.certus.msalumno.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.msalumno.entity.AlumnoEntity;

@Repository
public interface AlumnoRepository extends JpaRepository< AlumnoEntity, Long > {
    

    /**
     * Busca alumnos ( estado = true (1) ) 
     * @return Listado de objetos <b>AlumnoEntity</b>
     */
    public List< AlumnoEntity > findAllByEstadoTrue();



    /**
     * Busca un alumno ( estado = true (1) ) con el ID ingresado
     * @param id ID del alumno a buscar
     * @return Objeto <b>AlumnoEntity</b>
     */
    public AlumnoEntity findByIdAndEstadoTrue( Long id );



    /**
     * Busca un alumno ( estado = true (1) ) con los nombres y apellidos ingresados.
     * @param nombres Nombres del alumno a buscar
     * @param apellidos Apellidos del alumno a buscar
     * @return Objeto <b>AlumnoEntity</b>
     */
    public AlumnoEntity findByNombresAndApellidosAndEstadoTrue( String nombres, String apellidos );
    
    
}
