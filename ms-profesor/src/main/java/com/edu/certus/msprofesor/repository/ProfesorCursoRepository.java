package com.edu.certus.msprofesor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.msprofesor.entity.ProfesorCursoEntity;

@Repository
public interface ProfesorCursoRepository extends JpaRepository< ProfesorCursoEntity, Long >{
    
    public ProfesorCursoEntity findByIdProfesorAndIdCurso( Long idProfesor, Long idCurso );
    public List<ProfesorCursoEntity> findAllByIdProfesor( Long idProfesor );

}
