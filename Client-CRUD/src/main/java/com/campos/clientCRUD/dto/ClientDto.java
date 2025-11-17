package com.campos.clientCRUD.dto;

import com.campos.clientCRUD.entities.Client;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClientDto {

    private Long id;

    @Size(min = 3, max = 80, message = "The number of characters must be between 3 and 80!")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Size(min = 11, max = 11, message = "CPF must have 11 numbers")
    @NotBlank(message = "Name cannot be empty")
    private String cpf;

    @Positive(message = "The income must be positive")
    private Double income;

    @PastOrPresent(message = "Future date is not valid for date of birth.")
    private LocalDate birthDate;

    @PositiveOrZero(message = "the number of children must be positive or zero")
    private Integer children;

    public ClientDto() {}

    public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDto(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}
