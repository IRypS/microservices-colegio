package com.edu.certus.mscurso.service;

import com.edu.certus.mscurso.dto.CursoDto;
import com.edu.certus.mscurso.dto.ResponseDto;

public interface CursoService {


    /**
     * Obtiene un listado de todos los cursos existentes
     * @param onlyTrue Valor <b>true</b> si se buscarán sólo los registros visibles
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y listado de cursos si se encuentran datos. <br><br>
     *         - Código exitoso si no se encuentran cursos. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto getAllCurso( boolean onlyTrue );



    /**
     * Obtiene un curso a partir de un ID proporcionado
     * @param idCurso ID del curso a buscar
     * @param onlyTrue Valor <b>true</b> si se buscará el id sólo en los registros visibles
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso e información del curso si se encuentra el ID solicitado. <br><br>
     *         - Código exitoso si no se encuentran el curso solicitado. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto getCursoById( Long idCurso, boolean onlyTrue );



    /**
     * Crea un curso a partir de un Objeto <b>CursoDto</b>
     * @param cursoDto Objeto <b>CursoDto</b> con los datos a crear 
     * <b>(El objeto a crear no debe estar visible y registrado previamente)</b>
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y curso creado si se registra exitósamente. <br><br>
     *         - Código fallido y curso repetido si se encuentra un curso ya registrado. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto createCurso( CursoDto cursoDto );



    /**
     * Actualiza un curso a partir de un Objeto <b>CursoDto</b>
     * @param cursoDto Objeto <b>CursoDto</b> con el ID y los datos a actualizar. 
     * <b>(No actualiza el estado)</b>
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y curso si se actualiza exitósamente. <br><br>
     *         - Código fallido si no se encuentra un curso para actualizar. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto updateCurso( CursoDto cursoDto );



    /**
     * Borra lógicamente un curso visible
     * @param idCurso ID de un curso existente a borrar
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso si el curso se borra exitósamente. <br><br>
     *         - Código fallido si no se encuentra un curso para borrar. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto deleteCursoById( Long idCurso );
    
}