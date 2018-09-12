package com.Filter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihai on 4/22/2018.
 */
public class FilterNetworkinginfo {

    public String validate_IP(String ip){
        return ip=ip.replaceAll("IPv4 Address. . . . . . . . . . . : ","");
    }

    public  String validate_Macaddress(String mac){
        Logger logg = Logger.getLogger(this.getClass().getName());
        String mac_save="";
        for (int i=0;i<mac.length();++i) {
            if (mac.charAt(i) != ' ')mac_save+=mac.charAt(i);
            else break;
        }
        logg.log(Level.INFO, "Log validation macaddress show all:"+mac_save);
        return mac_save;
    }

}
