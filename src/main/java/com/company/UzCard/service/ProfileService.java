package com.company.UzCard.service;

import com.company.UzCard.dto.ProfileDTO;
import com.company.UzCard.entity.ProfileEntity;
import com.company.UzCard.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO create(ProfileDTO dto) {
    ProfileEntity entity = new ProfileEntity();
    entity.setName(dto.getName());
    entity.setSurname(dto.getSurname());
    entity.setBirthDate(dto.getBirthDate());
    entity.setCreatedDate(LocalDateTime.now());
    entity.setMiddleName(dto.getMiddleName());

    profileRepository.save(entity);
    dto.setId(entity.getId());
    return dto;
    }

    public List<ProfileDTO> getAll() {
        return profileRepository.findAll().stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public ProfileDTO getById(Integer id) {
        ProfileEntity entity = profileRepository
                .findById(id)
                .orElseThrow(() -> new NullPointerException("Profile Not Found with this ID"));
        return toDTO(entity);
    }

    public ProfileDTO deleteById(Integer id) {
        ProfileDTO dto = getById(id);
        profileRepository.deleteById(id);
        return dto;
    }


    public ProfileEntity get(Integer id){
       return this.profileRepository.findById(id).orElseThrow(()-> new RuntimeException("Profile not found!"));
    }

    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setMiddleName(entity.getMiddleName());
        dto.setBirthDate(entity.getBirthDate());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
