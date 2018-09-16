package com.Databases;

import com.Model.PcHardware;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihai on 3/14/2018.
 */
public class HardwareInfoCrud {

    private Connection con= null;
    private Statement stat=null;
    private static  final  String DBurl="jdbc:mysql://localhost/LouderAdmin";
    private static final String username="root";
    private static final String password="1111";


    public HardwareInfoCrud(){}


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



    public ArrayList<PcHardware> get_all_items(){
        ArrayList<PcHardware> items=new ArrayList<PcHardware>();
        try {
            if(create_connection()==true) {
                ResultSet rs = stat.executeQuery("SELECT * from HardwareInfo");

                while (rs.next()) {
                    PcHardware item=new PcHardware();
                    item.setCpu_type(rs.getString("type_cpu"));
                    item.setGpu_type(rs.getString("videocontroller"));
                    item.setPhysicalmemory(rs.getString("totalphysicalmemory"));
                    item.setOstype(rs.getString("ostype"));
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


    public PcHardware get_all_items_hardware_inet(String macaddress){
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
                ResultSet rs = stat.executeQuery("SELECT count(type_cpu) from HardwareInfo;");
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
                ResultSet rs = stat.executeQuery("SELECT count(type_cpu) from HardwareInfo;");
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


    public boolean insert_item(PcHardware item,int net_id){
        boolean status_execution=true;
        try {
            if(create_connection()==true) {
                stat.executeUpdate("Insert into HardwareInfo values(null,'"+item.getCpu_type()+"','"+item.getGpu_type()+"','"+item.getPhysicalmemory()+"','"+item.getOstype()+"',"+net_id+");");
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            status_execution=false;
            e.printStackTrace();
        }
        return status_execution;
    }


    public ArrayList<PcHardware> get_interval_items_hardware_network(int val_min){
        ArrayList<PcHardware> items=new ArrayList<PcHardware>();

        try {
            ResultSet rs,rs1;
            if(create_connection()==true) {
                if(val_min==1)rs = stat.executeQuery("Select * from networking LIMIT 20 OFFSET 0;");
                else rs = stat.executeQuery("Select * from networking LIMIT 20 OFFSET "+val_min+";");

                while (rs.next()) {
                    PcHardware hardware= new PcHardware();
                    hardware.setIp(rs.getString("IP"));
                    hardware.setMacaddress(rs.getString("macaddress"));
                    hardware.setDate_time(rs.getString("net_date"));
                    items.add(hardware);
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




    public ArrayList<PcHardware> get_interval_items(int val_min){
        ArrayList<PcHardware> items=new ArrayList<PcHardware>();
        try {
            ResultSet rs;
            if(create_connection()==true) {
                               if(val_min==1)rs = stat.executeQuery("Select * from HardwareInfo LIMIT 20 OFFSET 0;");
                else rs = stat.executeQuery("Select * from HardwareInfo LIMIT 20 OFFSET "+val_min+";");

                while (rs.next()) {
                    PcHardware item=new PcHardware();
                    item.setCpu_type(rs.getString("type_cpu"));
                    item.setGpu_type(rs.getString("videocontroller"));
                    item.setPhysicalmemory(rs.getString("totalphysicalmemory"));
                    item.setOstype(rs.getString("ostype"));
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


    public boolean verified_element(String macaddress){
        Logger logg = Logger.getLogger(this.getClass().getName());
        try {
            ResultSet rs;
            if(create_connection()==true) {
                rs = stat.executeQuery("Select count(id) from hardwareinfo where macaddress=(Select id from networking where macaddress='"+macaddress+"');");
                while (rs.next()){
                    if(rs.getInt(1)>0){
                        logg.log(Level.INFO, "Find duplicate hardware address!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        return true;
                    }
                }
            }
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
