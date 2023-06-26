package com.edu.certus.mscurso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.certus.mscurso.entity.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
    
    /**
     * Retorna un listado de cursos cuyo estado es <b>True</b>
     * @return Listado de cursos activos
     */
    public List<CursoEntity> findAllByEstadoTrue();


    /**
     * Busca un curso con estado [True] por su ID
     * @param id ID del curso a buscar
     * @return Curso visible [True]
     */
    public CursoEntity findByIdAndEstadoTrue( Long id );


    /**
     * Busca un curso activo según su descripción
     * @param descripcion Descripción del curso
     * @return Curso activo según la descripción ingresada
     */
    public CursoEntity findByDescripcionAndEstadoTrue( String descripcion );

}
