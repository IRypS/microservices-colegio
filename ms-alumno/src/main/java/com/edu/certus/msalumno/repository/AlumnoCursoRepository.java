package com.edu.certus.msalumno.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.msalumno.entity.AlumnoCursoEntity;

@Repository
public interface AlumnoCursoRepository extends JpaRepository< AlumnoCursoEntity, Long >{
    
    
    /**
     * Busca un registro ( alumno - curso ) a partir de ID's alumno y curso
     * @param idAlumno ID del alumno a buscar en un registro
     * @param idCurso ID del curso a buscar en el registro
     * @return Objeto <b>AlumnoCursoEntity</b>
     */
    public AlumnoCursoEntity findByIdAlumnoAndIdCurso( Long idAlumno, Long idCurso );



    /**
     * Busca un listado de registros ( alumno - curso ) a partir de un ID de alumno
     * @param idAlumno ID del alumno a buscar en los registros
     * @return Listado de objetos <b>AlumnoCursoEntity</b>
     */
    public List<AlumnoCursoEntity> findAllByIdAlumno( Long idAlumno );

    
}
