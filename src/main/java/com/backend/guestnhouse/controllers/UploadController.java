package com.backend.guestnhouse.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/upload")
public class UploadController {

	@PostMapping("/uploadMultiple")
    public ResponseEntity<Void> handlemultipleImageFileUpload(@RequestParam("images") MultipartFile[] images) {
        try {
            String fileName = System.getProperty("user.dir");
            Path path = Paths.get(fileName + "/uploadsAll");

            for(MultipartFile file : images ) {
                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                    File fileToSave = new File(
                    path + "/" + file.getOriginalFilename());
                    file.transferTo(fileToSave);
                } else {
                    File fileToSave = new File(
                    path + "/" + file.getOriginalFilename());
                    file.transferTo(fileToSave);
                }
            }
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (IOException ioe) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
	
	@PostMapping("/uploadUserImage/{fileName}")
    public String handleuserImageFileUpload(@RequestParam("userImage") MultipartFile userImage,@PathVariable(value="fileName") String fileName) {
        try {
            String directoryName = System.getProperty("user.dir");
            Path path = Paths.get(directoryName + "/uploads");

            if (!Files.exists(path)) {
                Files.createDirectory(path);
                File fileToSave = new File(path + "/" + fileName);
                userImage.transferTo(fileToSave);
            } else {
                File fileToSave = new File(path + "/" + fileName);
                userImage.transferTo(fileToSave);
            }
        } catch (IOException ioe) {
            return "error";
        }
        return userImage.getOriginalFilename();
    }
}
