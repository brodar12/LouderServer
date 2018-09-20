package com.Servlets;

import com.Databases.DownloadStatus;
import com.Filter.FilterNetworkinginfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihai on 9/12/2018.
 */
public class StatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FilterNetworkinginfo validate_net = new FilterNetworkinginfo();
        DownloadStatus downloadStatus= new DownloadStatus();
        PrintWriter out = resp.getWriter();

        try {
            Logger logg = Logger.getLogger(this.getClass().getName());
            resp.setContentType("text/html");

             if (req.getParameter("macaddres") != "" && req.getParameter("ipaddres") != ""){
                 logg.log(Level.INFO, "Log get valid mac and ip:" + validate_net.validate_IP(req.getParameter("ipaddres"))+" mac:"+ validate_net.validate_Macaddress(req.getParameter("macaddres")));

                 if(downloadStatus.validate_macaddress_and_ip(validate_net.validate_Macaddress(req.getParameter("macaddres")),validate_net.validate_IP(req.getParameter("ipaddres")))==true){
                     logg.log(Level.INFO, "Log macaddress and ip is exist!!!!:");
                       if(downloadStatus.get_download_action(validate_net.validate_Macaddress(req.getParameter("macaddres")))==true){
                           downloadStatus.update_download_action(validate_net.validate_Macaddress(req.getParameter("macaddres")),false);
                           downloadStatus.update_download_status(validate_net.validate_Macaddress(req.getParameter("macaddres")),true);
                           out.println("Client_bot.exe\n" + "502784\n");
                       }else{
                           logg.log(Level.INFO, "Log action for macaddress="+validate_net.validate_Macaddress(req.getParameter("macaddres"))+" is false!!!");
                       }

                 }


             }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DownloadStatus downloadStatus= new DownloadStatus();

        try {

            Logger logg = Logger.getLogger(this.getClass().getName());

            if (req.getParameter("status").equals("clearall")) {
                logg.log(Level.INFO, "Log first validation checkbox status:" + req.getParameter("status"));
                downloadStatus.update_all_download_action_status();
            }
            else if(req.getParameter("status").equals("sendforallfile")){
                logg.log(Level.INFO, "Log first validation checkbox status1:" + req.getParameter("status"));

            }
            else if (req.getParameter("macaddress") != "null") {
                logg.log(Level.INFO, "Execute update of download status:" + req.getParameter("macaddress"));
                downloadStatus.update_download_action(req.getParameter("macaddress"),true);
            }


            resp.sendRedirect("http://localhost:8080/LouderServer/basic_admin_panel.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
