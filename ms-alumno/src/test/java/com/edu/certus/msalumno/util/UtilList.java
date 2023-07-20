package com.edu.certus.msalumno.util;

import java.util.List;

import com.edu.certus.msalumno.dto.AlumnoDto;

public class UtilList {
    

    public static int getItemsCount( Object data ) {

        if (data instanceof List<?>) {
            List<?> objetoLista = (List<?>) data;
            if (!objetoLista.isEmpty() && objetoLista.get(0) instanceof AlumnoDto) {
                return objetoLista.size();
            }
        }

        return 0;

    }

    
}
