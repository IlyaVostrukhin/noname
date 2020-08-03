package dev.noname.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class JDBCUtils {

    private JDBCUtils() {
    }

    public static <T> T select(Connection connection,
                               String sql,
                               ResultSetHandler<T> resultSetHandler,
                               Object... parameters) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            populatePreparedStatement(ps, parameters);
            ResultSet rs = ps.executeQuery();
            return resultSetHandler.handle(rs);
        }
    }

    public static void populateSqlAndParams(StringBuilder sql, List<Object> params,
                                            List<Integer> list, String expression) {
        if (list != null && !list.isEmpty()) {
            sql.append(" and (");
            for (int i = 0; i < list.size(); i++) {
                sql.append(expression);
                params.add(list.get(i));
                if (i != list.size() - 1) {
                    sql.append(" or ");
                }
            }
            sql.append(")");
        }
    }

    private static void populatePreparedStatement(PreparedStatement ps, Object... parameters)
            throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i + 1, parameters[i]);
            }
        }
    }
}
