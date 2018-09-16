package com.Databases;

import com.Model.PcHardware;
import com.Model.StatusOfDownload;
import com.Model.WalletStatistics;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by mihai on 9/12/2018.
 */
public class DownloadStatus {

    private Connection con= null;
    private Statement stat=null;
    private static  final  String DBurl="jdbc:mysql://localhost/LouderAdmin";
    private static final String username="root";
    private static final String password="1111";



    public boolean create_connection(){
        boolean conect_status=true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(DBurl,username, password);
            stat=con.createStatement();
        } catch (Exception e) {
            conect_status=false;
            System.out.println("Failed conection to DB!!!!");
            e.printStackTrace();
        }

        return conect_status;
    }



    public boolean get_download_status(String macaddress){
        boolean curent_status=false;
        try {
            if(create_connection()==true) {
                ResultSet rs = stat.executeQuery("Select downloadstatus.status  from downloadstatus \n"+
                        "inner join networking on networking.id=downloadstatus.macaddress\n" +
                        "where  networking.id=(Select id from networking  where macaddress='"+macaddress+"'); ");

                while (rs.next()) {
                    curent_status=rs.getBoolean("status");
                }
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curent_status;
    }


    public boolean update_download_action(String macaddress,boolean status){
        try {
            if(create_connection()==true) {
                    if(stat.executeUpdate("update downloadstatus set action="+status+" where \n"+
                            " macaddress=(Select id from networking where macaddress='"+macaddress+"');")>0)return true;
                    else return false;
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


}
