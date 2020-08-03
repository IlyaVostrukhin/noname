package dev.noname.service.impl;

import dev.noname.entity.Category;
import dev.noname.entity.Producer;
import dev.noname.entity.Product;
import dev.noname.exception.InternalServerErrorException;
import dev.noname.form.SearchForm;
import dev.noname.jdbc.JDBCUtils;
import dev.noname.jdbc.ResultSetHandler;
import dev.noname.jdbc.ResultSetHandlerFactory;
import dev.noname.jdbc.SearchQuery;
import dev.noname.service.ProductService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ProductServiceImpl implements ProductService {

    private static final ResultSetHandler<List<Product>> productsResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);

    private final ResultSetHandler<List<Category>> categoryResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.CATEGORY_RESULT_SET_HANDLER);

    private final ResultSetHandler<List<Producer>> producerResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.PRODUCER_RESULT_SET_HANDLER);

    private final ResultSetHandler<Integer> countResultSetHandler =
            ResultSetHandlerFactory.getCountResultSetHandler();

    private DataSource dataSource;

    public ProductServiceImpl(DataSource dataSource) {
//        super();
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> listAllProducts(int page, int limit) {
        try (Connection connection = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(connection, "select p.*, c.name as category, pr.name " +
                    "as producer from product p, producer pr, category c where c.id=p.id_category " +
                    "and pr.id=p.id_producer limit ? offset ?", productsResultSetHandler, limit, offset);
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    @Override
    public List<Product> listProductsByCategory(String categoryUrl, int page, int limit) {
        try (Connection connection = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(connection, "select p.*, c.name as category, pr.name " +
                            "as producer from product p, producer pr, category c where c.url=? and c.id=p.id_category " +
                            "and pr.id=p.id_producer order by p.id limit ? offset ?",
                    productsResultSetHandler, categoryUrl, limit, offset);
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    @Override
    public List<Category> listAllCategories() {
        try (Connection connection = dataSource.getConnection()) {
            return JDBCUtils.select(connection, "select * from category order by id", categoryResultSetHandler);
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    @Override
    public List<Producer> listAllProducer() {
        try (Connection connection = dataSource.getConnection()) {
            return JDBCUtils.select(connection, "select * from producer order by name", producerResultSetHandler);
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    @Override
    public int countAllProducts() {
        try (Connection connection = dataSource.getConnection()) {
            return JDBCUtils.select(connection, "select count(*) from product",
                    countResultSetHandler);
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    @Override
    public int countProductsByCategory(String categoryUrl) {
        try (Connection connection = dataSource.getConnection()) {
            return JDBCUtils.select(connection, "select count(p.*) from product p, category c " +
                    "where c.id=p.id_category and c.url=?", countResultSetHandler, categoryUrl);
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    @Override
    public List<Product> listProductsBySearchForm(SearchForm searchForm, int page, int limit) {
        try (Connection connection = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            SearchQuery searchQuery = buildSearchQuery("p.*, c.name as category, pr.name as producer", searchForm);
            searchQuery.getSql().append(" order by p.id limit ? offset ?");
            searchQuery.getParams().add(limit);
            searchQuery.getParams().add(offset);
            return JDBCUtils.select(connection, searchQuery.getSql().toString(),
                    productsResultSetHandler, searchQuery.getParams().toArray());
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    protected SearchQuery buildSearchQuery(String selectFields, SearchForm form) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select ");
        sql.append(selectFields).append(" from product p, category c, producer pr where pr.id=p.id_producer and " +
                "c.id=p.id_category and (p.name ilike ? or p.description ilike ?)");
        params.add("%" + form.getQuery() + "%");
        params.add("%" + form.getQuery() + "%");
        JDBCUtils.populateSqlAndParams(sql, params, form.getCategories(), "c.id=?");
        JDBCUtils.populateSqlAndParams(sql, params, form.getProducers(), "pr.id=?");
        return new SearchQuery(sql, params);
    }

    @Override
    public int countProductsBySearchForm(SearchForm searchForm) {
        try (Connection connection = dataSource.getConnection()) {
            SearchQuery searchQuery = buildSearchQuery("count (*)", searchForm);
            return JDBCUtils.select(connection, searchQuery.getSql().toString(),
                    countResultSetHandler, searchQuery.getParams().toArray());
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }
}
