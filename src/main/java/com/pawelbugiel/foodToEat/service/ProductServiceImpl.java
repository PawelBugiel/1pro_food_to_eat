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

    /*
    * ************* CREATE
    * */
    @Override
    public ProductDto createProduct(ProductWriteDto productWriteDto) {

        Product product = mapProductWriteDtoToProduct(productWriteDto);
        Product newProduct = productRepository.save(product);
        return mapProductToProductDto(newProduct);
    }

    /*
     * ************* READ
     * */
    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductAndProductDtoMapper::mapProductToProductDto)
                .toList();
    }

    //Throws: NullPointerException – if the mapping function is null or returns a null result
    @Override
    public Optional<ProductDto> getProductById(String id) {
        Optional<UUID> uuid = UUID_Converter.convertStringToUUID(id);
        if (uuid.isPresent()) {
            return productRepository.findById(uuid.get())
                    .map(ProductAndProductDtoMapper::mapProductToProductDto);
        }
        return Optional.empty();
    }
}
