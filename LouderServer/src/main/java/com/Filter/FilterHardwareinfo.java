package com.Filter;

/**
 * Created by mihai on 3/14/2018.
 */
public class FilterHardwareinfo {

    public  String validate_cpu(String cpu_data){
        return cpu_data=cpu_data.replaceAll("Name ","");
    }

    public  String validate_gpu(String gpu_data){
        return gpu_data=gpu_data.replaceAll("Name ","");
    }

    public  String validate_ostype(String ostype_data){
        return ostype_data=ostype_data.replaceAll("Caption=","");
    }

    public  String validate_physicalmemory(String physicalmemory_data){return physicalmemory_data=physicalmemory_data.replaceAll("TotalPhysicalMemory ","");}

    public  String validate_IP(String ip){return ip=ip.replaceAll("Autoconfiguration IPv4 Address. . : ","");}

    public  String validate_macaddress(String macaddress){return macaddress=macaddress.substring(0,17);}

}
