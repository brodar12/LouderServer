package com.Model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mihai on 1/21/2018.
 */
@XmlRootElement()
public class PcStatistics {

    private String Etherium_wallet;
    private String Bitcoi_wallet;
    private String BitcoinCash_wallet;
    private String Litecoin_wallet;

    public PcStatistics(){}

    public PcStatistics( String etherium_wallet, String bitcoi_wallet, String bitcoinCash_wallet, String litecoin_wallet) {
        Etherium_wallet = etherium_wallet;
        Bitcoi_wallet = bitcoi_wallet;
        BitcoinCash_wallet = bitcoinCash_wallet;
        Litecoin_wallet = litecoin_wallet;
    }


    public String getEtherium_wallet() {
        return Etherium_wallet;
    }

    public void setEtherium_wallet(String etherium_wallet) {
        Etherium_wallet = etherium_wallet;
    }

    public String getBitcoi_wallet() {
        return Bitcoi_wallet;
    }

    public void setBitcoi_wallet(String bitcoi_wallet) {
        Bitcoi_wallet = bitcoi_wallet;
    }

    public String getBitcoinCash_wallet() {
        return BitcoinCash_wallet;
    }

    public void setBitcoinCash_wallet(String bitcoinCash_wallet) {
        BitcoinCash_wallet = bitcoinCash_wallet;
    }

    public String getLitecoin_wallet() {
        return Litecoin_wallet;
    }

    public void setLitecoin_wallet(String litecoin_wallet) {
        Litecoin_wallet = litecoin_wallet;
    }

}
