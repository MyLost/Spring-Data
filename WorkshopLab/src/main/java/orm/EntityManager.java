package orm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E> {

    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String SELECT_FROM_S_S = "SELECT * FROM %s %s";
    public static final String SELECT_FROM_S_LIMIT_1 = "Select * from %s LIMIT 1;";
    public static final String SELECT_FROM_S_S_LIMIT_1 = "Select * from %s %s LIMIT 1;";

    public EntityManager(Connection connection) {
        Helper.setConnection(connection);
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {

        Field primaryKey = Helper.getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object value = primaryKey.get(entity);

        if(value == null || (long) value <= 0) {
            return Helper.doInsert(entity, primaryKey);
        }
            return Helper.doUpdate(entity, primaryKey);
    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String tableName = Helper.getTableName(table);

        String query = SELECT_FROM + tableName;
        ResultSet result = Helper.getConnection().prepareStatement(query).executeQuery();

        List<E> foundedEntities = new ArrayList<>();

        Constructor constructor = table.getConstructor();

        while(result.next()) {
            E entity = table.getConstructor().newInstance();
            Helper.fillEntity(table,result,entity);
            foundedEntities.add(entity);
        }
        return foundedEntities;
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String tableName = Helper.getTableName(table);

        String query = String.format(SELECT_FROM_S_S, tableName,where != null ? "WHERE " + where : "");
        System.out.println("FIND QUERY -> " + query);
        ResultSet result = Helper.getConnection().prepareStatement(query).executeQuery();

        List<E> foundedEntities = new ArrayList<>();

        Constructor constructor = table.getConstructor();

        while(result.next()) {
            E entity = table.getConstructor().newInstance();
            Helper.fillEntity(table,result,entity);
            foundedEntities.add(entity);
        }
        return foundedEntities;
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Statement statement = Helper.getConnection().createStatement();
        String tableName = Helper.getTableName(table);

        String query = String.format(SELECT_FROM_S_LIMIT_1, tableName);

        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.getDeclaredConstructor().newInstance();

        resultSet.next();
        Helper.fillEntity(table, resultSet, entity);
        return entity;
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Statement statement = Helper.getConnection().createStatement();
        String tableName = Helper.getTableName(table);

        String query = String.format(SELECT_FROM_S_S_LIMIT_1, tableName, where != null ? "WHERE " + where : "");

        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.getDeclaredConstructor().newInstance();

        resultSet.next();
        Helper.fillEntity(table, resultSet, entity);
        return entity;
    }

    @Override
    public void doCreate(Class<E> entityClass) throws SQLException {
        String newFields = Helper.getNewFields(entityClass);
        String tableName = Helper.getTableName(entityClass);
        if(!newFields.isEmpty()) {
            String query =
                    String.format("CREATE TABLE %s ( id INT PRIMARY KEY AUTO_INCREMENT, %s);", tableName, Helper.getAllFieldsAndDataTypes(entityClass));
//                        .replace("java.lang.String","varchar(255)");
            System.out.println(query);
            PreparedStatement statement = Helper.getConnection().prepareStatement(query);
            statement.execute();
        }
        System.out.println(String.format("Table %s already exist", tableName));
    }

    @Override
    public void doAlter(Class<E> entityClass) throws SQLException {
        String tableName = Helper.getTableName(entityClass);
        String newFields = Helper.getNewFields(entityClass);
        if(!newFields.isEmpty()) {
            String query = String.format("ALTER TABLE %s ADD COLUMN %s;", tableName, newFields);
            System.out.println(query);
            Helper.getConnection().prepareStatement(query).executeUpdate();
        }
    }

    @Override
    public boolean delete(Class<E> entityClass, Integer id) throws SQLException {
        String tableName = Helper.getTableName(entityClass);
        String query = String.format("DELETE FROM %s WHERE id = %d;", tableName, id);
        System.out.println("DELETE -> " + query);
        return Helper.getConnection().prepareStatement(query).execute();
    }

}
