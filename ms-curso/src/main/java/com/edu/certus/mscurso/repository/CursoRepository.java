package com.edu.certus.mscurso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.mscurso.entity.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
    
    /**
     * Busca cursos ( estado = true (1) ) 
     * @return Listado de objetos <b>CursoEntity</b>
     */
    public List<CursoEntity> findAllByEstadoTrue();


    /**
     * Busca un curso ( estado = true (1) ) con el ID ingresado
     * @param id ID del curso a buscar
     * @return Objeto <b>CursoEntity</b>
     */
    public CursoEntity findByIdAndEstadoTrue( Long id );


    /**
     * Busca un curso ( estado = true (1) ) con la descrioción ingresada.
     * @param descripcion Descripción (nombre) del curso a buscar
     * @return Objeto <b>CursoEntity</b>
     */
    public CursoEntity findByDescripcionAndEstadoTrue( String descripcion );

}
