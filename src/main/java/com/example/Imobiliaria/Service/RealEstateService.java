package com.example.Imobiliaria.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Imobiliaria.Model.RealEstate;
import com.example.Imobiliaria.Repository.CategoryRepository;
import com.example.Imobiliaria.Repository.RealEstateRepository;

@Service
public class RealEstateService {
    //select * from real estate where category id = x
    @Autowired
    private RealEstateRepository realEstateRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<RealEstate> listaPorCategoria(UUID id){
        // Category category = categoryRepository.findById(id).get();
        return realEstateRepository.findByCategoryId(id);
        // return realEstateRepository.findByCategory(category);

    }
    public List<RealEstate> listar(){
        return realEstateRepository.findAll();
    }

    public boolean validaId(UUID id){
        if (categoryRepository.findById(id).isEmpty()) {
            return false;
        }
        return true;
    }
    public RealEstate cadastrarRealEstate(RealEstate realEstate){
        LocalDate currentDate = LocalDate.now();
        realEstate.setCreatedAt(currentDate);
        realEstate.setUpdatedAt(currentDate);
        return realEstateRepository.save(realEstate);
    }

}
