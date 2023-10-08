package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mapper.ProductAndProductDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import com.pawelbugiel.foodToEat.validators.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pawelbugiel.foodToEat.mapper.ProductAndProductDtoMapper.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ObjectValidator<Product> productObjectValidator) {
        this.productRepository = productRepository;
    }

    // --- CREATE

    @Override
    public ProductWriteDto createProduct(ProductWriteDto productWriteDto) {

        Product product = mapProductWriteDtoToProduct(productWriteDto);

        Product newProduct = productRepository.save(product);

        return mapProductToProductWriteDto(newProduct);
    }

    // --- READ
    @Override
    public List<ProductDto> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(ProductAndProductDtoMapper::mapProductToProductDto)
                .toList();
    }


}
