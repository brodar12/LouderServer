package com.Databases;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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



    public boolean update_all_download_action_status(){
        try {
            if(create_connection()==true) {
                if(stat.executeUpdate("update downloadstatus set action=false,status=false where id>0;")>0)return true;
                else return false;
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update_all_download_action_true(){
        try {
            if(create_connection()==true) {
                if(stat.executeUpdate("  update downloadstatus set action=true where id>0 and status!=true;")>0)return true;
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


    public void set_file_path(String file_path){
        Properties prop= new Properties();
        OutputStream out=null;

        Logger logg = Logger.getLogger(this.getClass().getName());
        logg.log(Level.INFO, "Get path:" +getClass().getResource("/netconfig.properties"));

        try {
            out= new FileOutputStream(getClass().getResource("/netconfig.properties").getPath());
            prop.setProperty("file_path_mem",file_path);
            prop.store(out,null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String get_file_path(){
        Properties prop= new Properties();
        FileInputStream in=null;

        Logger logg = Logger.getLogger(this.getClass().getName());
        logg.log(Level.INFO, "Get path:" +getClass().getResource("/netconfig.properties"));

        try {
            in= new FileInputStream(getClass().getResource("/netconfig.properties").getPath());
            prop.load(in);
            if(prop.getProperty("file_path_mem").toString()!="null"){
                return prop.getProperty("file_path_mem");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Path to file";
    }

   public String get_file_name(){
       String path= get_file_path();
       try {
               if(get_file_path()!="null") {
               File folder = new File(path);
               File[] listOfFiles = folder.listFiles();
               for (File file : listOfFiles) {
                   if (file.isFile()) {
                       return file.getName();
                   }
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return "Not exist!!!";
   }

    public int get_file_size(){
        String path= get_file_path();
        try {
            if(get_file_path()!="null") {
                File folder = new File(path);
                File[] listOfFiles = folder.listFiles();
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        return (int)file.length();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
