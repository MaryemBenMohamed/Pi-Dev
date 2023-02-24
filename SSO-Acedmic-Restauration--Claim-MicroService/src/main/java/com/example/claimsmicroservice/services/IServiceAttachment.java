package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.entities.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IServiceAttachment {

    List<Attachment> findByClaim(Long id);

}
