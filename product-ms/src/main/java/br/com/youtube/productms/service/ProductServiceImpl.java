package br.com.youtube.productms.service;

import br.com.youtube.productms.dto.ProductDTO;
import br.com.youtube.productms.model.Product;
import br.com.youtube.productms.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Optional<ProductDTO> create(ProductDTO request) {

        Product product = mapper.map(request, Product.class);
        repository.saveAndFlush(product);
        ProductDTO response = mapper.map(product, ProductDTO.class);
        return Optional.of(response);
    }

    @Override
    public List<ProductDTO> getAll(){

        List<Product> products = repository.findAll();
        List<ProductDTO> responses = new ArrayList<>();

        /* mesmo exemplo que abaixo, mas com for.
        for(Product product: products){
            ProductDTO response = mapper.map(product, ProductDTO.class);
            responses.add(response);
        }
        */

        products.forEach(product -> {
            ProductDTO response = mapper.map(product, ProductDTO.class);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        Optional<Product> product = repository.findById(id);
        /*
        if(product.isPresent()){
            Product productEntity = product.get();
            ProductDTO response = mapper.map(productEntity, ProductDTO.class);
            return Optional.of(response);
        }

        return Optional.empty();
        */

        return product.map(value -> mapper.map(value, ProductDTO.class));
    }

    @Override
    public boolean inactive(Long id) {
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()){
            product.get().setAvailable(false);
            return true;
        }
        return false;
    }

    @Override
    public Optional<ProductDTO> update(Long id, ProductDTO request) {

        Optional<Product> product = repository.findById(id);
        if(product.isPresent()){
            product.get().setDescription(request.getDescription());
            product.get().setPrice(request.getPrice());
            repository.save(product.get());
            return Optional.of(mapper.map(product.get(), ProductDTO.class));
        }

        return Optional.empty();
    }


}
