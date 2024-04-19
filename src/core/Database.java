package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    //Signleton Design Pattern
    private static  Database instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/turizmacente";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASSWORD = "postgres";

    private Database(){
        try{
            this.connection = DriverManager.getConnection(
                    DB_URL, DB_USERNAME, DB_PASSWORD
            );
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public static Connection getInstance() {
        try{
            if(instance == null || instance.getConnection().isClosed()){
                instance = new Database();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return instance.getConnection();
    }

}
