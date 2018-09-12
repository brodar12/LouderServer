package com.Model;

/**
 * Created by mihai on 3/25/2018.
 */
public class WalletStatistics {
    public int Etherium_wallet;
    public int Bitcoi_wallet;
    public int BitcoinCash_wallet;
    public int Litecoin_wallet;
    public String macaddress;
    public String ipaddress;


    public String date;


    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public int getEtherium_wallet() {
        return Etherium_wallet;
    }

    public void setEtherium_wallet(int etherium_wallet) {
        Etherium_wallet = etherium_wallet;
    }

    public int getBitcoi_wallet() {
        return Bitcoi_wallet;
    }

    public void setBitcoi_wallet(int bitcoi_wallet) {
        Bitcoi_wallet = bitcoi_wallet;
    }

    public int getBitcoinCash_wallet() {
        return BitcoinCash_wallet;
    }

    public void setBitcoinCash_wallet(int bitcoinCash_wallet) {
        BitcoinCash_wallet = bitcoinCash_wallet;
    }

    public int getLitecoin_wallet() {
        return Litecoin_wallet;
    }

    public void setLitecoin_wallet(int litecoin_wallet) {
        Litecoin_wallet = litecoin_wallet;
    }


    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

}
