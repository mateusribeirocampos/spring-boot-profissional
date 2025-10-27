package com.campos.jpa_hib.resources;

import com.campos.jpa_hib.dto.v1.userDto.UserCreateDto;
import com.campos.jpa_hib.dto.v1.userDto.UserResponseDto;
import com.campos.jpa_hib.dto.v1.userDto.UserUpdateDto;
import com.campos.jpa_hib.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users/v1")
public class UserResource {

    @Autowired
    private UserService userService;

    /*@GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }*/
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    /*@GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }*/

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findById(@Valid @PathVariable Long id) {
        UserResponseDto userDto = userService.findById(id);
        return ResponseEntity.ok().body(userDto);
    }

    /*@PostMapping()
    public ResponseEntity<User> create(@RequestBody User user) {
    user = userService.create(user);
    URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(user.getId())
            .toUri();
    return ResponseEntity.created(uri).body(user);
    }*/

    @PostMapping()
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto dto) {
        UserResponseDto created = userService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    /*@PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        user = userService.update(id, user);
        return ResponseEntity.ok().body(user);
    }*/

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> update(@Valid @PathVariable Long id,@Valid @RequestBody UserUpdateDto dto) {
        UserResponseDto updatedUser = userService.update(id, dto);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
    }
}
