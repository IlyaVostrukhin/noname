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
        return getAccount(login, password) != null;
    }

    //TODO:Доработать для работы с телефоном и именем
    public Account getAccount(String login, String password) {
        try (Connection connection = dataSource.getConnection()) {
            return JDBCUtils.select(connection, "select * from account where email=? and password=?",
                    accountResultSetHandler, login, password);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }

    @Override
    public Account register(String login, String email, String password, String surName,
                            String firstName, String lastName, String phone, String city,
                            Integer postCode, String address) {
        try (Connection connection = dataSource.getConnection()) {
            Account account = JDBCUtils.insert(connection, "insert into account values " +
                            "(nextval('account_seq'),?,?,?,?,?,?,?,?,?,?)",
                    accountResultSetHandler, firstName, email, phone, password, login, surName,
                    lastName, city, postCode, address);
            connection.commit();
            return account;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }
}
