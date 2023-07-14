package com.example.Imobiliaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Imobiliaria.Model.Category;
import com.example.Imobiliaria.Service.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    @Autowired 
    private CategoryService categoryService;
    
    @PostMapping()
    public ResponseEntity<?> criaCategoria(@RequestBody @Valid Category category){
        return ResponseEntity.ok(categoryService.create(category));

    }
    @GetMapping()
    public ResponseEntity<?> listarCategorias(){
        return ResponseEntity.ok(categoryService.listar());
    }
    
}
