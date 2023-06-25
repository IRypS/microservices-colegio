package com.edu.certus.msalumno.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.msalumno.entity.AlumnoCursoEntity;

@Repository
public interface AlumnoCursoRepository extends JpaRepository< AlumnoCursoEntity, Long >{
    
    public AlumnoCursoEntity findByIdAlumnoAndIdCurso( Long idAlumno, Long idCurso );
    public List<AlumnoCursoEntity> findAllByIdAlumno( Long idAlumno );

}
