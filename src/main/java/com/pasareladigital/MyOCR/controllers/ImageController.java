package com.pasareladigital.MyOCR.controllers;

import com.pasareladigital.MyOCR.services.ImageService;
import com.pasareladigital.MyOCR.util.MyFile;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.net.URI;

@Controller
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private Environment env;

    @PostMapping(value = "/image-to-text", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> imageToText(@RequestParam("image") MultipartFile file)  {
        try {
            String result = imageService.convertImageToText(file.getInputStream());
            return ResponseEntity.ok(result);

        } catch (Exception exception) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: "+exception.getMessage());

        }

    }

}