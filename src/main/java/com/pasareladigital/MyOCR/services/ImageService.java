package com.pasareladigital.MyOCR.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


import net.sourceforge.tess4j.ITessAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;

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
        //final String value = ",.:; áéíóúabcdefghijklmnñopqrstuvwxyz0123456789ÁÉÍÓÚABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        //tesseract.setTessVariable("tessedit_char_whitelist", value); //Especifica q solo esos caracteres seran reconocidos
        tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_LSTM_ONLY); //Especifica el motor usado
    }

    public String convertImageToText(String imagePath) throws TesseractException {

        // Carga la imagen
        File imageFile = new File(imagePath);

        // Convierte la imagen a texto
        String result="";
        result = this.tesseract.doOCR(imageFile);
        return result;
    }

    public String convertImageToText(InputStream input) throws TesseractException, IOException {
        BufferedImage imBuff = ImageIO.read(input);
        String result="";
        result = this.tesseract.doOCR(imBuff);
        return result;
    }

}