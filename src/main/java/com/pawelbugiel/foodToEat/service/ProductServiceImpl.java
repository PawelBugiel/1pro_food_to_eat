package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.exceptions.PageException;
import com.pawelbugiel.foodToEat.exceptions.ProductNotFoundException;
import com.pawelbugiel.foodToEat.mappers.ProductMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import com.pawelbugiel.foodToEat.validators.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    public static final int PAGE_SIZE = 10;
    private final ProductRepository productRepository;

    private final static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//************** CREATE *************

    @Override
    public ProductDto createProduct(ProductWriteDto productWriteDto) {
        var passedProduct = ProductMapper.toProduct(productWriteDto);
        var savedProduct = productRepository.save(passedProduct);

        return ProductMapper.toProductDto(savedProduct);
    }

    //************** READ *************
// #q rename and unify variable names. Today it is too late - 12:49 AM.
    @Override
    public List<ProductDto> findAllProducts(String page, Sort.Direction direction, ProductProperties sortBy) {
        var startPage = PageValidator.getValidPage(page);
        var aValidDirection = SortDirectionValidator.validDirection(direction);
        var sortByValue = SortByValidator.valid(sortBy);

        var pageRequest = PageRequest.of(startPage, PAGE_SIZE, Sort.by(aValidDirection, sortByValue));

        var resultList = productRepository.findAll(pageRequest)
                .stream()
                .map(ProductMapper::toProductDto)
                .toList();
        if (resultList.isEmpty()) throw new PageException(page);
        return resultList;
    }

    @Override
    public ProductDto findProductById(String id) {
        var uuid = UUID_Validator.convertStringToUUID(id);
        var product = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(id));

        return ProductMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> findProductsByPartialName(String partialName, String page, Sort.Direction direction, ProductProperties sortBy) {
        var startPage = PageValidator.getValidPage(page);
        var validSortDirection = SortDirectionValidator.validDirection(direction);
        var sortByValue = SortByValidator.valid(sortBy);
        // #q to implement a validation of the partialName param

        var pageRequest = PageRequest.of(startPage, PAGE_SIZE, Sort.by(validSortDirection, sortByValue));
        var resultList = productRepository.findByPartialName(partialName, pageRequest);
        // #q if there are no results at all, also throws the exception. This approach is not as good as it should
        // All similar cases works the same.
        if (resultList.isEmpty()) throw new PageException(page);

        return resultList.stream()
                .map(ProductMapper::toProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> findProductsWithExpiredDate(String page, Sort.Direction direction) {
        var startPage = PageValidator.getValidPage(page);
        var validDirection = SortDirectionValidator.validDirection(direction);

        var pageRequest = PageRequest.of(startPage, PAGE_SIZE, Sort.by(validDirection, "expiryDate"));
        var resultList = productRepository.findWithExpiredDate(pageRequest);

        // #q simplify to Stream API ?
        if (resultList.isEmpty()) throw new PageException(page);

        return resultList.stream()
                .map(ProductMapper::toProductDto)
                .toList();
    }

/*    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return null;
    }*/


//************** UPDATE *************

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {

        var validUUID = UUID_Validator.convertStringToUUID(id);
        var productToUpdate = productRepository.findById(validUUID)
                .orElseThrow(() -> new ProductNotFoundException(id));
        // #q Find out what to do with passed id and id from productDto ? Tonight is too late for me, to do that.
        if (!id.equals(productDto.getId().toString()))
            throw new ProductNotFoundException("A conflict between passed id and found id");

        Product newProduct = Product.ProductBuilder.aProduct()
                .withId(productToUpdate.getId())
                .withName(productDto.getName())
                .withQuantity(productDto.getQuantity())
                .withExpiryDate(productDto.getExpiryDate())
                .build();

        Product savedProduct = productRepository.save(newProduct);

        return ProductMapper.toProductDto(savedProduct);
    }

//************** DELETE *************

    @Override
    public ProductDto deleteProductById(String id) {
        UUID uuid = UUID_Validator.convertStringToUUID(id);
        Product foundProduct = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(id));
        ProductDto productDto = ProductMapper.toProductDto(foundProduct);
        productRepository.deleteById(uuid);
        return productDto;
    }
}
