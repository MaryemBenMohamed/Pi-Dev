package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.entities.Attachment;
import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.repositories.AttachmentRepository;
import com.example.claimsmicroservice.repositories.ClaimRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
@AllArgsConstructor
public class AttachmentService implements IServiceAttachment{
    private AttachmentRepository attachmentRepository;
    private ClaimRepository claimRepository;
    @Override
    public List<Attachment> findByClaim(Long id) {
        Claim claim = claimRepository.findById(id).orElse(null);
        claim.setId(id);
        return attachmentRepository.findByClaim(claim);
    }



}
