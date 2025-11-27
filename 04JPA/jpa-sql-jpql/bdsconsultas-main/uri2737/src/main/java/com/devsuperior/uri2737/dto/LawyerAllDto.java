package com.devsuperior.uri2737.dto;

import com.devsuperior.uri2737.projections.LawyerAllProjection;

public class LawyerAllDto {

    private Long register;
    private String name;
    private Integer customersNumber;

    public LawyerAllDto(){}

    public LawyerAllDto(Long register, String name, Integer customersNumber) {
        this.register = register;
        this.name = name;
        this.customersNumber = customersNumber;
    }

    public LawyerAllDto(LawyerAllProjection projection) {
        register = projection.getRegister();
        name = projection.getName();
        customersNumber = projection.getCustomersNumber();
    }

    public Long getRegister() {
        return register;
    }

    public void setRegister(Long register) {
        this.register = register;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomersNumber() {
        return customersNumber;
    }

    public void setCustomersNumber(Integer customersNumber) {
        this.customersNumber = customersNumber;
    }

    @Override
    public String toString() {
        return "LawyerAllDto{" +
                "register=" + register +
                ", name='" + name + '\'' +
                ", customersNumber=" + customersNumber +
                '}';
    }
}
