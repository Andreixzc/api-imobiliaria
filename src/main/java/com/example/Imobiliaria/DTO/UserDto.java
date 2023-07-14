package com.example.Imobiliaria.DTO;


import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class UserDto {
    public UserDto(){}
    private UUID id;
    private String name;
    private String email;
    private boolean adm;
    private boolean active;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
