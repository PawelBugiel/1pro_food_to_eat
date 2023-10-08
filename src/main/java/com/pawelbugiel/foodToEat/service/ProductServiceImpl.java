package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mapper.ProductWriteDtoMapper;
import com.pawelbugiel.foodToEat.mapper.ProductWriteMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import com.pawelbugiel.foodToEat.validators.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ObjectValidator<Product> productObjectValidator) {
        this.productRepository = productRepository;
    }

    // --- CREATE

    @Override
    public ProductDto createProduct(ProductWriteDto productWriteDto){

        Product product = ProductWriteMapper.mapProductDtoToProduct(productWriteDto);

        Product newProduct = productRepository.save(product);

        return ProductWriteDtoMapper.productToDto(newProduct);
    }

    // --- READ
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


}
