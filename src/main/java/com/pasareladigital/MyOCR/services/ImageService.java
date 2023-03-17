package com.pasareladigital.MyOCR.services;

import java.io.File;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class ImageService {

    private Environment env;
    final private Tesseract tesseract;
    @Autowired
    ImageService(Environment env) {

        this.env=env;
        tesseract = new Tesseract();
        final String pathTessdata = env.getProperty("tessdata")+"/";
        tesseract.setDatapath(pathTessdata); // Cambia esto por la ruta a la carpeta tessdata en tu proyecto
        tesseract.setLanguage("spa"); // Establece el idioma del texto a reconocer

    }

    public String convertImageToText(String imagePath) throws TesseractException {


        // Carga la imagen
        File imageFile = new File(imagePath);

        // Convierte la imagen a texto
        String result="";

        result = this.tesseract.doOCR(imageFile);


        return result;
    }

}