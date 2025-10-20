package com.campos.jpa_hib.mapper;

import com.campos.jpa_hib.dto.v1.UserCreateDto;
import com.campos.jpa_hib.dto.v1.UserResponseDto;
import com.campos.jpa_hib.dto.v1.UserSummaryDto;
import com.campos.jpa_hib.dto.v1.UserUpdateDto;
import com.campos.jpa_hib.entities.User;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    /**
     * Convert Entity to DTO of response
     * 🔒 hire: email, phone, password
     */
    public UserResponseDto toResponseDto(User entity) {
        if (entity == null) return null;

        return new UserResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getBirthDate()
        );
    }

    /**
     * Convert entity to DTO summary
     * 🔒 more minimalist: only id and name
     */
    public UserSummaryDto toSummaryDto(User entity) {
        if (entity == null) return null;

        return new UserSummaryDto(
                entity.getId(),
                entity.getName()
        );
    }
    /*
    * convert entities list to DTOs list
    * */

    public List<UserResponseDto> toResponseDtoList(List<User> entities) {
        if (entities == null) return null;

        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    /*
    * Convert CreateDto to entity
    * Receive: name, email, birthDate, phone and password
    * */
    public User toEntity(UserCreateDto dto) {
        if (dto == null) return null;

        User entity = new User();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    /*
    * Update entity from UpdateDto
    * update: name, email, phone, birthDate
    * do not update: password
    * */
    public void updateEntityFromDto(UserUpdateDto dto, User entity) {
        if (dto == null || entity == null) return;

        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null) {
            entity.setPhone(dto.getPhone());
        }
        if (dto.getBirthDate() != null) {
            entity.setBirthDate(dto.getBirthDate());
        }
    }
}
