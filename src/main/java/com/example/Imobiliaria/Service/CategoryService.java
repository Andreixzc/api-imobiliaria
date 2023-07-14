package com.example.Imobiliaria.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Imobiliaria.Model.Category;
import com.example.Imobiliaria.Repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    
    public Category create(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> listar(){
        return categoryRepository.findAll();
    }
    

}
