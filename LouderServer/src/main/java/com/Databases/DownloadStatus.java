package com.Databases;

import java.sql.*;

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


    public boolean insert_download_status_empty(int macaddress_id){
        boolean status_execution=true;
        try {
            if(create_connection()==true) {
                stat.executeUpdate("Insert into downloadstatus value(null,false,false,"+macaddress_id+");");
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            status_execution=false;
            e.printStackTrace();
        }
        return status_execution;
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

    public boolean get_download_action(String macaddress){
        boolean curent_status=false;
        try {
            if(create_connection()==true) {
                ResultSet rs = stat.executeQuery("Select downloadstatus.action  from downloadstatus \n"+
                        "inner join networking on networking.id=downloadstatus.macaddress\n" +
                        "where  networking.id=(Select id from networking  where macaddress='"+macaddress+"'); ");

                while (rs.next()) {
                    curent_status=rs.getBoolean("action");
                }
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curent_status;
    }


    public boolean update_download_status(String macaddress,boolean status){
        try {
            if(create_connection()==true) {

                if(create_connection()==true) {
                    if(stat.executeUpdate("update downloadstatus set status="+status+" where \n"+
                            " macaddress=(Select id from networking where macaddress='"+macaddress+"');")>0)return true;
                    else return false;
                }
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
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

    public boolean validate_macaddress_and_ip(String macaddress,String ip){
        int save_val=0;
        try {
            if(create_connection()==true) {
                ResultSet rs = stat.executeQuery("select count(*) from networking where macaddress='"+macaddress+"' and ip='"+ip+"';");
                while (rs.next()) {
                    save_val=rs.getInt(1);
                }
                if(save_val<=0)return false;
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


}
