package com.example.claimsmicroservice.controllers;

import com.example.claimsmicroservice.EntitiesDTO.ClaimDto;
import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.entities.TypeReclamation;
import com.example.claimsmicroservice.services.IServiceClaim;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class ClaimController {
    private IServiceClaim iServiceRec;

    private ModelMapper modelMapper;

    @PostMapping("/addClaim")
    public Claim addReclamation(@RequestBody Claim claim){
        return iServiceRec.addClaim(claim);
    }

    @GetMapping("/allClaims")
    public List<Claim> getAllReclamations () {
        return iServiceRec.findAllClaims();
    }
    @GetMapping("/findClaim/{id}")
    public Claim getReclamationById (@PathVariable("id") Long id) {
        return iServiceRec.findClaimById(id);
    }


    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<ClaimDto> updatePost(@PathVariable Long id, @RequestBody ClaimDto claimDto) {

        // convert DTO to Entity
        Claim claimRequest = modelMapper.map(claimDto, Claim.class);

        Claim post = iServiceRec.changeStatus(id, claimRequest);

        // entity to DTO
        ClaimDto postResponse = modelMapper.map(post, ClaimDto.class);

        return ResponseEntity.ok().body(postResponse);
    }


    @PostMapping("/cancelClaim/{id}")
    public void cancelReclamation(@PathVariable Long id) {
        iServiceRec.cancelClaim(id);
    }

    @DeleteMapping("/removeClaim/{id}")
    public void removeReclamation(@PathVariable Long id){
        iServiceRec.removeClaim(id);
    }

    @GetMapping("/nbClaimsResolu/{d1}/{d2}")
    public Integer nbReclamationsResolu(@PathVariable ("d1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDiff, @PathVariable ("d2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateRes) {
        return iServiceRec.nbClaimsResolu(dateDiff,dateRes);
    }
    @PatchMapping("/resolveClaim")
    public Claim resolveClaim(@RequestBody Claim claim){
        return iServiceRec.resolveClaim(claim);
    }
    @PatchMapping("/returnClaim")
    public Claim returnClaim(@RequestBody Claim claim){
        return iServiceRec.returnClaim(claim);
    }
    @PatchMapping("/archiveClaim")
    public Claim archiveClaim(@RequestBody Claim claim){
        return iServiceRec.archiveClaim(claim);
    }
    @PatchMapping("/editClaim")
    public Claim editClaim(@RequestBody Claim claim) {
        return iServiceRec.editClaim(claim);
    }

    @GetMapping("/findAllByStatus")
    public ResponseEntity<List<Claim>> getAllReclamationsByStatus () {

        List<Claim> reclamations  = iServiceRec.findReclamationByStatus();

        return new ResponseEntity<>(reclamations, HttpStatus.OK);
    }

    @GetMapping("/findByDateDiff/{d1}/{d2}")
    public List<Claim> findByDateDiff(@PathVariable ("d1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable ("d2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return iServiceRec.findByDateDiff(startDate,endDate);
    }@GetMapping("/findByDateRes/{d1}/{d2}")
    public List<Claim> findByDateRes(@PathVariable ("d1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable ("d2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return iServiceRec.findByDateRes(startDate,endDate);
    }
    @GetMapping("/findClaimsByTypeReclamationForum")
    public List<Claim> findClaimsByTypeReclamationForum() {
        return iServiceRec.findClaimsByTypeReclamationForum();
    }
    @GetMapping("/findClaimsByTypeReclamationRestaurant")
    public List<Claim> findClaimsByTypeReclamationRestaurant() {
        return iServiceRec.findClaimsByTypeReclamationRestaurant();
    }


    @GetMapping("/findClaimsByTypeReclamationAcademicHome")
    public List<Claim> findClaimsByTypeReclamationAcademicHome() {
        return iServiceRec.findClaimsByTypeReclamationAcademicHome();
    }

    @GetMapping("/findClaimsByTypeReclamationReservation")
    public List<Claim> findClaimsByTypeReclamationReservation() {
        return iServiceRec.findClaimsByTypeReclamationReservation();
    }

    @GetMapping("/findClaimsByTypeReclamationSubscription")
    public List<Claim> findClaimsByTypeReclamationSubscription() {
        return iServiceRec.findClaimsByTypeReclamationSubscription();
    }

    @GetMapping("/searchByKeywords")
    public List<Claim> search(String keywords) {
        return iServiceRec.search(keywords);

    }

    /*@GetMapping("/findByTitreContaining")
    public Page<Claim> findByTitreContaining(String title) {
        return iServiceRec.findByTitreContaining(title);
    }*/








}
