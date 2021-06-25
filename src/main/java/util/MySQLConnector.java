package util;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {

    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/shop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "svvabi181287";

    public static Connection CONNECTION = getConnection();

    @SneakyThrows
    private static Connection getConnection() {
        Class.forName(MYSQL_DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
