package com.Databases;

import com.InitializeResources.InitDatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by mihai on 1/21/2018.
 */
public class ItemsCRUD {
    private Connection con= null;
    private Statement stat=null;



    public ItemsCRUD(){}


    public boolean create_connection(){
        boolean conect_status=true;
        try {
            con= InitDatabaseConnection.getInstance().get_database_connection();
            stat=con.createStatement();
        } catch (Exception e) {
            conect_status=false;
            System.out.println("Failed conection to DB!!!!");
            e.printStackTrace();
        }

        return conect_status;
    }



}
