package com.Servlets;

import com.Databases.HardwareInfoCrud;
import com.Databases.NetworkingCrud;
import com.Databases.WalletstatisticsCRUD;
import com.Filter.FilterHardwareinfo;
import com.Filter.FilterNetworkinginfo;
import com.Model.Networking;
import com.Model.PcHardware;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihai on 9/12/2018.
 */
public class StatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Logger logg = Logger.getLogger(this.getClass().getName());

            String status_checkbox[] = req.getParameterValues("status");

            if(status_checkbox != null && status_checkbox.length!=0){
                for(int i=0;i<status_checkbox.length;++i) {
                    logg.log(Level.INFO, "Log first validation checkbox status:" + status_checkbox[i]);
                }
            }

            resp.sendRedirect("https://www.google.com");
            /*if (req.getParameter("type").equals("network")) {
                if (req.getParameter("macaddress") != "" && req.getParameter("ip") != "") {
                    logg.log(Level.INFO, "Log first validation macaddress show all:" + req.getParameter("type"));
                    logg.log(Level.INFO, "IP " + req.getParameter("ip"));
                    if (infonetworking.verified_element(validate_net.validate_Macaddress(req.getParameter("macaddress"))) == false) {
                        net.setIp(validate_net.validate_IP(req.getParameter("ip")));
                        net.setMacaddress(validate_net.validate_Macaddress(req.getParameter("macaddress")));
                        infonetworking.insert_item(net);
                    }
                }
            } else if (req.getParameter("type").equals("hardware")) {
                logg.log(Level.INFO, "Log mac show:::::" + req.getParameter("macaddres"));
                if(hardwareinf.verified_element(validate_net.validate_Macaddress(req.getParameter("macaddres")))==false) {
                    if (req.getParameter("cpu") != "") {
                        hardware.setCpu_type(validate_hardware.validate_cpu(req.getParameter("cpu")));
                    }
                    if (req.getParameter("videocontroller") != "") {
                        hardware.setGpu_type(validate_hardware.validate_gpu(req.getParameter("videocontroller")));
                    }
                    if (req.getParameter("osversion") != "") {
                        hardware.setOstype(validate_hardware.validate_ostype(req.getParameter("osversion")));
                    }
                    if (req.getParameter("totalphysicalmemory") != "") {
                        hardware.setPhysicalmemory(validate_hardware.validate_physicalmemory(req.getParameter("totalphysicalmemory")));
                    }
                    hardwareinf.insert_item(hardware, infonetworking.get_id_data(validate_net.validate_Macaddress(req.getParameter("macaddres"))));
                }
            }
            else if(req.getParameter("type").equals("wallet")){
                logg.log(Level.INFO, "Type::" + req.getParameter("type"));
                walletinf.insert_item(infonetworking.get_id_data(validate_net.validate_Macaddress(req.getParameter("macaddress"))),
                        req.getParameter("wallet"),walletinf.get_id_wallet(req.getParameter("typewallet")));
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
