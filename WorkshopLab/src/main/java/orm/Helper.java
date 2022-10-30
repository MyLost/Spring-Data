package orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Helper {

    public static final String INT = "INT";
    public static final String DATE = "DATE";
    public static final String VARCHAR = "VARCHAR(255)";
    public static final String SELECT_COLUMN_NAME_FROM_INFORMATION_SCHEMA_COLUMNS_WHERE_TABLE_SCHEMA_CUSTOM_ORM_AND_COLUMN_NAME_ID_AND_TABLE_NAME_STUDENTS =
            "SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA`='Custom_ORM' and 'COLUMN_NAME' != 'id' and `TABLE_NAME` = 'students';";

    public static void setConnection(Connection conn) {
        if(connection == null) {
            connection = conn;
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    private static Connection connection;
    public static final String ENTITY_DOES_NOT_HAVE_PRIMARY_KEY = "Entity does not have primary key";
    public static final String UPDATE_S_SET_S_WHERE_ID_D = "UPDATE %s SET %s WHERE id= %d;";
    public static final String INSERT_INTO_S_S_VALUES_S = "INSERT INTO %s ( %s ) VALUES ( %s )";

    public static void fillEntity(Class<?> table, ResultSet resultSet, Object entity) throws SQLException, IllegalAccessException {

        Field[] declaredFileds = Arrays.stream(table.getDeclaredFields()).toArray(Field[]::new);

        for (Field field : declaredFileds) {
            field.setAccessible(true);
            fillField(field, resultSet, entity);
        }
    }

    public static void fillField(Field field, ResultSet resultSet, Object entity) throws SQLException, IllegalAccessException {

        field.setAccessible(true);

        if(field.getType() == int.class || field.getType() == long.class) {
            field.set(entity, resultSet.getInt(field.getAnnotation(Column.class).name()));
        } else if (field.getType() == LocalDate.class) {
            field.set(entity, LocalDate.parse(resultSet.getString(field.getAnnotation(Column.class).name())));
        } else {
            field.set(entity, resultSet.getString(field.getAnnotation(Column.class).name()));
        }
    }

    public static Field getId(Class<?> entity) {

        return Arrays.stream(entity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException(ENTITY_DOES_NOT_HAVE_PRIMARY_KEY));
    }

    public static List<String> getColumnNames(Class<?> entity) {

        return Arrays.stream(entity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> field.getAnnotation(Column.class).name())
                .collect(Collectors.toList());
    }

    public static List<String> getColumnValues(Object entity) {

        List<Field> fields =  Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class)).collect(Collectors.toList());

        return fields.stream().map(field ->{
            field.setAccessible(true);
            try {
                return String.format("\"%s\"", field.get(entity));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public static String getTableName(Class<?> aClass) {

        return aClass.getAnnotation(Entity.class).name();
    }

    public static boolean doUpdate(Object entity, Field primaryKey) throws IllegalAccessException, SQLException {

        primaryKey.setAccessible(true);
        Object primaryKeyValue = primaryKey.get(entity);

        String tableName = getTableName(entity.getClass());
        List<String> tableColumns = getColumnNames(entity.getClass());
        List<String> tableValues = getColumnValues(entity);

        List<String> setString = new ArrayList<>();
        for (int i = 0; i < tableColumns.size(); i++) {
            setString.add(tableColumns.get(i) + "=" + tableValues.get(i));
        }

        String query = String.format(UPDATE_S_SET_S_WHERE_ID_D,
                tableName,
                setString.stream().collect(Collectors.joining(","))
                , primaryKeyValue);

        if(connection.prepareStatement(query).execute()) {
            return true;
        }
        return false;
    }

    public static boolean doInsert(Object entity, Field primaryKey) throws SQLException {

        String tableName = getTableName(entity.getClass());
        String tableColumns = getColumnNames(entity.getClass()).stream().collect(Collectors.joining(","));
        String tableValues = getColumnValues(entity).stream().map(value -> value.toString()).collect(Collectors.joining(","));
//      String query = "INSERT INTO " + tableName + "(" + tableColumns + ")" + "VALUES(" + tableValues +  ")";
        String query = String.format(INSERT_INTO_S_S_VALUES_S, tableName, tableColumns, tableValues);
        System.out.printf("INSERT QUERY -> %s%n", query);
        return connection.prepareStatement(query).execute();
    }

    public static String getSqlType(Class<?> type) {
        if(type == Integer.class || type == int.class) {
            return INT;
        } else if (type == LocalDate.class) {
            return DATE;
        } else {
            return VARCHAR;
        }
    }

    public static Set<String> getAllFieldsFromTable() throws SQLException {
        Set<String> allFields = new HashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                SELECT_COLUMN_NAME_FROM_INFORMATION_SCHEMA_COLUMNS_WHERE_TABLE_SCHEMA_CUSTOM_ORM_AND_COLUMN_NAME_ID_AND_TABLE_NAME_STUDENTS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            allFields.add(resultSet.getString(1));
        }
        return allFields;
    }

    public static String getNewFields(Class<?> aClass) throws SQLException {
        Set<String> tablefields = Helper.getAllFieldsFromTable();
        Set<String> newFields = new HashSet();
        Arrays.stream(aClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> {
                    String fieldName = field.getAnnotation(Column.class).name();
                    if(!tablefields.contains(fieldName)) {
                        newFields.add(getNameAndDateTypeOfFiled(field));
                    }
                });

        return newFields.stream().collect(Collectors.joining(" , "));
    }

    public static Object getAllFieldsAndDataTypes(Class<?> entityClass) {
        Field[] fields = Arrays.stream(entityClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Column.class)).toArray(Field[]::new);

        List<String> NamesAndDateTypesOfFileds = new ArrayList<>();

        Arrays.stream(fields).forEach(field -> {
            NamesAndDateTypesOfFileds.add(getNameAndDateTypeOfFiled(field));
        });

        return NamesAndDateTypesOfFileds.stream().collect(Collectors.joining(" , "));
    }

    public static String getNameAndDateTypeOfFiled(Field field) {
        String fieldType = Helper.getSqlType(field.getType());
        String fieldName = field.getAnnotation(Column.class).name();
        return String.format("%s %s", fieldName, fieldType);
    }
}
