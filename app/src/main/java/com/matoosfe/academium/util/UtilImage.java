package com.matoosfe.academium.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Clase para manejar las utilidades de imagen
 */
public class UtilImage {

    /**
     * Método para convertir una ImageView a bytes
     * @param imageView
     * @return
     * @throws Exception
     */
    public static byte[] convertirABytes(ImageView imageView) throws Exception {
        byte[] imagenBytes = null;
        if(imageView != null) {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imagenBytes = baos.toByteArray();
        }else{
            throw new Exception("No existe imagen para convertir");
        }
        return  imagenBytes;
    }
}
