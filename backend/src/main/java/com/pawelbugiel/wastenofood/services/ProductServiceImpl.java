package com.pawelbugiel.wastenofood.services;

import com.pawelbugiel.wastenofood.dtos.ProductRequest;
import com.pawelbugiel.wastenofood.dtos.ProductResponse;
import com.pawelbugiel.wastenofood.exceptions.ProductNotFoundException;
import com.pawelbugiel.wastenofood.mappers.ProductMapper;
import com.pawelbugiel.wastenofood.models.Product;
import com.pawelbugiel.wastenofood.repositories.ProductRepository;
import com.pawelbugiel.wastenofood.validators.PageableValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PageableValidator pageableValidator;

    private final static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, PageableValidator pageValidator) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.pageableValidator = pageValidator;
    }

//************** CREATE *************

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Optional<Product> existingProduct = productRepository
                .findByNameAndExpiryDate(productRequest.getName(), productRequest.getExpiryDate());

        Product savedProduct;

        if (existingProduct.isEmpty()) {
            Product passedProduct = productMapper.toProduct(productRequest);
            savedProduct = productRepository.save(passedProduct);
        } else {
            Product actualProduct = existingProduct.get();

            actualProduct.setQuantity(actualProduct.getQuantity() + productRequest.getQuantity());
            savedProduct = productRepository.save(actualProduct);
        }
        return productMapper.toProductResponse(savedProduct);
    }

    //************** READ *************
    @Override
    public Page<ProductResponse> findAllProducts(int page, Integer pageSize, String sortBy, Sort.Direction sortDirection) {

        Pageable pageable = createPageable(page, pageSize, sortBy, sortDirection);

        return productRepository
                .findAll(pageable)
                .map(productMapper::toProductResponse);
    }

    @Override
    public ProductResponse findProductById(UUID id) {
        Product product = findProductByIdOrThrow(id);
        return productMapper.toProductResponse(product);
    }

    private Product findProductByIdOrThrow(UUID id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Page<ProductResponse> findProductsByPartialName(
            String partialName, int page, Integer pageSize, String sortBy, Sort.Direction sortDirection) {

        Pageable pageable = createPageable(page, pageSize, sortBy, sortDirection);

        Page<Product> products = productRepository
                .findByPartialName(partialName, pageable);

        return products
                .map(productMapper::toProductResponse);
    }

    private Pageable createPageable(int page, Integer pageSize, String sortBy, Sort.Direction sortDirection) {
        return pageableValidator
                .validatePageable(page, pageSize, sortBy, sortDirection);
    }

    @Override
    public Page<ProductResponse> findProductsWithExpiredDate(
            int page, Integer pageSize, String sortBy, Sort.Direction sortDirection) {

        Pageable pageable = createPageable(page, pageSize, sortBy, sortDirection);

        Page<Product> expiredProductsPage = productRepository
                .findWithExpiredDate(pageable);

        return expiredProductsPage
                .map(productMapper::toProductResponse);
    }

//************** UPDATE *************

    @Override
    public ProductResponse updateProduct(UUID id, ProductRequest productRequest) {

        Product productToUpdate = findProductByIdOrThrow(id);

        productMapper.updateProductFromRequest(productRequest, productToUpdate);

        Product savedProduct = productRepository.save(productToUpdate);

        return productMapper.toProductResponse(savedProduct);
    }

//************** DELETE *************

    @Override
    public ProductResponse deleteProductById(UUID id) {
        Product foundProduct = findProductByIdOrThrow(id);

        ProductResponse productResponse = productMapper.toProductResponse(foundProduct);

        productRepository.deleteById(id);

        return productResponse;
    }
}
