package com.example.claimsmicroservice.controllers;

import com.example.claimsmicroservice.entities.Attachment;
import com.example.claimsmicroservice.services.IServiceAttachment;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
public class AttachmentController {

    private IServiceAttachment iServiceAttachment;
    @GetMapping("/findByClaim/{id}")
    public List<Attachment> findByClaim(@PathVariable Long id) {
        return iServiceAttachment.findByClaim(id);
    }


}
