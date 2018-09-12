package com.Model;

/**
 * Created by mihai on 9/12/2018.
 */
public class StatusOfDownload {


    private boolean status;
    private boolean action;
    private String macaddress;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }


}
