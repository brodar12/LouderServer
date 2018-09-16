package com.Model;

/**
 * Created by mihai on 2/25/2018.
 */
public class PcHardware {

    private String cpu_type;
    private String gpu_type;
    private String ostype;
    private String physicalmemory;
    private String ip;
    private String date_time;



    public PcHardware(){}

    public PcHardware( String cpu_type, String gpu_type,String ostype,String physicalmemory) {
        this.ostype=ostype;
        this.cpu_type = cpu_type;
        this.gpu_type = gpu_type;
        this.physicalmemory=physicalmemory;
    }

    public String getIp() {return ip;}

    public void setIp(String ip) {this.ip = ip;}

    public String getMacaddress() {return macaddress;}

    public void setMacaddress(String macaddress) {this.macaddress = macaddress;}

    private String macaddress;

    public String getCpu_type() {
        return cpu_type;
    }

    public void setCpu_type(String cpu_type) {
        this.cpu_type = cpu_type;
    }

    public String getGpu_type() {
        return gpu_type;
    }

    public void setGpu_type(String gpu_type) {this.gpu_type = gpu_type;}

    public String getOstype() {return ostype;}

    public void setOstype(String ostype) {this.ostype = ostype;}

    public String getPhysicalmemory() {return physicalmemory;}

    public void setPhysicalmemory(String physicalmemory) {this.physicalmemory = physicalmemory;}

    public String getDate_time() {return date_time;}

    public void setDate_time(String date_time) {this.date_time = date_time;}

}
