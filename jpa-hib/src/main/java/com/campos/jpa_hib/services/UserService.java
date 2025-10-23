package com.campos.jpa_hib.services;

import com.campos.jpa_hib.dto.v1.userDto.UserCreateDto;
import com.campos.jpa_hib.dto.v1.userDto.UserResponseDto;
import com.campos.jpa_hib.dto.v1.userDto.UserUpdateDto;
import com.campos.jpa_hib.entities.User;
import com.campos.jpa_hib.mapper.UserMapper;
import com.campos.jpa_hib.repositories.UserRepository;
import com.campos.jpa_hib.services.exceptions.DatabaseException;
import com.campos.jpa_hib.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  /* public List<User> findAll() {
    return userRepository.findAll();
  }*/

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toResponseDtoList(users);
    }


  /*public User findById(Long id) {
    Optional<User> obj = userRepository.findById(id);
    return obj.orElseThrow(() -> new ResourceNotFoundException(id));
  }*/

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return userMapper.toResponseDto(user);
    }

  /*public User create(User user) {
    return userRepository.save(user);
  }*/

    @Transactional
    public UserResponseDto create(UserCreateDto dto) {
        User user = userMapper.toEntity(dto);
        User saveUser = userRepository.save(user);
        return userMapper.toResponseDto(saveUser);
    }

  /*public User update(Long id, User obj) {
    try {
      User entity = userRepository.getReferenceById(id);
      updateData(entity, obj);
      return userRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  public void updateData(User entity, User obj) {
    entity.setName(obj.getName());
    entity.setEmail(obj.getEmail());
    entity.setPhone(obj.getPhone());
  }*/

    @Transactional
    public UserResponseDto update(Long id, UserUpdateDto dto) {
        try{
            User user = userRepository.getReferenceById(id);
            userMapper.updateEntityFromDto(dto, user);

            User updatedUser = userRepository.save(user);
            return userMapper.toResponseDto(updatedUser);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
