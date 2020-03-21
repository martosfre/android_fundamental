package com.matoosfe.academium.util;


import android.widget.BaseAdapter;

/**
 * Clase para administrar las utilidades del Combo
 */
public class SpinnerUtil {


    /**
     * Método para obtener la posición de adaptador por valor
     * @param adapter
     * @param value
     * @return
     */
    public static int getPositionByElement(BaseAdapter adapter, String value) {
        for(int i = 0; i < adapter.getCount(); i++) {
            if(adapter.getItem(i).equals(value)) {
                return i;
            }
        }
        return 0;
    }
}
