package com.example.claimsmicroservice.controllers;

import com.example.claimsmicroservice.responses.BarResponse;
import com.example.claimsmicroservice.responses.PieResponse;
import com.example.claimsmicroservice.services.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DashboardController {

    private DashboardService dashboardService;


    @GetMapping("/nbClaimByStatus")
    public PieResponse pieClaim() {
        return  dashboardService.pieClaim();
    }

    @GetMapping("/nbClaimByMonth")
    public BarResponse getClaims() {
        return  dashboardService.getClaims();
    }

        @GetMapping("/nbClaimByStatusAndUsername/{username}")
    public PieResponse getNbClaimByStatusAndUsername(@PathVariable String username) {
        return  dashboardService.pieClaimByStatusAndUsername(username);
    }


}
