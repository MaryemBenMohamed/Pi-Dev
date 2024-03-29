package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.entities.Status;
import com.example.claimsmicroservice.entities.TypeReclamation;
import com.example.claimsmicroservice.repositories.ClaimRepository;
import com.example.claimsmicroservice.usermcroservices.UserDtoClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class ClaimService implements IServiceClaim {

    private ClaimRepository claimRepository;
    private JavaMailSender javaMailSender;

    @Override
    public Claim addClaim(Claim claim, String username) {
        claim.setDateDiff(new Date());
        claim.setStatus(Status.EN_COURS);
        claim.setUsername(username);
        //return claimRepository.save(claim);

        Claim newClaim = claimRepository.save(claim);
        sendClaimConfirmationEmail(newClaim);
        return newClaim;
    }

    @Override
    public List<Claim> findAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public Claim findClaimById(Long id) {
        return claimRepository.findById(id).orElse(null);
    }

    @Override
    public Claim changeStatus(Long id,Claim claimRequest) {

        Claim claim = claimRepository.findById(id).orElse(null);

        claim.setStatus(claimRequest.getStatus());
        return claimRepository.save(claim);
    }


    @Override
    public void cancelClaim(Long id) {
        Claim claim = claimRepository.findById(id).orElse(null);
        if (claim.getStatus().equals(Status.EN_COURS)){
                claim.setStatus(Status.ANNULER);
        }
        claimRepository.save(claim);
    }

    @Override
    public void removeClaim(Long id) {
        Claim claim = claimRepository.findById(id).orElse(null);
        if (claim.getStatus().equals(Status.ANNULER)){
            claimRepository.deleteById(id);
        }
    }

    @Override
    public Integer nbClaimsResolu(Date dateDiff, Date dateRes) {
        return claimRepository.countByStatusAndDateResBetween(Status.RESOLU,dateDiff,dateRes);
    }
    @Override
    public Claim resolveClaim(Claim claim){
        claim.setDateRes(new Date());
        claim.setStatus(Status.RESOLU);
        return claimRepository.save(claim);
    }
    @Override
    public Claim returnClaim(Long id,Claim claimRequest){
        Claim claim = claimRepository.findById(id).orElse(null);
        claim.setStatus(Status.RETOUR);
        claim.setCause(claimRequest.getCause());
        return claimRepository.save(claim);
    }
    @Override
    public Claim archiveClaim(Claim claim){
        claim.setStatus(Status.ARCHIVER);
        return claimRepository.save(claim);
    }
    @Override
    public Claim editClaim(Claim claim){
        claim.setStatus(Status.MODIFIER);
        return claimRepository.save(claim);
    }

    @Override
    public List<Claim> findReclamationByStatus() {

        return claimRepository.findByStatusIsNot(Status.ANNULER);
    }

    @Override
    public List<Claim> findByDateDiff(Date startDate, Date endDate) {
        return claimRepository.findClaimsByDateDiffBetween(startDate,endDate);
    }

    @Override
    public List<Claim> findByDateRes(Date startDate, Date endDate) {
        return claimRepository.findClaimsByDateResBetween(startDate,endDate);
    }

    @Override
    public List<Claim> findClaimsByTypeReclamationForum() {
        return claimRepository.findClaimsByTypeReclamation(TypeReclamation.FORUM);
    }

    @Override
    public List<Claim> findClaimsByTypeReclamationRestaurant() {
        return claimRepository.findClaimsByTypeReclamation(TypeReclamation.RESTAURANT);
    }


    @Override
    public List<Claim> findClaimsByTypeReclamationAcademicHome() {
        return claimRepository.findClaimsByTypeReclamation(TypeReclamation.ACADEMIC_HOME);
    }

    @Override
    public List<Claim> findClaimsByTypeReclamationReservation() {
        return claimRepository.findClaimsByTypeReclamation(TypeReclamation.RESERVATION);
    }

    @Override
    public List<Claim> findClaimsByTypeReclamationSubscription() {
        return claimRepository.findClaimsByTypeReclamation(TypeReclamation.SUBSCRIPTION);
    }

    @Override
    public List<Claim> search(String keywords) {
            return claimRepository.findByKeywords(keywords);

    }
    @Scheduled(cron = "*/60 * * * * *")
    @Override
    public String Noticate() {
        String msg = "";
        List<Claim> claims = claimRepository.findAll();
        for (Claim claim : claims) {
            if (claim.getStatus().equals(Status.EN_COURS)) {
                return msg = "!!! Claim RECEIVED! Time to verify";
            }

        }
        //return msg+ claimRepository.countByStatus(Status.EN_COURS);
        return msg;

    }

    @Override
    public List<Claim> findByUsername(String username) {

            return claimRepository.findClaimsByUsername(username);
        }


   /* @Override
    public void banUserToAddClaim(String username,Long id) {
        if (claimRepository.countClaimByUsername(username)>2)
            claimRepository.deleteById(id);
        else {
            throw new NotFoundException("User with id: " + id + " not found");
        }
    }*/


    @Override
    public void banUserToAddClaim(String email,Long id) {
        if (claimRepository.countClaimByEmail(email)>2)
            claimRepository.deleteById(id);
        else {
            throw new NotFoundException("User with email: " + email + " not permitted");
        }
    }

    @Override
    public void banSave (Claim claim,String email) {
        if (claimRepository.countClaimByEmail(email)<2)
            claimRepository.save(claim);
        else {
            throw new NotFoundException("User with email: " + claim + " not permitted");
        }
    }



    public void sendClaimConfirmationEmail(Claim claim) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(claim.getEmail());
        mailMessage.setSubject("Confirmation");
        mailMessage.setText("Good morning " + claim.getTitle() + ",\n\n" +
                "Your Claim has been considered for the " + claim.getDateDiff() + ".\n\n" +
                "Cordially,\n\n" +
                "Hot-Stuff-Dev");
        javaMailSender.send(mailMessage);
    }


}
