package com.company.UzCard.controller;

import com.company.UzCard.dto.ProfileDTO;
import com.company.UzCard.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@Slf4j
@RestController
@RequestMapping("/profile")
@Api(tags = "Profile Control")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping
    @ApiOperation(value = "Creating new Profile")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto) {
        log.warn("New Profile added {} ", dto);
        log.error("New Profile added {} ", dto);
        ProfileDTO response = profileService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "All Profile list")
    public ResponseEntity getAll() {
        log.warn("All profiles {} ");
        log.error("All profiles {} ");
        return ResponseEntity.ok(profileService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Profile by id")
    public ResponseEntity getById(@PathVariable Integer id) {
        log.warn("Profile with this id {} ", id);
        log.error("Profile with this id {} ", id);
        return ResponseEntity.ok(profileService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Profile by id")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        log.warn("Profile with this id deleted {} ", id);
        log.error("Profile with this id deleted{} ", id);
        return ResponseEntity.ok(profileService.deleteById(id));
    }

}
