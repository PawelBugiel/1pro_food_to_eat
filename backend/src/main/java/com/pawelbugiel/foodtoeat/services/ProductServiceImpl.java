package com.pawelbugiel.foodtoeat.services;

import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.dtos.ProductResponse;
import com.pawelbugiel.foodtoeat.exceptions.PageException;
import com.pawelbugiel.foodtoeat.exceptions.ProductNotFoundException;
import com.pawelbugiel.foodtoeat.mappers.ProductMapper;
import com.pawelbugiel.foodtoeat.models.Product;
import com.pawelbugiel.foodtoeat.repositories.ProductRepository;
import com.pawelbugiel.foodtoeat.validators.PageableValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 50;
    private static final String DEFAULT_SORT_BY = "name";
    private static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;
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
        Optional<Product> existingProduct = productRepository.findByNameAndExpiryDate(productRequest.getName(), productRequest.getExpiryDate());

        Product savedProduct;

        if (existingProduct.isEmpty()) {
            Product passedProduct = productMapper.toProduct(productRequest);
            savedProduct = productRepository.save(passedProduct);
        } else {
            Product actualProduct = existingProduct.get();
            actualProduct = Product.builder()
                    .id(actualProduct.getId())
                    .name(actualProduct.getName())
                    .quantity(actualProduct.getQuantity() + productRequest.getQuantity())
                    .expiryDate(actualProduct.getExpiryDate())
                    .build();
            savedProduct = productRepository.save(actualProduct);
        }
        return productMapper.toProductResponse(savedProduct);
    }

    //************** READ *************
    @Override
    public Page<ProductResponse> findAllProducts(int page, Integer pageSize, String sortBy, Sort.Direction sortDirection) {

        Pageable pageable = pageableValidator.validatePageable(page, pageSize, sortBy, sortDirection);

        return productRepository
                .findAll(pageable)
                .map(product -> {
                    ProductResponse resultProductResponses = ProductResponse.builder()
                            .id(product.getId())
                            .expiryDate(product.getExpiryDate())
                            .name(product.getName())
                            .quantity(product.getQuantity())
                            .build();
                    return resultProductResponses;
                });

        // -todo exceptions - what if empty ?
    }

    @Override
    public ProductResponse findProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductResponse> findProductsByPartialName(String partialName, int page, Integer pageSize, String sortBy, Sort.Direction sortDirection) {

        Pageable pageable = pageableValidator.validatePageable(page, pageSize, sortBy, sortDirection);

        Page<Product> products = productRepository.findByPartialName(partialName, pageable);

        return products.map(productMapper::toProductResponse);
        // -todo exceptions
        //  if (resultList.isEmpty()) throw new PageException(params.getPage()
    }

    @Override
    public List<ProductResponse> findProductsWithExpiredDate(int page, Integer pageSize, String sortBy, Sort.Direction sortDirection) {

        Pageable pageable = pageableValidator.validatePageable(page, pageSize, sortBy, sortDirection);

        var resultList = productRepository.findWithExpiredDate(pageable);

        if (resultList.isEmpty()) throw new PageException();

        return resultList.stream()
                .map(productMapper::toProductResponse)
                .toList();
        // --todo exceptions
    }

//************** UPDATE *************

    @Override
    public ProductResponse updateProduct(UUID id, ProductResponse productResponse) {
        var productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        if (!id.equals(productResponse.id()))
            throw new ProductNotFoundException(id) ;

        Product newProduct = Product.builder()
                .id(productToUpdate.getId())
                .name(productResponse.name())
                .quantity(productResponse.quantity())
                .expiryDate(productResponse.expiryDate())
                .build();

        Product savedProduct = productRepository.save(newProduct);

        return productMapper.toProductResponse(savedProduct);
    }

//************** DELETE *************

    @Override
    public ProductResponse deleteProductById(UUID id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        ProductResponse productResponse = productMapper.toProductResponse(foundProduct);
        productRepository.deleteById(id);

        return productResponse;
    }
}
