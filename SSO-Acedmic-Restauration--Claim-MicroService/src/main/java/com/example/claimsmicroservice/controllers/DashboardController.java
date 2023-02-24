package com.example.claimsmicroservice.controllers;

import com.example.claimsmicroservice.response.BarResponse;
import com.example.claimsmicroservice.response.PieResponse;
import com.example.claimsmicroservice.services.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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


}
