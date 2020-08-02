package dev.noname.service;

import dev.noname.entity.Category;
import dev.noname.entity.Producer;
import dev.noname.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProducts(int page, int limit);

    List<Product> listProductsByCategory(String categoryUrl, int page, int limit);

    List<Category> listAllCategories();

    List<Producer> listAllProducer();
}
