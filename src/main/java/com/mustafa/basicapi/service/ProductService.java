package com.mustafa.basicapi.service;

import com.mustafa.basicapi.dto.ProductDTO;
import com.mustafa.basicapi.entity.Product;
import com.mustafa.basicapi.repository.ProductRepository;
import com.mustafa.basicapi.service.base.ProductImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductImpl {
    private final ModelMapper mapper;
    private final ProductRepository repository;

    public ProductService(ModelMapper mapper, ProductRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = repository.findAll();
        return productList.stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id).orElseThrow();
        return mapper.map(product, ProductDTO.class);
    }

    @Override
    public Long createProduct(ProductDTO productDTO) {
        Product product = mapper.map(productDTO, Product.class);
        return repository.save(product).getId();
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = repository.findById(id).orElseThrow();

        if (productDTO.getName() != null) {
            product.setName(productDTO.getName());
        }
        if (productDTO.getStock() != null) {
            product.setStock(productDTO.getStock());
        }
        if (productDTO.getPrice() != null) {
            product.setPrice(productDTO.getPrice());
        }
        if (productDTO.getArrivalDate() != null) {
            product.setArrivalDate(productDTO.getArrivalDate());
        }
        if (productDTO.getArrivalTime() != null) {
            product.setArrivalTime(productDTO.getArrivalTime());
        }

        return mapper.map(repository.save(product),ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
