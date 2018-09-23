package com.InitializeResources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihai on 9/23/2018.
 */
public class InitResources {

    private static InitResources instance = null;
    private static String hostname="";
    private static int port=0;



    protected InitResources() {
        Properties prop= new Properties();
        FileInputStream in=null;

        try {
            in= new FileInputStream(getClass().getResource("/netconfig.properties").getPath());
            prop.load(in);
             this.hostname=prop.getProperty("networking.hostname");
             this.port=Integer.parseInt(prop.getProperty("networking.port"));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static InitResources getInstance() {
        if(instance == null) {
            instance = new InitResources();
        }
        return instance;
    }

    public String getHostname(){return hostname;}
    public int getPort(){return  port;}


}
