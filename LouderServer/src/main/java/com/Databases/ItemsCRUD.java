package com.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by mihai on 1/21/2018.
 */
public class ItemsCRUD {
    private Connection con= null;
    private Statement stat=null;
    private static  final  String DBurl="jdbc:mysql://localhost/LouderAdmin";
    private static final String username="root";
    private static final String password="1111";


    public ItemsCRUD(){}


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


/*
    public ArrayList<PcStatistics> get_all_items(){
        ArrayList<PcStatistics> items=new ArrayList<PcStatistics>();
        try {
            if(create_connection()==true) {
                ResultSet rs = stat.executeQuery("SELECT * from pcstatistics");

                while (rs.next()) {
                    PcStatistics item=new PcStatistics();
                    item.setPC_ip(rs.getString("IP"));
                    item.setMac_address(rs.getString("mac_address"));
                    item.setBitcoi_wallet(rs.getString("Bitcoi_wallet"));
                    item.setBitcoinCash_wallet(rs.getString("BitcoinCash_wallet"));
                    item.setEtherium_wallet(rs.getString("Etherium_wallet"));
                    item.setLitecoin_wallet(rs.getString("Litecoin_wallet"));
                    items.add(item);
                }
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }


    public int get_count_items(){
        int count=0,count_db=0;
        try {
            if(create_connection()==true) {
                ResultSet rs = stat.executeQuery("SELECT count(mac_address) from pcstatistics;");
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
                ResultSet rs = stat.executeQuery("SELECT count(id) from pcstatistics;");
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


    public boolean insert_item(PcStatistics item){
        boolean status_execution=true;
        try {
            if(create_connection()==true) {

                stat.executeUpdate("Insert into pcstatistics values(null,'"+item.getPC_ip()+"','"+item.getEtherium_wallet()+"'," +
                        "'"+item.getBitcoi_wallet()+"','"+item.getBitcoinCash_wallet()+"','"+item.getLitecoin_wallet()+"','"+item.getMac_address()+"');");
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            status_execution=false;
            e.printStackTrace();
        }
        return status_execution;
    }



    public ArrayList<PcStatistics> get_interval_items(int val_min){
        ArrayList<PcStatistics> items=new ArrayList<PcStatistics>();
        try {
            ResultSet rs;
            if(create_connection()==true) {
                //ResultSet rs = stat.executeQuery("Select * from pcstatistics where id>"+val_min+" and id<="+val_max+";");
               // limit 10 offset 3
                if(val_min==1)rs = stat.executeQuery("Select * from pcstatistics LIMIT 20 OFFSET 0;");
                 else rs = stat.executeQuery("Select * from pcstatistics LIMIT 20 OFFSET "+val_min+";");

                while (rs.next()) {
                    PcStatistics item=new PcStatistics();
                    item.setPC_ip(rs.getString("IP"));
                    item.setMac_address(rs.getString("mac_address"));
                    item.setBitcoi_wallet(rs.getString("Bitcoi_wallet"));
                    item.setBitcoinCash_wallet(rs.getString("BitcoinCash_wallet"));
                    item.setEtherium_wallet(rs.getString("Etherium_wallet"));
                    item.setLitecoin_wallet(rs.getString("Litecoin_wallet"));
                    items.add(item);
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

*/



}
