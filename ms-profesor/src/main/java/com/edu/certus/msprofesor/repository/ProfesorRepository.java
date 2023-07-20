package com.edu.certus.msprofesor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.msprofesor.entity.ProfesorEntity;

@Repository
public interface ProfesorRepository extends JpaRepository< ProfesorEntity, Long >{


    /**
     * Busca profesores ( estado = true (1) ) 
     * @return Listado de objetos <b>ProfesorEntity</b>
     */
    public List< ProfesorEntity > findAllByEstadoTrue();



    /**
     * Busca un profesor ( estado = true (1) ) con el ID ingresado
     * @param id ID del profesor a buscar
     * @return Objeto <b>ProfesorEntity</b>
     */
    public ProfesorEntity findByIdAndEstadoTrue( Long id );



    /**
     * Busca un profesor ( estado = true (1) ) con los nombres y apellidos ingresados.
     * @param nombres Nombres del profesor a buscar
     * @param apellidos Apellidos del profesor a buscar
     * @return Objeto <b>ProfesorEntity</b>
     */
    public ProfesorEntity findByNombresAndApellidosAndEstadoTrue( String nombres, String apellidos );
    
    
}
