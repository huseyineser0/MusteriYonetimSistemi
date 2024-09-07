package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:mysql://localhost:3306/customermanage";
    private final String DB_USERNAMER = "root";
    private final String DB_PASSWORD = "12345678";

    private Database() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAMER, DB_PASSWORD);
        } catch (SQLException expetion) {
            expetion.printStackTrace();
        }

    }

    private Connection getConnection() {
        return connection;
    }

    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Database();
            }
        } catch (SQLException expetion) {
            expetion.printStackTrace();
        }

        return instance.getConnection();
    }
}
