package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper.mapProductToProductDto;
import static com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper.mapProductWriteDtoToProduct;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // --- CREATE

    @Override
    public ProductDto createProduct(ProductWriteDto productWriteDto) {

        Product product = mapProductWriteDtoToProduct(productWriteDto);
        Product newProduct = productRepository.save(product);
        return mapProductToProductDto(newProduct);
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
    public Optional<ProductDto> getProductById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<ProductDto> result;
        if (isValidUUID(id)) {
            result = productRepository.findById(uuid)
                    .map(ProductAndProductDtoMapper::mapProductToProductDto);
            return result;
        }
        return Optional.empty();
    }

    private Optional<UUID> isValidUUID(String string) {
        Optional<UUID> optionalUUID = Optional.of(UUID.fromString(string));
        if(optionalUUID.isPresent())
             return Optional.of(UUID.fromString(string));
        return Optional.empty();
    }

}
