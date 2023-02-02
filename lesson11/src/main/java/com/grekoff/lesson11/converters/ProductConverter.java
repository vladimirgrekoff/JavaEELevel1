package com.grekoff.lesson11.converters;

import com.grekoff.lesson11.dto.ProductDto;
import com.grekoff.lesson11.dto.SelectedProductDto;
import com.grekoff.lesson11.entities.Product;
import com.grekoff.lesson11.entities.SelectedProduct;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    public SelectedProduct dtoToSelectedProduct(ProductDto productDto) {
        return new SelectedProduct(productDto.getId(), productDto.getTitle(), productDto.getPrice(), 1);
    }
    public SelectedProductDto selectedProductToSelectedProductDto(SelectedProduct selectedProduct) {
        return new SelectedProductDto(selectedProduct.getId(), selectedProduct.getTitle(), selectedProduct.getPrice(), selectedProduct.getCount());
    }

    public SelectedProduct selectedProductDtoToSelectedProduct(SelectedProductDto selectedProductDto) {
        return new SelectedProduct(selectedProductDto.getId(), selectedProductDto.getTitle(), selectedProductDto.getPrice(), selectedProductDto.getCount());
    }

}
