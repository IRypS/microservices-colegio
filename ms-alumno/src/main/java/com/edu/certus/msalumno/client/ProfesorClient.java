package com.edu.certus.msalumno.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.certus.msalumno.dto.ResponseDto;

/**
 * Feign client para la interacción con el microservicio <b>ms-profesor</b>
 */
@FeignClient( name = "ms-profesor" )
public interface ProfesorClient {


    /**
     * Método para listar los profesores visibles
     * @return Objeto <b>ResponseDto</b> con el listado de cursos <br><br>
     *         - Podría traer datos vacíos y códigos en caso de algún error
     */
    @GetMapping( "/v1/profesor" )
    public ResponseDto getAllProfesorTrue();



    /**
     * Método para obtener un profesor visible mediante su ID
     * @param id ID del profesor
     * @return Objeto <b>ResponseDto</b> con el curso encontrado <br><br>
     *         - Podría traer datos vacíos y códigos en caso de algún error
     */
    @GetMapping( "/v1/profesor/{id}" )
    public ResponseDto getProfesorTrueById( @PathVariable("id") Long idProfesor );



    /**
     * Método para obtener objetos de profesor - curso
     * @return Objeto <b>ResponseDto</b> con el listado de objetos profesor curso<br><br>
     *         - Podría traer datos vacíos y códigos en caso de algún error
     */
    @GetMapping( "/v1/profesor-curso" )
    public ResponseDto getAllProfesorCurso();



    /**
     * Método para obtener un listado de objetos profesor - curso mediante un idProfesor
     * @param idProfesor id del profesor a buscar en los registros
     * @return Objeto <b>ResponseDto</b> con el listado de objetos profesor curso<br><br>
     *         - Podría traer datos vacíos y códigos en caso de algún error
     */
    @GetMapping( "/v1/profesor-curso/profesores/{idProfesor}" )
    public ResponseDto getProfesorCursosByProfesor( @PathVariable("idProfesor") Long idProfesor );



    /**
     * Método para obtener un listado de objetos profesor - curso mediante un idCurso
     * @param idCurso id del cursop a buscar en los registros
     * @return Objeto <b>ResponseDto</b> con el listado de objetos profesor curso<br><br>
     *         - Podría traer datos vacíos y códigos en caso de algún error
     */
    @GetMapping( "/v1/profesor-curso/cursos/{idCurso}" )
    public ResponseDto getProfesorCursosByCurso( @PathVariable("idCurso") Long idCurso );

}
