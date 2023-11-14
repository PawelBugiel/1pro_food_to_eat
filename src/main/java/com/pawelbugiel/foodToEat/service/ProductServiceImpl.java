package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper.mapProductToProductWriteDto;
import static com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper.mapProductWriteDtoToProduct;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

  /*  @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ObjectValidator<Product> productObjectValidator) {
        this.productRepository = productRepository;
    }*/

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

    @Override
    public Optional<ProductDto> getProductById(UUID id) {

        Optional<ProductDto> result =  productRepository.findById(id)
                .map(ProductAndProductDtoMapper::mapProductToProductDto);

        return (result.isPresent()) ? result : Optional.empty();
    }


}
