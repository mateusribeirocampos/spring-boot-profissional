package com.campos.jpa_hibernate.config;

import com.campos.jpa_hibernate.entities.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConfigObj {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate birth_date = LocalDate.parse("25/06/1986");
    User user = new User(1L, "Maria Brown", "maria.brown", birth_date, "3599632587", "123456");

    sout


}
