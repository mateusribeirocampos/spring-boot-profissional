package com.campos.jpa_hib.dto.v1.userDto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Objects;

public class UserCreateDto {

    @NotBlank(message = "Name is obligatory")
    @Size(min=3, max = 100, message = "Name must have between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Email is obligatory")
    @Email(message = "Email invalid")
    private String email;

    @NotNull(message = "Birth date is obligatory")
    @Past(message = "Birth date must not in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Phone number is obligatory")
    @Pattern(regexp = "\\d{9,11}", message = "Phone number must have between 9 and 11 digits")
    private String phone;

    @NotBlank(message = "Password is obligatory")
    @Size(min = 6, message = "Password must have minimum 6 characters")
    private String password;

    public UserCreateDto() {}

    public UserCreateDto(String name, String email, LocalDate birthDate,
                         String phone, String password) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.phone = phone;
        this.password = password;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateDto that = (UserCreateDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, birthDate, phone);
    }
}