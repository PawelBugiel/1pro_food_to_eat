package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import com.pawelbugiel.foodToEat.utils.UUID_Converter;
import com.pawelbugiel.foodToEat.validators.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper.mapProductToProductDto;
import static com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper.mapProductWriteDtoToProduct;

@Service
public class ProductServiceImpl implements ProductService {

    public static final int DEFAULT_PAGE_SIZE = 3;
    private final ProductRepository productRepository;
    private final ObjectValidator<Integer> integerObjectValidator;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ObjectValidator<Integer> integerObjectValidator) {
        this.productRepository = productRepository;
        this.integerObjectValidator = integerObjectValidator;
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
    public List<ProductDto> getAllProducts(Integer page, Sort.Direction sort) {

/*        int startPage = (page != null && page >= 0) ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;*/

        return productRepository.findAll(PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by(sort, "name")))
                .stream()
                .map(ProductAndProductDtoMapper::mapProductToProductDto)
                .toList();
    }

   /* //    @Override
    public List<ProductDto> getAllProducts222222222(Integer page, Sort.Direction sort) {
        return productRepository.findAll(PageRequest.of(page, 3, Sort.by(sort, "name")))
                .stream()
                .map(ProductAndProductDtoMapper::mapProductToProductDto)
                .toList();
    }*/

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
