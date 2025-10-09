package com.campos.jpa_hibernate.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "user_tb")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "name", nullable = false, length = 80)
    private String name;

    //@Column(name = "email", nullable = false, length = 100)
    private String email;

    //@Column(name = "birth_date", nullable = false)
    private LocalDate birth_date;

    //@Column(name = "phone", nullable = false, length = 20)
    private String phone;

    //@Column(name = "password", nullable = false, length = 15)
    private String password;

    public User() {}

    public User(Long id, String name, String email, LocalDate birth_date, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth_date = birth_date;
        this.phone = phone;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(birth_date, user.birth_date) && Objects.equals(phone, user.phone) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, birth_date, phone, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth_date=" + birth_date +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
