package br.com.youtube.productms.controller;

import br.com.youtube.productms.dto.ProductDTO;
import br.com.youtube.productms.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO request){
        Optional<ProductDTO> response = service.create(request);
        /*
        if (response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        */
        return response.map(productDTO -> new ResponseEntity<>(response.get(), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

}
