package com.sammydev.claimmanagementservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/claims/")
public class ClaimController {

    @PostMapping("create-claim/")
    public ResponseEntity<String> createClaim(){
        return ResponseEntity.ok("Claim created");
    }
}
