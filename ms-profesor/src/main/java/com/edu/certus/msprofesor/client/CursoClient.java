package com.edu.certus.msprofesor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.certus.msprofesor.dto.ResponseDto;


/**
 * Feign client para la interacción con el microservicio <b>ms-curso</b>
 */
@FeignClient( name = "ms-curso" )
public interface CursoClient {
    

    /**
     * Método para listar los cursos visibles
     * @return Objeto <b>ResponseDto</b> con el listado de cursos <br><br>
     *         - Podría traer datos vacíos y códigos en caso de algún error
     */
    @GetMapping( "/v1/curso" )
    public ResponseDto getAllCursoTrue();



    /**
     * Método para obtener un curso visible mediante su ID
     * @param id ID del curso
     * @return Objeto <b>ResponseDto</b> con el curso encontrado <br><br>
     *         - Podría traer datos vacíos y códigos en caso de algún error
     */
    @GetMapping( "/v1/curso/{id}" )
    public ResponseDto getCursoTrueById( @PathVariable( "id" ) Long id );


}
