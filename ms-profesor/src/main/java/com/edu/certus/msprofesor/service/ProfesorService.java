package com.edu.certus.msprofesor.service;

import com.edu.certus.msprofesor.dto.ProfesorDto;
import com.edu.certus.msprofesor.dto.ResponseDto;

public interface ProfesorService {

    /**
     * Obtiene un listado de todos los profesores existentes
     * @param onlyTrue Valor <b>true</b> si se buscarán sólo los registros visibles
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y listado de profesores si se encuentran datos. <br><br>
     *         - Código exitoso si no se encuentran profesores. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto getAllProfesor( boolean onlyTrue );



    /**
     * Obtiene un profesor a partir de un ID proporcionado
     * @param idProfesor ID del profesor a buscar
     * @param onlyTrue Valor <b>true</b> si se buscará el id sólo en los registros visibles
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso e información del profesor si se encuentra el ID solicitado. <br><br>
     *         - Código exitoso si no se encuentran el profesor solicitado. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto getProfesorById( Long idProfesor, boolean onlyTrue );



    /**
     * Crea un profesor a partir de un Objeto <b>ProfesorDto</b>
     * @param profesorDto Objeto <b>ProfesorDto</b> con los datos a crear 
     * <b>(El objeto a crear no debe estar visible y registrado previamente)</b>
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y profesor creado si se registra exitósamente. <br><br>
     *         - Código fallido y profesor repetido si se encuentra un profesor ya registrado. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto createProfesor( ProfesorDto profesorDto );



    /**
     * Actualiza un profesor a partir de un Objeto <b>ProfesorDto</b>
     * @param profesorDto Objeto <b>ProfesorDto</b> con el ID y los datos a actualizar. 
     * <b>(No actualiza el estado)</b>
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y profesor si se actualiza exitósamente. <br><br>
     *         - Código fallido si no se encuentra un profesor para actualizar. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto updateProfesor( ProfesorDto profesorDto );



    /**
     * Borra lógicamente un profesor visible
     * @param idProfesor ID de un profesor existente a borrar
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso si el profesor se borra exitósamente. <br><br>
     *         - Código fallido si no se encuentra un profesor para borrar. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto deleteProfesorById( Long idProfesor );
    
}
