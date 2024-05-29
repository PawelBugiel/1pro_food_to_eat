package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.exceptions.ProductNotFoundException;
import com.pawelbugiel.foodToEat.mappers.ProductMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import com.pawelbugiel.foodToEat.validators.PageValidator;
import com.pawelbugiel.foodToEat.validators.SortingValidator;
import com.pawelbugiel.foodToEat.validators.UUID_Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ProductServiceImpl implements ProductService {

    public static final int DEFAULT_PAGE_SIZE = 10;
    private final ProductRepository productRepository;
    private final PageValidator pageValidator;
    private final SortingValidator sortingValidator;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PageValidator pageValidator, SortingValidator sortingValidator) {
        this.productRepository = productRepository;
        this.pageValidator = pageValidator;
        this.sortingValidator = sortingValidator;
    }

//************** CREATE *************

    @Override
    public ProductDto createProduct(ProductWriteDto productWriteDto) {
        Product passedProduct = ProductMapper.toProduct(productWriteDto);
        Product savedProduct = productRepository.save(passedProduct);
        return ProductMapper.toProductDto(savedProduct);
    }

//************** READ *************

    @Override
    public List<ProductDto> findAllProducts(String page, Sort.Direction sort) {
        int startPage = pageValidator.getValidPage(page);
        Sort.Direction sortDirection = sortingValidator.validSortingType(sort);

        return productRepository.findAll(PageRequest.of(startPage, DEFAULT_PAGE_SIZE, Sort.by(sortDirection, "expiryDate")))
                .stream()
                .map(ProductMapper::toProductDto)
                .toList();
    }

    @Override
    public ProductDto findProductById(String id) {
        UUID uuid = UUID_Validator.convertStringToUUID(id);
        Product product = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(uuid));
        return ProductMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> findProductsByPartialName(String partialName, String page, Sort.Direction sort) {
        int startPage = pageValidator.getValidPage(page);
        Sort.Direction sortDirection = sortingValidator.validSortingType(sort);

        PageRequest pageRequest = PageRequest.of(startPage, DEFAULT_PAGE_SIZE, Sort.by(sortDirection, "expiryDate"));

        List<Product> listFetchedByRepository = productRepository.findByPartialName(partialName, pageRequest);
        return listFetchedByRepository.stream()
                .map(ProductMapper::toProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> findProductsWithExpiredDate(String page, Sort.Direction sort) {
        int startPage = pageValidator.getValidPage(page);
        sort = sortingValidator.setDescending();

        PageRequest pageRequest = PageRequest.of(startPage, DEFAULT_PAGE_SIZE, Sort.by(sort, "expiryDate"));
        List<Product> foundProducts = productRepository.findWithExpiredDate(pageRequest);
        return foundProducts.stream()
                .map(ProductMapper::toProductDto)
                .toList();
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return null;
    }


//************** UPDATE *************

/*    @Override
    public ProductDto updateProduct2(ProductDto productDto) {
        String id = productDto.getId().toString();
        Optional<ProductDto> productToUpdate = findProductById(id);
        if (productToUpdate.isEmpty()) {
            throw new ProductNotFoundException(UUID.fromString("There is no product with id " + id));
        }
        Product newProduct = Product.ProductBuilder.aProduct()
                .withId(productDto.getId())
                .withName(productDto.getName())
                .withQuantity(productDto.getQuantity())
                .withExpiryDate(productDto.getExpiryDate())
                .build();

        Product savedProduct = productRepository.save(newProduct);

        return mapProductToProductDto(savedProduct);
    }*/

//************** DELETE *************

    @Override
    public ProductDto deleteProductById(String id) {
        UUID uuid = UUID_Validator.convertStringToUUID(id);
        Product foundProduct = productRepository.findById(uuid).orElseThrow(()-> new ProductNotFoundException(uuid));
        ProductDto productDto = ProductMapper.toProductDto(foundProduct);
        productRepository.deleteById(uuid);
        return productDto;
    }
}
