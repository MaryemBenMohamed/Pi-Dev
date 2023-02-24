package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.entities.Status;
import com.example.claimsmicroservice.entities.TypeReclamation;
import com.example.claimsmicroservice.repositories.ClaimRepository;
import com.example.claimsmicroservice.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class ClaimService implements IServiceClaim {

    private ClaimRepository claimRepository;

    @Override
    public Claim addClaim(Claim claim) {
        claim.setDateDiff(new Date());
        claim.setStatus(Status.EN_COURS);
        return claimRepository.save(claim);
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
    public Claim returnClaim(Claim claim){
        claim.setStatus(Status.RETOUR);
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

    /*@Override
    public Page<Claim> findByTitreContaining(String title) {
        return claimRepository.findByTitreContaining(title);
    }*/


}
