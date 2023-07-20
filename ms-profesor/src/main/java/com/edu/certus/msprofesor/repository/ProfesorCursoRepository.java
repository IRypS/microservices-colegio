package com.edu.certus.msprofesor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.msprofesor.entity.ProfesorCursoEntity;

@Repository
public interface ProfesorCursoRepository extends JpaRepository< ProfesorCursoEntity, Long >{
    

    /**
     * Busca un registro ( profesor - curso ) a partir de ID's profesor y curso
     * @param idProfesor ID del profesor a buscar en un registro
     * @param idCurso ID del curso a buscar en el registro
     * @return Objeto <b>ProfesorCursoEntity</b>
     */
    public ProfesorCursoEntity findByIdProfesorAndIdCurso( Long idProfesor, Long idCurso );



    /**
     * Busca un listado de registros ( profesor - curso ) a partir de un ID de profesor
     * @param idProfesor ID del profesor a buscar en los registros
     * @return Listado de objetos <b>ProfesorCursoEntity</b>
     */
    public List<ProfesorCursoEntity> findAllByIdProfesor( Long idProfesor );



    /**
     * Busca un listado de registros ( profesor - curso ) a partir de un ID de curso
     * @param idProfesor ID del profesor a buscar en los registros
     * @return Listado de objetos <b>ProfesorCursoEntity</b>
     */
    public List<ProfesorCursoEntity> findAllByIdCurso( Long idCurso );

    
}
