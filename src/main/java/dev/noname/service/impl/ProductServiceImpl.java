package dev.noname.service.impl;

import dev.noname.entity.Category;
import dev.noname.entity.Producer;
import dev.noname.entity.Product;
import dev.noname.exception.InternalServerErrorException;
import dev.noname.jdbc.JDBCUtils;
import dev.noname.jdbc.ResultSetHandler;
import dev.noname.jdbc.ResultSetHandlerFactory;
import dev.noname.service.ProductService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class ProductServiceImpl implements ProductService {

    private static final ResultSetHandler<List<Product>> productsResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);

    private final ResultSetHandler<List<Category>> categoryResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.CATEGORY_RESULT_SET_HANDLER);

    private final ResultSetHandler<List<Producer>> producerResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.PRODUCER_RESULT_SET_HANDLER);

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
            return JDBCUtils.select(connection, "select * from producer order by id", producerResultSetHandler);
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }
}
