package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductResponse;
import com.pawelbugiel.foodToEat.dto.ProductRequest;
import com.pawelbugiel.foodToEat.exception.PageException;
import com.pawelbugiel.foodToEat.exception.ProductNotFoundException;
import com.pawelbugiel.foodToEat.mapper.ProductMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.model.ProductProperties;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import com.pawelbugiel.foodToEat.validator.*;
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
    public ProductResponse createProduct(ProductRequest productRequest) {
        var passedProduct = ProductMapper.toProduct(productRequest);

        var savedProduct = productRepository.save(passedProduct);

        return ProductMapper.toProductResponse(savedProduct);
    }

    //************** READ *************
// #q rename and unify variable names. Today it is too late - 12:49 AM.
    @Override
    public List<ProductResponse> findAllProducts(String page, Sort.Direction direction, ProductProperties sortBy) {
        var startPage = PageValidator.getValidPage(page);
        var aValidDirection = SortDirectionValidator.validDirection(direction);
        var sortByValue = SortByValidator.valid(sortBy);

        var pageRequest = PageRequest.of(startPage, PAGE_SIZE, Sort.by(aValidDirection, sortByValue));

        var resultList = productRepository.findAll(pageRequest)
                .stream()
                .map(ProductMapper::toProductResponse)
                .toList();
        if (resultList.isEmpty()) throw new PageException(page);
        return resultList;
    }

    @Override
    public ProductResponse findProductById(String id) {
        var uuid = UUID_Validator.convertStringToUUID(id);
        var product = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(id));

        return ProductMapper.toProductResponse(product);
    }

    @Override
    public List<ProductResponse> findProductsByPartialName(String partialName, String page, Sort.Direction direction, ProductProperties sortBy) {
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
                .map(ProductMapper::toProductResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> findProductsWithExpiredDate(String page, Sort.Direction direction) {
        var startPage = PageValidator.getValidPage(page);
        var validDirection = SortDirectionValidator.validDirection(direction);

        var pageRequest = PageRequest.of(startPage, PAGE_SIZE, Sort.by(validDirection, "expiryDate"));
        var resultList = productRepository.findWithExpiredDate(pageRequest);

        // #q simplify to Stream API ?
        if (resultList.isEmpty()) throw new PageException(page);

        return resultList.stream()
                .map(ProductMapper::toProductResponse)
                .toList();
    }

/*    @Override
    public ProductResponse updateProduct(ProductResponse productDto) {
        return null;
    }*/


//************** UPDATE *************

    @Override
    public ProductResponse updateProduct(String id, ProductResponse productResponse) {

        var validUUID = UUID_Validator.convertStringToUUID(id);
        var productToUpdate = productRepository.findById(validUUID)
                .orElseThrow(() -> new ProductNotFoundException(id));
        // #q Find out what to do with passed id and id from productResponse ? Tonight is too late for me, to do that.
        if (!id.equals(productResponse.getId().toString()))
            throw new ProductNotFoundException("A conflict between passed id and found id");

        Product newProduct = Product.ProductBuilder.aProduct()
                .withId(productToUpdate.getId())
                .withName(productResponse.getName())
                .withQuantity(productResponse.getQuantity())
                .withExpiryDate(productResponse.getExpiryDate())
                .build();

        Product savedProduct = productRepository.save(newProduct);

        return ProductMapper.toProductResponse(savedProduct);
    }

//************** DELETE *************

    @Override
    public ProductResponse deleteProductById(String id) {
        UUID uuid = UUID_Validator.convertStringToUUID(id);
        Product foundProduct = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(id));
        ProductResponse productResponse = ProductMapper.toProductResponse(foundProduct);
        productRepository.deleteById(uuid);
        return productResponse;
    }
}
