package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.entities.Claim;
import org.springframework.data.domain.Page;


import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface IServiceClaim {
     Claim addClaim (Claim claim);
     List<Claim> findAllClaims();
     Claim findClaimById(Long id);
     Claim changeStatus(Long id,Claim claimRequest);
     void cancelClaim(Long id);
     void removeClaim(Long id);
     Integer nbClaimsResolu(Date dateDiff, Date dateRes);
     Claim resolveClaim(Claim claim);
     Claim returnClaim(Claim claim);
     Claim archiveClaim(Claim claim);
     Claim editClaim(Claim claim);
     List<Claim> findReclamationByStatus();
     List<Claim> findByDateDiff(Date startDate, Date endDate);
     List<Claim> findByDateRes(Date startDate, Date endDate);

     List<Claim> findClaimsByTypeReclamationForum();
     List<Claim> findClaimsByTypeReclamationRestaurant();

     List<Claim> findClaimsByTypeReclamationAcademicHome();
     List<Claim> findClaimsByTypeReclamationReservation();
     List<Claim> findClaimsByTypeReclamationSubscription();
     List<Claim> search(String keywords);
     //Page<Claim> findByTitreContaining(String title);





}
