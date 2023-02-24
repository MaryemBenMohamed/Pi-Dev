package com.example.claimsmicroservice.controllers;

import com.example.claimsmicroservice.response.MessageResponse;
import com.example.claimsmicroservice.services.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileUploaderController {
    private FileUploadService fileUploadService;

    @PostMapping("/upload/{id}")
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("files") List<MultipartFile> files, @PathVariable Long id) {


        MessageResponse message =    fileUploadService.uploadFile(files, id);


        return ResponseEntity.status(HttpStatus.OK).body(message);

    }
}
