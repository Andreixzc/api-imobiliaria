package com.example.Imobiliaria.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Imobiliaria.Model.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,UUID>  {
    List<User> findByEmail(String email);
    Optional<User> findById(UUID id);
}
