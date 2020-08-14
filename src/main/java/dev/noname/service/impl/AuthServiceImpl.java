package dev.noname.service.impl;

import dev.noname.entity.Account;
import dev.noname.exception.InternalServerErrorException;
import dev.noname.jdbc.JDBCUtils;
import dev.noname.jdbc.ResultSetHandler;
import dev.noname.jdbc.ResultSetHandlerFactory;
import dev.noname.service.AuthService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class AuthServiceImpl implements AuthService {
    private static final ResultSetHandler<Account> accountResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ACCOUNT_RESULT_SET_HANDLER);

    private final DataSource dataSource;

    public AuthServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isExist(String login, String password) {
        try (Connection connection = dataSource.getConnection()) {
            Account account = JDBCUtils.select(connection, "select * from account where email=?", accountResultSetHandler, login);
            return account != null;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }

    @Override
    public void register(String name, String email, String phone, String password) {
        try (Connection connection = dataSource.getConnection()) {
            JDBCUtils.insert(connection, "insert into account values (nextval('account_seq'),?,?,?,?)",
                    accountResultSetHandler, name, email, phone, password);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }
}
