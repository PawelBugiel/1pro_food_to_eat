package com.pawelbugiel.foodtoeat.services;

import com.pawelbugiel.foodtoeat.dtos.ProductDTO;
import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.dtos.QueryParams;
import com.pawelbugiel.foodtoeat.exceptions.PageException;
import com.pawelbugiel.foodtoeat.exceptions.ProductNotFoundException;
import com.pawelbugiel.foodtoeat.mappers.ProductMapper;
import com.pawelbugiel.foodtoeat.models.Product;
import com.pawelbugiel.foodtoeat.repositories.ProductRepository;
import com.pawelbugiel.foodtoeat.validators.PageValidator;
import com.pawelbugiel.foodtoeat.validators.PageableValidator;
import com.pawelbugiel.foodtoeat.validators.SortDirectionValidator;
import com.pawelbugiel.foodtoeat.validators.UUID_Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, PageableValidator pageValidator) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.pageableValidator = pageValidator;
    }

//************** CREATE *************

    @Override
    public ProductDTO createProduct(ProductRequest productRequest) {
        Optional<Product> existingProduct = productRepository.findByNameAndExpiryDate(productRequest.getName(), productRequest.getExpiryDate());

        Product savedProduct;

        if (existingProduct.isEmpty()) {
            Product passedProduct = productMapper.toProduct(productRequest);
            savedProduct = productRepository.save(passedProduct);
        } else {
            Product actualProduct = existingProduct.get();
            actualProduct = Product.ProductBuilder.aProduct()
                    .withId(actualProduct.getId())
                    .withName(actualProduct.getName())
                    .withQuantity(actualProduct.getQuantity() + productRequest.getQuantity())
                    .withExpiryDate(actualProduct.getExpiryDate())
                    .build();
            savedProduct = productRepository.save(actualProduct);
        }
        return productMapper.toProductResponse(savedProduct);
    }

    //************** READ *************
    @Override
    public Page<ProductDTO> findAllProducts(QueryParams params, Pageable pageable) {
        return productRepository.findAll(pageable).map(product -> {
            ProductDTO dto = ProductDTO.ProductDTOBuilder.aProductDto()
                    .withId(product.getId())
                    .withExpiryDate(product.getExpiryDate())
                    .withName(product.getName())
                    .withQuantity(product.getQuantity())
                    .build();
            return dto;
        });
    }

    private static PageRequest getPageRequest(QueryParams queryParams) {
        return PageRequest.of(Integer.parseInt(queryParams.getPage()),
                DEFAULT_PAGE_SIZE,
                Sort.by(queryParams.getSortDirection(), queryParams.getSortBy()));
    }

    @Override
    public ProductDTO findProductById(String id) {
        var uuid = UUID_Validator.convertStringToUUID(id);
        var product = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(id));

        return productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductDTO> findProductsByPartialName(String partialName, int page, int pageSize, String sortBy, Sort.Direction sortDirection) {

        Pageable pageable = pageableValidator.validatePageable(page, pageSize, sortBy, sortDirection);
        Page<Product> products = productRepository.findByPartialName(partialName, pageable);
        return products.map(productMapper::toProductResponse);
        // -todo exceptions
        //  if (resultList.isEmpty()) throw new PageException(params.getPage()
    }

    @Override
    public List<ProductDTO> findProductsWithExpiredDate(String page, Sort.Direction direction) {
        var startPage = PageValidator.getValidPage(page);
        var validDirection = SortDirectionValidator.validDirection(direction);

        var pageRequest = PageRequest.of(startPage, DEFAULT_PAGE_SIZE, Sort.by(validDirection, "expiryDate"));
        var resultList = productRepository.findWithExpiredDate(pageRequest);

        if (resultList.isEmpty()) throw new PageException(page);

        return resultList.stream()
                .map(productMapper::toProductResponse)
                .toList();
    }


/*    private Optional<ProductDTO> findByNameAndExpiryDate(String name, LocalDate date) {
        Optional<Product> foundProduct = productRepository.findByNameAndExpiryDate(name, date);
        return (foundProduct.isEmpty()) ? Optional.empty() : foundProduct.map(ProductMapper_old::toProductResponse);
    }*/
    
    /*    @Override
    public ProductDTO updateProduct(ProductDTO productDto) {
        return null;
    }*/


//************** UPDATE *************

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {

        var validUUID = UUID_Validator.convertStringToUUID(id);
        var productToUpdate = productRepository.findById(validUUID)
                .orElseThrow(() -> new ProductNotFoundException(id));
        // #q Find out what to do with passed id and id from productDTO ? Tonight is too late for me, to do that.
        if (!id.equals(productDTO.getId().toString()))
            throw new ProductNotFoundException("A conflict between passed id and found id");

        Product newProduct = Product.ProductBuilder.aProduct()
                .withId(productToUpdate.getId())
                .withName(productDTO.getName())
                .withQuantity(productDTO.getQuantity())
                .withExpiryDate(productDTO.getExpiryDate())
                .build();

        Product savedProduct = productRepository.save(newProduct);

        return productMapper.toProductResponse(savedProduct);
    }

//************** DELETE *************

    @Override
    public ProductDTO deleteProductById(String id) {
        UUID uuid = UUID_Validator.convertStringToUUID(id);
        Product foundProduct = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(id));
        ProductDTO productDTO = productMapper.toProductResponse(foundProduct);
        productRepository.deleteById(uuid);
        return productDTO;
    }
}
