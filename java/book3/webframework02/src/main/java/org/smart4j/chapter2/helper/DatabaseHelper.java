package org.smart4j.chapter2.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.util.CollectionUtil;
import org.smart4j.chapter2.util.PropsUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    private static final QueryRunner QUERY_RUNNER;
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL;
    private static final BasicDataSource DATA_SOURCE;

    static {
        CONNECTION_THREAD_LOCAL = new ThreadLocal<>();
        QUERY_RUNNER = new QueryRunner();

        Properties config = PropsUtil.loadProps("config.properties");
        DRIVER = config.getProperty("jdbc.driver");
        URL = config.getProperty("jdbc.url");
        USERNAME = config.getProperty("jdbc.username");
        PASSWORD = config.getProperty("jdbc.password");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver class, ", e);
        }
    }

    public static Connection getConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if(connection == null){
            try{
                connection = DATA_SOURCE.getConnection();
            }catch (SQLException e){
                LOGGER.error("get connection failure with DATASOURCE, ", DATA_SOURCE, "with error", e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            LOGGER.error("get connection failure ", e);
//        } finally {
//            CONNECTION_THREAD_LOCAL.set(connection);
//        }
        return connection;
    }

//    public static void closeConnection() {
//        Connection connection = CONNECTION_THREAD_LOCAL.get();
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                LOGGER.error("close connection failure, ", e);
//            } finally {
//                CONNECTION_THREAD_LOCAL.remove();
//            }
//        }
//    }

    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... parameters) {
        List<T> entityList;

        try {
            Connection connection = getConnection();
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), parameters);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure, ", e);
            throw new RuntimeException(e);
        }
        return entityList;
    }

    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... parameters) {
        T entity;
        try {
            Connection connection = getConnection();
            entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), parameters);
        } catch (SQLException e) {
            LOGGER.error("query entity execution failure, ", e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    public static List<Map<String, Object>> executeQuery(String sql, Object... parameters) {
        List<Map<String, Object>> resultMapList = null;
        try {
            Connection connection = getConnection();
            resultMapList = QUERY_RUNNER.query(connection, sql, new MapListHandler(), parameters);
        } catch (Exception e) {
            LOGGER.error("execute query for maplist failure, ", e);
            throw new RuntimeException(e);
        }
        return resultMapList;
    }

    /**
     * 更新实体
     *
     * @param sql
     * @param parameters
     * @return
     */

    public static int executeUpdate(String sql, Object... parameters) {
        int rows = 0;
        try {
            Connection connection = getConnection();
            rows = QUERY_RUNNER.update(connection, sql, parameters);
        } catch (SQLException e) {
            LOGGER.error("execute update failure, ", e);
            throw new RuntimeException(e);
        }
        return rows;
    }

    /**
     * 插入实体
     *
     * @param entityClass
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {

        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("can not update empty entity: filed Map is empty");
            return false;
        }

        String sql = "UPDATE " + getTableName(entityClass).toLowerCase() + " SET ";
        StringBuilder columns = new StringBuilder("(");

        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append("=?, ");
        }

        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");

        sql = sql + columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id=?";
        List<Object> paramList = new ArrayList<>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();
        return executeUpdate(sql, paramList) == 1;
    }

    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("can not insert empty entity: filed Map is empty");
            return false;
        }


        String sql = "INSERT INTO " + getTableName(entityClass).toLowerCase();
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append((", "));
            values.append("?, ");
        }

        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql = sql + columns + " VALUES " + values;
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;
    }

    /**
     * 删除实体
     */
    public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
        String sql = "DELETE FROM " + getTableName(entityClass).toLowerCase() + " WHERE id = ?";
        return executeUpdate(sql, id) == 1;
    }

    private static String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName();
    }
}
