package com.example.Imobiliaria.Repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Imobiliaria.Model.Category;
import com.example.Imobiliaria.Model.RealEstate;


public interface RealEstateRepository extends JpaRepository<RealEstate,UUID> {
    List<RealEstate> findByCategory(Category category);
    List<RealEstate> findByCategoryId(UUID id);
}
