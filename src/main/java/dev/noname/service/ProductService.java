package dev.noname.service;

import dev.noname.entity.Category;
import dev.noname.entity.Producer;
import dev.noname.entity.Product;
import dev.noname.form.SearchForm;

import java.util.List;

public interface ProductService {
    List<Product> listAllProducts(int page, int limit);

    int countAllProducts();

    List<Product> listProductsByCategory(String categoryUrl, int page, int limit);

    int countProductsByCategory(String categoryUrl);

    List<Category> listAllCategories();

    List<Producer> listAllProducer();

    List<Product> listProductsBySearchForm(SearchForm searchForm, int page, int limit);

    int countProductsBySearchForm(SearchForm searchForm);
}
