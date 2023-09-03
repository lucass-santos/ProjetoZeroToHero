package br.com.youtube.productms.service;


import br.com.youtube.productms.dto.ProductDTO;

import java.util.Optional;

public interface ProductService {

    Optional<ProductDTO> create(ProductDTO request);
}
