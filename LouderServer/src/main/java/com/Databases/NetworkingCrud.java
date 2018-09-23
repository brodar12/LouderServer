package com.Databases;

import com.InitializeResources.InitDatabaseConnection;
import com.Model.Networking;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihai on 4/20/2018.
 */
public class NetworkingCrud {


    private Connection con= null;
    private Statement stat=null;



    public NetworkingCrud(){}


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


    public boolean insert_item(Networking item){
        boolean status_execution=true;
        try {
            if(create_connection()==true) {
                stat.executeUpdate("Insert into networking values(null,'"+item.getIp()+"','"+item.getMacaddress()+"',now());");
            }
            stat.close();
           //con.close();
        } catch (SQLException e) {
            status_execution=false;
            e.printStackTrace();
        }
        return status_execution;
    }

    public boolean verified_element(String macaddress){
        Logger logg = Logger.getLogger(this.getClass().getName());
        try {
            ResultSet rs;
            if(create_connection()==true) {
                rs = stat.executeQuery("select count(macaddress) from networking where macaddress='"+macaddress+"';");
                while (rs.next()){
                     if(rs.getInt(1)>0){
                         logg.log(Level.INFO, "Find duplicate mac address!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                         return true;
                     }
                }
            }
            stat.close();
           // con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public int get_id_data(String macaddress){
        int id=0;
            try {
                Logger logg = Logger.getLogger(this.getClass().getName());
                ResultSet rs;
                if(create_connection()==true) {
                    rs = stat.executeQuery("select id from networking where macaddress='"+macaddress+"';");
                    while (rs.next())id=rs.getInt(1);
                    logg.log(Level.INFO, "Showw idddddddddd:" + id);
                }
                stat.close();
               // con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return id;
    }

}
