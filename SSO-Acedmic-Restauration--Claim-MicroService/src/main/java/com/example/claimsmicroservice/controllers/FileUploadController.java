package com.example.claimsmicroservice.controllers;

import com.example.claimsmicroservice.response.MessageResponse;
import com.example.claimsmicroservice.services.FileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@AllArgsConstructor
public class FileUploadController {

    private FileUploadService fileUploadService;
    @PostMapping("/upload/{id}")
    public   ResponseEntity<MessageResponse>  upload(@RequestParam("files") List<MultipartFile> files,@PathVariable Long id) {
        MessageResponse message =    fileUploadService.uploadFile(files,id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
