package com.example.Imobiliaria.Repository;


import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Imobiliaria.Model.Category;



public interface CategoryRepository extends JpaRepository<Category,UUID> {

}
