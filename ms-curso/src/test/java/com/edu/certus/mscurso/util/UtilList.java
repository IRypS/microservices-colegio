package com.edu.certus.mscurso.util;

import java.util.List;

import com.edu.certus.mscurso.dto.CursoDto;


public class UtilList {
    

    public static int getItemsCount( Object data ) {

        if (data instanceof List<?>) {
            List<?> objetoLista = (List<?>) data;
            if ( !objetoLista.isEmpty() && objetoLista.get(0) instanceof CursoDto ) {
                return objetoLista.size();
            }
        }

        return 0;

    }

    
}
