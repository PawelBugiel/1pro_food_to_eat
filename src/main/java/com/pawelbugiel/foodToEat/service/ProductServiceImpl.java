package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import com.pawelbugiel.foodToEat.utilities.UUID_Converter;
import com.pawelbugiel.foodToEat.validators.ObjectValidator;
import com.pawelbugiel.foodToEat.validators.PageValidator;
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

    public static final int DEFAULT_PAGE_SIZE = 5;
    public static final Sort.Direction ASC_SORTING = Sort.Direction.ASC;
    public static final Sort.Direction DESC_SORTING = Sort.Direction.DESC;
    private final ProductRepository productRepository;
    private final PageValidator pageValidator;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ObjectValidator<Integer> integerObjectValidator, PageValidator pageValidator) {
        this.productRepository = productRepository;
        this.pageValidator = pageValidator;
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
     * ************* FIND
     * */
    @Override
    public List<ProductDto> findAllProducts(String page, Sort.Direction sort) {
        int startPage = pageValidator.getValidPage(page);
        Sort.Direction sortDirection = sort != null ? sort : ASC_SORTING;

        return productRepository.findAll(PageRequest.of(startPage, DEFAULT_PAGE_SIZE, Sort.by(sortDirection, "expiryDate")))
                .stream()
                .map(ProductAndProductDtoMapper::mapProductToProductDto)
                .toList();
    }

    @Override
    public Optional<ProductDto> findProductById(String id) {
        Optional<UUID> uuid = UUID_Converter.convertStringToUUID(id);
        if (uuid.isPresent()) {
            return productRepository.findById(uuid.get())
                    .map(ProductAndProductDtoMapper::mapProductToProductDto);
        }
        return Optional.empty();
    }

    @Override
    public List<ProductDto> findProductsByPartialName(String partialName, String page, Sort.Direction sort) {
        int startPage = pageValidator.getValidPage(page);
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(startPage, DEFAULT_PAGE_SIZE, Sort.by(sortDirection, "expiryDate"));

        List<Product> listFetchedByRepository = productRepository.findByPartialName(partialName, pageRequest);
        List<ProductDto> resultList = listFetchedByRepository.stream()
                .map(ProductAndProductDtoMapper::mapProductToProductDto)
                .toList();
        return resultList;
    }

    @Override
    public List<ProductDto> findProductsWithExpiryDateUntilToday(String page, Sort.Direction sort) {
        int startPage = pageValidator.getValidPage(page);
        Sort.Direction sortDirection = sort != null ? sort : DESC_SORTING;

        PageRequest pageRequest = PageRequest.of(startPage, DEFAULT_PAGE_SIZE, Sort.by(sortDirection, "expiryDate"));
        List<Product> foundProducts = productRepository.findProductsWithExpiredDate(pageRequest);
        List<ProductDto> resultList = foundProducts.stream()
                .map(ProductAndProductDtoMapper::mapProductToProductDto)
                .toList();

        return resultList;
    }
}
