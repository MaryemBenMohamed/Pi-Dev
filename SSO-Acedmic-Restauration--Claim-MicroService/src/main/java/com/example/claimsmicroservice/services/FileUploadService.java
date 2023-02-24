package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.entities.Attachment;
import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.repositories.AttachmentRepository;
import com.example.claimsmicroservice.repositories.ClaimRepository;
import com.example.claimsmicroservice.response.MessageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileUploadService {
    private AttachmentRepository attachmentRepository;
    private ClaimRepository claimRepository;
    private final Path root = Paths.get("uploads");
    public MessageResponse uploadFile(List<MultipartFile> files, Long id){
        Claim reclamation = claimRepository.findById(id).orElse(null);
        reclamation.setId(id);
        try {
            for (MultipartFile file: files) {
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                Attachment pieceJoint = new Attachment();
                pieceJoint.setClaim(reclamation);
                pieceJoint.setPath(file.getOriginalFilename());
                attachmentRepository.save(pieceJoint);
            }
        }  catch (IOException e) {
            e.printStackTrace();
            return new MessageResponse(false, "Attention", "not effected");
        }

        return  new MessageResponse(true, "Success", "effected");

    }
}
