package com.example.claimsmicroservice.repositories;

import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.entities.Status;
import com.example.claimsmicroservice.entities.TypeReclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim,Long> {
    Integer countByStatusAndDateResBetween(Status status, Date dateDiff, Date dateRes);
    List<Claim> findByStatusIsNot(Status annuler);

    long countByStatus(Status status);
    @Query("select count(c) from Claim c where c.status != 'ANNULER' and MONTH(c.dateDiff)=:month and YEAR(c.dateDiff) = YEAR(CURRENT_DATE)")
    long countAllByMonth(int month);
    @Query("select count(c) from Claim c where c.status = 'RESOLU' and MONTH(c.dateRes)=:month and YEAR(c.dateRes) = YEAR(CURRENT_DATE)")
    long countResoluByMonth(int month);

    List<Claim> findClaimsByDateDiffBetween(Date starDate, Date endDate);
    List<Claim> findClaimsByDateResBetween(Date starDate, Date endDate);

    List<Claim> findClaimsByTypeReclamation(TypeReclamation typeReclamation);

    @Query("SELECT c FROM Claim c WHERE lower(c.title) LIKE lower(concat('%',:keywords,'%')) OR lower(c.description) LIKE lower(concat('%',:keywords,'%'))")
    List<Claim> findByKeywords(@Param("keywords") String keywords);

    List<Claim> findClaimsByUsername(String username);
    long countByStatusAndUsername(Status status,String username);

    //long countClaimByUsername(String username);
    long countClaimByEmail(String email);







}
