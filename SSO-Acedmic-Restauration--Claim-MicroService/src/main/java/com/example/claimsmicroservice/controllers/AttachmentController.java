package com.example.claimsmicroservice.controllers;

import com.example.claimsmicroservice.entities.Attachment;
import com.example.claimsmicroservice.response.MessageResponse;
import com.example.claimsmicroservice.services.AttachmentService;
import com.example.claimsmicroservice.services.IServiceAttachment;
import lombok.AllArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class AttachmentController {

    private IServiceAttachment iServiceAttachment;
    @GetMapping("/findByClaim/{id}")
    public List<Attachment> findByClaim(@PathVariable Long id) {
        return iServiceAttachment.findByClaim(id);
    }

    @PostMapping("/uploadFile/{id}")
    public   ResponseEntity<MessageResponse>  upload(@RequestParam("files") List<MultipartFile> files, @PathVariable Long id) {
        MessageResponse message =    iServiceAttachment.uploadFile(files,id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = iServiceAttachment.download(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + file.getFilename() + "\"").body(file);
    }


}
