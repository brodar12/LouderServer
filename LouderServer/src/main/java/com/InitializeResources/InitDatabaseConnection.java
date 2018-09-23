package com.InitializeResources;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by mihai on 9/23/2018.
 */
public class InitDatabaseConnection {

    private static InitDatabaseConnection instance = null;
    private static Connection con=null;
    private static String database_url="";
    private static String database_name="";
    private static String database_username="";
    private static String database_password="";
    private static String database_driver="";




    protected InitDatabaseConnection() {
        Properties prop= new Properties();
        FileInputStream in=null;

        try {
            in= new FileInputStream(getClass().getResource("/database.properties").getPath());
            prop.load(in);
            this.database_url=prop.getProperty("database.url");
            this.database_name=prop.getProperty("database.name");
            this.database_username=prop.getProperty("database.username");
            this.database_password=prop.getProperty("database.password");
            this.database_driver=prop.getProperty("database.driver");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(database_driver);
            con= DriverManager.getConnection(database_url+database_name,database_username, database_password);
        } catch (Exception e) {
            System.out.println("Failed conection to DB!!!!");
            e.printStackTrace();
        }

    }


    public static InitDatabaseConnection getInstance() {
        if(instance == null) {
            instance = new InitDatabaseConnection();
        }
        return instance;
    }

    public Connection get_database_connection(){return con;}

}
