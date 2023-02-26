package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.entities.Attachment;
import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.repositories.AttachmentRepository;
import com.example.claimsmicroservice.repositories.ClaimRepository;
import com.example.claimsmicroservice.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
@AllArgsConstructor
public class FileUploadService {

    private AttachmentRepository attachmentRepository;
    private ClaimRepository claimRepository;
    private final Path root = Paths.get("uploads");
    public MessageResponse uploadFile(List<MultipartFile> files, Long id){
        Claim claim = claimRepository.findById(id).orElse(null);
        claim.setId(id);
        try {
            for (MultipartFile file: files) {
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                Attachment attachment = new Attachment();
                attachment.setClaim(claim);
                attachment.setPath(file.getOriginalFilename());
                attachmentRepository.save(attachment);
            }
        }  catch (IOException e) {
            e.printStackTrace();
            return new MessageResponse(false, "Attention", "Opération non effectuée");
        }

        return  new MessageResponse(true, "Succès", "Opération effectuée");

    }



}
