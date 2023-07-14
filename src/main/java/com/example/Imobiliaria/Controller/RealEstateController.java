package com.example.Imobiliaria.Controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Imobiliaria.Model.RealEstate;
import com.example.Imobiliaria.Service.RealEstateService;
import jakarta.validation.Valid;
@RestController
@RequestMapping("realEstate")
public class RealEstateController {

    @Autowired
    private RealEstateService realEstateService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorCategoria(@PathVariable UUID id){
        if (realEstateService.validaId(id)) {
            System.out.println("ID" + id);
            return ResponseEntity.ok(realEstateService.listaPorCategoria(id));
        }
        return ResponseEntity.badRequest().body("Propriedade não encontrada");
      
    }
    @PostMapping()
    public ResponseEntity<?> cadastrarPropriedade(@RequestBody @Valid RealEstate realEstate){
        return ResponseEntity.ok(realEstateService.cadastrarRealEstate(realEstate));
    }
    @GetMapping()
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(realEstateService.listar());
    }
}
