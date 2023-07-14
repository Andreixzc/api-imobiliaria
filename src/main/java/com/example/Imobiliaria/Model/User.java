package com.example.Imobiliaria.Model;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@Table(name = "Users")
public class User implements UserDetails {
    public User (){
        this.setActive(true);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean adm;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdAt;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("Entrou");
        System.out.println(this.adm);
        if (this.adm) {

            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    

}
