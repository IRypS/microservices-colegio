package com.edu.certus.msprofesor.util;

import java.util.List;

import com.edu.certus.msprofesor.dto.ProfesorDto;

public class UtilList {
    

    public static int getItemsCount( Object data ) {

        if (data instanceof List<?>) {
            List<?> objetoLista = (List<?>) data;
            if (!objetoLista.isEmpty() && objetoLista.get(0) instanceof ProfesorDto ) {
                return objetoLista.size();
            }
        }

        return 0;

    }

    
}
