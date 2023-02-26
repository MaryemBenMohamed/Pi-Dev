package com.example.claimsmicroservice.controllers;

import com.example.claimsmicroservice.dto.ClaimDto;
import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.dto.ClaimRetourDto;
import com.example.claimsmicroservice.entities.Status;
import com.example.claimsmicroservice.services.IServiceClaim;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
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
    public ResponseEntity<ClaimDto> changeStatus(@PathVariable Long id, @RequestBody ClaimDto claimDto) {

        // convert DTO to Entity
        Claim claimRequest = modelMapper.map(claimDto, Claim.class);

        Claim claim = iServiceRec.changeStatus(id, claimRequest);

        // entity to DTO
        ClaimDto claimResponse = modelMapper.map(claim, ClaimDto.class);

        return ResponseEntity.ok().body(claimResponse);
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
    @PatchMapping("/returnClaim/{id}")
    public ResponseEntity<ClaimRetourDto> returnClaim(@PathVariable Long id,@RequestBody ClaimRetourDto claimRetourDto){

        // convert DTO to Entity
        Claim claimRequest = modelMapper.map(claimRetourDto, Claim.class);

        Claim claim = iServiceRec.returnClaim(id,claimRequest);

        // entity to DTO
        ClaimRetourDto claimResponse = modelMapper.map(claim, ClaimRetourDto.class);

        return ResponseEntity.ok().body(claimResponse);
    }
    @PatchMapping("/archiveClaim")
    public Claim archiveClaim(@RequestBody Claim claim){
        return iServiceRec.archiveClaim(claim);
    }
    @PutMapping("/editClaim")
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
    @GetMapping("/Notication")
    public String Noticate() {
         return iServiceRec.Noticate();

       }










}
