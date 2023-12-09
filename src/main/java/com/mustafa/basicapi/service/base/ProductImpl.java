package com.mustafa.basicapi.service.base;

import com.mustafa.basicapi.dto.ProductDTO;

import java.util.List;

public interface ProductImpl {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    Long createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
